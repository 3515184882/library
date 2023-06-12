package com.bjpowernode.controller;

import com.bjpowernode.pojo.Book;
import com.bjpowernode.pojo.PageResult;
import com.bjpowernode.pojo.Result;
import com.bjpowernode.pojo.User;
import com.bjpowernode.service.BookService;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/selectNewbooks")
    public ModelAndView selectNewbooks(Book book,Integer pageNum,Integer pageSize, HttpServletRequest request){
        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize == null){
            pageSize=5;
        }

        PageResult pageResult = bookService.selectNewBooks(book,pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books_new");
        modelAndView.addObject("pageResult",pageResult);
        modelAndView.addObject("search",book);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Result<Book> findById(String bookId){
        try {
            Book book = bookService.findById(bookId);
            if (book==null){
                return new Result(false,"查询图书失败！");
            }
            return new Result(true,"查询图书成功",book);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"查询图书失败！");
        }
    }

    @RequestMapping("/borrowBook")
    @ResponseBody
    public Result borrowBook(Book book, HttpSession session){
        //获取当前用户名
        String pname=((User)session.getAttribute("USER_SESSION")).getUserName();
        book.setBookBorrower(pname);

        try {
            //根据图书的id和用户进行图书借阅
            Integer count = bookService.editBook(book);
            if (count!=1){
                return new Result(false,"借阅图书失败");
            }
            return new Result(true,"借阅成功，请到行政中心取书");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"借阅图书失败！");
        }


    }

    @RequestMapping("/search")
    public ModelAndView search(Book book, Integer pageNum, Integer pageSize, HttpServletRequest request){
        if (pageNum==null){
            pageNum =1;
        }
        if (pageSize == null){
            pageSize = 5;
        }
        //查询到的图书
        PageResult pageResult = bookService.search(book, pageNum, pageSize);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("books");
        //将查询到的数据存放到ModelAndView的对象中
        modelAndView.addObject("pageResult",pageResult);
        //将查询到的参数返回到页面，用于回显到查询的输入框中
        modelAndView.addObject("search",book);
        //将当前页码返回到页面，用于分页插件的分页显示
        modelAndView.addObject("pageNum",pageNum);
        //将当前查询的控制器路径返回到页面，页码变化时继续向该路径发送请求
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;

    }

    @RequestMapping("/addBook")
    @ResponseBody
    public Result addBook(Book book){
        try {
            Integer count = bookService.addBook(book);
            if (count !=1){
                return new Result(false,"新增图书失败！");
            }
            return new Result(true,"新增图书成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"新增图书失败！");
        }

    }

    @RequestMapping("/editBook")
    @ResponseBody
    public Result editBook(Book book){
        try {
            Integer count = bookService.editBook1(book);
            if (count != 1){
                return new Result(false,"编辑失败！");
            }
            return new Result(true,"编辑成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"编辑失败！");
        }
    }

    /**
     * 分页查询当前被借阅且未归还的图书信息
     * @param book 数据列表的当前页码
     * @param pageNum 数据列表1页展示多少条数据
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping("/searchBorrowed")
    public ModelAndView searchBorrowed(Book book,Integer pageNum,Integer pageSize,HttpServletRequest request){
        if (pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=5;
        }
        //获取当前用户
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        PageResult pageResult = bookService.searchBooks(book, user, pageNum, pageSize);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("book_borrowed");
        //将查询到的数据存放到ModelAndView的对象中
        modelAndView.addObject("pageResult",pageResult);
        //将查询的参数返回到页面，用于回显到查询的输入框中
        modelAndView.addObject("search",book);
        //将当前的页码返回到页面，用于分页插件的分页显示
        modelAndView.addObject("pageNum",pageNum);
        //将当前查询的控制器路径返回到页面，页码变化的时候继续向该路径发送请求
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;
    }

    /**
     * 归还图书
     * @param bookId 归还的图书id
     * @param session
     * @return
     */
    @RequestMapping("/returnBook")
    @ResponseBody
    public Result returnBook(String bookId,HttpSession session){
        //获取当前登录的用户信息
        User user= (User) session.getAttribute("USER_SESSION");
        try {
            boolean flag= bookService.returnBook(bookId,user);
            if (!flag){
                return new Result(true,"还书确认中，请先到行政中心还书！");
            }
            return new Result(false,"还书失败！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"还书失败！");
        }
    }

    /**
     * 图书确认归还确认
     * @param bookId  确认归还的图书id
     * @return
     */
    @RequestMapping("/returnConfirm")
    @ResponseBody
    public Result returnConfirm(String bookId){
        try {
            Integer count = bookService.returnConfirm(bookId);
            if (count!=1){
                return new Result(false,"确认失败!");
            }
            return new Result(true,"确认成功！");
        } catch (Exception e) {
          e.printStackTrace();
          return new Result(false,"确认失败！");
        }
    }




}
