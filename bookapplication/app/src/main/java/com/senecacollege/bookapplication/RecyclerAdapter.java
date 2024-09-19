package com.senecacollege.bookapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Book> books;

    public RecyclerAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book currentBook = books.get(position);
        holder.title.setText(currentBook.getTitle());
        holder.author.setText(currentBook.getAuthor());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView author;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            author = view.findViewById(R.id.author);
        }
    }
}