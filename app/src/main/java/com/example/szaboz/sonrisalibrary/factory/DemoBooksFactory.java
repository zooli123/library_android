package com.example.szaboz.sonrisalibrary.factory;

import android.widget.ArrayAdapter;

import com.example.szaboz.sonrisalibrary.bean.Book;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by szaboz on 2017.06.30..
 */

public class DemoBooksFactory {
    private ArrayList<Book> booksData;

    public DemoBooksFactory(){
        booksData = new ArrayList<>();
        booksData.add(new Book("A dzsungel könyve", "Szabó Zoltán", new Date()));
        booksData.add(new Book("A rémségek cirkusza", "Szabó Zoltán", new Date()));
        booksData.add(new Book("Thinking in Java"));
        booksData.add(new Book("Clean code"));
        booksData.add(new Book("A gyűrűk ura:A Gyűrű Szövetége", "Szabó Zoltán", new Date()));
        booksData.add(new Book("Galaxis utikalauz stopposoknak", "Szabó Zoltán János", new Date(117,01,01) ));
        booksData.add(new Book("C++"));
        booksData.add(new Book("PHP 5"));
    }
    public ArrayList<Book> getDemoBooksData() {
        return booksData;
    }
}
