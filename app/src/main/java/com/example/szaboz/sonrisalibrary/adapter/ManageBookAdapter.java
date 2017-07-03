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
 * Created by szaboz on 2017.07.03..
 */

public class ManageBookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private ArrayList<Book> books;
    private LayoutInflater inflater;

    public ManageBookAdapter(Context context, LayoutInflater inflater, ArrayList<Book> books) {
        super(context, 0, books);
        this.context = context;
        this.books = books;
        this.inflater = inflater;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ManageBookAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView=inflater.inflate(R.layout.manage_books_list, null, true);
            TextView bookTitle = (TextView) convertView.findViewById(R.id.manage_book_title);
            TextView bookBorrowDate = (TextView) convertView.findViewById(R.id.manage_book_borrow_date);

            holder = new ManageBookAdapter.ViewHolder();
            holder.bookTitle = bookTitle;
            holder.bookBorrowDate = bookBorrowDate;

            convertView.setTag(holder);
        } else {
            holder = (ManageBookAdapter.ViewHolder) convertView.getTag();
        }

        holder.bookTitle.setText(books.get(position).getTitle());
        holder.bookBorrowDate.setText(books.get(position).getBorrowDate());
        return convertView;
    }

    static class ViewHolder {
        TextView bookTitle;
        TextView bookBorrowDate;
    }

}
