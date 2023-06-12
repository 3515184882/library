package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.BookMapper;
import com.bjpowernode.pojo.Book;
import com.bjpowernode.pojo.PageResult;
import com.bjpowernode.pojo.Record;
import com.bjpowernode.pojo.User;
import com.bjpowernode.service.BookService;
import com.bjpowernode.service.RecordService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class BookServiceImpl implements BookService  {


    @Autowired
    BookMapper bookMapper;

    /**
     * 根据当前页面和每页需要展示的数据条数，查询最新上新的图书信息
     * @param pageNum  当前页码
     * @param pageSize 每页显示数量
     * @return
     */


    @Override
    public PageResult selectNewBooks(Book book,Integer pageNum, Integer pageSize) {
        //设置分页查询的参数，开始分页
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> page = bookMapper.selectNewBook(book);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Book findById(String bookId) {
        return bookMapper.findById(Integer.valueOf(bookId));
    }

    @Override
    public Integer editBook(Book book) {
        //根据id查询出需要借阅的完整图书信息
        Book b = this.findById(book.getBookId() + "");
        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        //设置当天借阅时间
        book.setBookBorrowtime(dateFormat.format(new Date()));
        //设置所借阅的图书状态为借阅中
        book.setBookStatus("1");
        //将图书的价格设置在book对象中
        book.setBookPrice(b.getBookPrice());
        //将图书的上架设置在book对象中
        book.setBookUploadtime(b.getBookUploadtime());
        return bookMapper.editBook(book);
    }

    /**
     *
     * @param book  封装查询的对象
     * @param pageNum  当前页码
     * @param pageSize  每页显示的数量
     * @return
     */
    @Override
    public PageResult search(Book book, Integer pageNum, Integer pageSize) {
        //设置分页查询的参数，开始分页
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> page = bookMapper.searchBooks(book);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 新增图书
     * @param book
     * @return
     */
    @Override
    public Integer addBook(Book book) {
        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        //设置新增图书上架时间
        book.setBookUploadtime(dateFormat.format(new Date()));
        return bookMapper.addBook(book);
    }

    @Override
    public Integer editBook1(Book book) {
        return bookMapper.editBook1(book);
    }

    /**
     * 查询用户当前借阅的图书
     * @param book 封装了查询条件的对象
     * @param user 当前登录用户
     * @param pageNum   当前页码
     * @param pageSize 每页显示数量
     * @return
     */
    @Override
    public PageResult searchBooks(Book book, User user, Integer pageNum, Integer pageSize) {
        //设置分页查询的参数，开始分页
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> page;
        //将当前登录的用户放入查询条件中
        book.setBookBorrower(user.getUserName());
        //如果是管理员，查询当前用户借阅但未归还的图书和所有待待确认归还的图书
        if ("ADMIN".equals(user.getUserRole())){
            page= bookMapper.selectBorrowed(book);
        }else{
            //如果是普通用户查询当前用户借阅但未归还的图书
            page=bookMapper.selectMyBorrowed(book);
        }
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 归还图书
     * @param bookId 归还的图书id
     * @param user 归还的人员，也就是当前图书的借阅者
     * @return
     */
    @Override
    public boolean returnBook(String bookId, User user) {
        //根据图书id查询出图书的完整信息
        Book book = this.findById(bookId);
        //再次核验当前登录人员和图书借阅者是不是同一个人
        boolean rb=book.getBookBorrower().equals(user.getUserName());
        //如果是同一个人，允许归还
        if (rb){
            //将图书借阅状态修改为归还中
            book.setBookStatus("2");
            bookMapper.editBook1(book);
        }
        return false;
    }

    @Autowired
    private RecordService recordService;


    //归还确认
    @Override
    public Integer returnConfirm(String bookId) {
        //根据图书id查询图书的完整信息
        Book book =this.findById(bookId);
        //根据归还确认的图书信息，设置借阅记录
        Record record =this.setRecord(book);
        //将图书借阅状态改为可借阅
        book.setBookStatus("0");
        //清除当前借阅人信息
        book.setBookBorrower("");
        //清除当前图书的借阅时间信息
        book.setBookBorrowtime("");
        //清除当前图书的预计归还时间信息
        book.setBookReturntime("");
        Integer count = bookMapper.editBook(book);
        //如果归还确认成功，则新增借阅记录
        if (count==1){
            return recordService.addRecord(record);
        }
        return 0;
    }
    /**
     * 根据图书信息设置借阅记录的信息
     *
     */
    private Record setRecord(Book book){
        Record record =new Record();
        //设置借阅记录的图书名称
        record.setRecordBookname(book.getBookName());
        //设置借阅记录的图书isbn
        record.setRecordBookisbn(book.getBookIsbn());
        //设置借阅记录的借阅人
        record.setRecordBorrower(book.getBookBorrower());
        //设置借阅记录的借阅时间
        record.setRecordBorrowtime(book.getBookBorrowtime());
        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        //设置图书归还确认的当天为图书归还时间
        record.setRecordRemandtime(dateFormat.format(new Date()));
        return record;
    }
}
