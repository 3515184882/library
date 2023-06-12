package com.bjpowernode.mapper;

import com.bjpowernode.pojo.Book;
import com.github.pagehelper.Page;


public interface BookMapper {
    Page<Book> selectNewBook(Book book);
    //根据id查询图书信息
    Book findById(Integer bookId);
    //编辑图书信息
    Integer editBook(Book book);

    //查询图书
    Page<Book> searchBooks(Book book);

    //新增图书
    Integer addBook(Book book);

    //编辑图书信息
    Integer editBook1(Book book);

    //查询借阅但未归还的图书和所有待确认归还的图书
    Page<Book> selectBorrowed(Book book);

    //查询借阅但未归还的图书
    Page<Book> selectMyBorrowed(Book book);
}
