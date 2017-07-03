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
        booksData.add(new Book("A dzsungel könyve", "szaboz", new Date()));
        booksData.add(new Book("A rémségek cirkusza", "szaboz", new Date()));
        booksData.add(new Book("Thinking in Java"));
        booksData.add(new Book("Clean code"));
        booksData.add(new Book("A gyűrűk ura:A Gyűrű Szövetége", "szaboz", new Date()));
        booksData.add(new Book("Galaxis utikalauz stopposoknak", "szaboz", new Date(117,01,01) ));
        booksData.add(new Book("C++"));
        booksData.add(new Book("PHP 5"));
        booksData.add(new Book("Az ezüst tó kincse"));
        booksData.add(new Book("Old Shatterhand"));
        booksData.add(new Book("Mosó Masa mosodája"));
        booksData.add(new Book("Harry Potter és a titkok kamrája"));
        booksData.add(new Book("A Harmadik Birodalom felemelkedése"));
        booksData.add(new Book("Assembly", "szaboz", new Date(117, 04, 12)));

        booksData.sort((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()));
    }
    public ArrayList<Book> getDemoBooksData() {
        return booksData;
    }
}
