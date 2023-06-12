package com.bjpowernode.service;

import com.bjpowernode.pojo.Book;
import com.bjpowernode.pojo.PageResult;
import com.bjpowernode.pojo.User;
import net.sf.jsqlparser.statement.insert.Insert;

public interface BookService {

    PageResult selectNewBooks(Book book,Integer pageNum,Integer pageSize);


    //根据id查询图书信息
    Book findById(String bookId);

    //编辑借阅图书信息
    Integer editBook(Book book);

    //分页查询图书
    PageResult search(Book book, Integer pageNum,Integer pageSize);

    //新增图书
    Integer addBook(Book book);

    //编辑图书信息
    Integer editBook1(Book book);

    //查询当前借阅的图书
    PageResult searchBooks(Book book, User user,Integer pageNum,Integer pageSize);

    //归还图书
    boolean returnBook(String bookId,User user);

    //归还确认
    Integer returnConfirm(String bookId);



}
