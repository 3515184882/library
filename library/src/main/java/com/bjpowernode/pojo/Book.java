package com.bjpowernode.pojo;

public class Book {
    private Integer bookId;
    private String bookName;
    private String bookIsbn;
    private String bookPress;
    private String bookAuthor;
    private Integer bookPagination;
    private Double bookPrice;
    private String bookUploadtime;
    private String bookStatus;
    private String bookBorrower;
    private String bookBorrowtime;
    private String bookReturntime;

    public Book() {
    }

    public Book(Integer bookId, String bookName, String bookIsbn, String bookPress, String bookAuthor, Integer bookPagination, Double bookPrice, String bookUploadtime, String bookStatus, String bookBorrower, String bookBorrowtime, String bookReturntime) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookIsbn = bookIsbn;
        this.bookPress = bookPress;
        this.bookAuthor = bookAuthor;
        this.bookPagination = bookPagination;
        this.bookPrice = bookPrice;
        this.bookUploadtime = bookUploadtime;
        this.bookStatus = bookStatus;
        this.bookBorrower = bookBorrower;
        this.bookBorrowtime = bookBorrowtime;
        this.bookReturntime = bookReturntime;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookPagination() {
        return bookPagination;
    }

    public void setBookPagination(Integer bookPagination) {
        this.bookPagination = bookPagination;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookUploadtime() {
        return bookUploadtime;
    }

    public void setBookUploadtime(String bookUploadtime) {
        this.bookUploadtime = bookUploadtime;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBookBorrower() {
        return bookBorrower;
    }

    public void setBookBorrower(String bookBorrower) {
        this.bookBorrower = bookBorrower;
    }

    public String getBookBorrowtime() {
        return bookBorrowtime;
    }

    public void setBookBorrowtime(String bookBorrowtime) {
        this.bookBorrowtime = bookBorrowtime;
    }

    public String getBookReturntime() {
        return bookReturntime;
    }

    public void setBookReturntime(String bookReturntime) {
        this.bookReturntime = bookReturntime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", bookPress='" + bookPress + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPagination=" + bookPagination +
                ", bookPrice=" + bookPrice +
                ", bookUploadtime='" + bookUploadtime + '\'' +
                ", bookStatus='" + bookStatus + '\'' +
                ", bookBorrower='" + bookBorrower + '\'' +
                ", bookBorrowtime='" + bookBorrowtime + '\'' +
                ", bookReturntime='" + bookReturntime + '\'' +
                '}';
    }
}
