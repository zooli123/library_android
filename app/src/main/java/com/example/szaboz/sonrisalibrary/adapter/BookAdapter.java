package com.example.szaboz.sonrisalibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.szaboz.sonrisalibrary.R;
import com.example.szaboz.sonrisalibrary.bean.Book;

import java.util.ArrayList;

/**
 * Created by szaboz on 2017.06.30..
 */

public class BookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private ArrayList<Book> books;
    private LayoutInflater inflater;

    public BookAdapter(Context context, LayoutInflater inflater, ArrayList<Book> books) {
        super(context, 0, books);
        this.context = context;
        this.books = books;
        this.inflater = inflater;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BookAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView=inflater.inflate(R.layout.borrow_books_list, null, true);
            TextView bookTitle = (TextView) convertView.findViewById(R.id.book_title);
            TextView bookBorrower = (TextView) convertView.findViewById(R.id.book_borrower);
            TextView bookBorrowDate = (TextView) convertView.findViewById(R.id.book_borrow_date);

            holder = new BookAdapter.ViewHolder();
            holder.bookTitle = bookTitle;
            holder.bookBorrower = bookBorrower;
            holder.bookBorrowDate = bookBorrowDate;

            convertView.setTag(holder);
        } else {
            holder = (BookAdapter.ViewHolder) convertView.getTag();
        }

        holder.bookTitle.setText(books.get(position).getTitle());
        holder.bookBorrower.setText(books.get(position).getBorrower());
        holder.bookBorrowDate.setText(books.get(position).getBorrowDate());
        return convertView;
    }

    static class ViewHolder {
        TextView bookTitle;
        TextView bookBorrower;
        TextView bookBorrowDate;
    }

}
