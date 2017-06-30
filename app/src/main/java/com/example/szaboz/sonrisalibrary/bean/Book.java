package com.example.szaboz.sonrisalibrary.bean;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by szaboz on 2017.06.30..
 */

public class Book {
    private String title;
    private String borrower;
    private Date borrowDate;

    public Book(String title, String borrower, Date borrowDate){
        this.title = title;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
    }

    public Book (String title) {
        this.title = title;
    }

    public String getBorrowDate(){
        if(borrowDate != null)
            return DateFormat.getDateInstance().format(borrowDate);
        return "";
    }

    public void setBorrowDate(Date date) {
        borrowDate = date;
    }

    public void setBorrower(String name) {
        borrower = name;
    }

    public String getTitle(){return title;}
    public String getBorrower(){return borrower;}

    public String toString(){
        return title + " " + borrower != null + borrower ? " " : "" + getBorrowDate();
    }
}
