package com.senecacollege.assignment3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<MovieModel> movies;
    private OnItemClickListener onItemClickListener;


    public RecyclerAdapter(ArrayList<MovieModel> movies) {
        this.movies = movies;
    }

    public interface OnItemClickListener{
        void onItemClick(MovieModel movieModel);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieModel currentMovie = movies.get(position);
        holder.title.setText(currentMovie.getTitle());
        holder.year.setText(String.valueOf(currentMovie.getYear()));
        holder.type.setText(currentMovie.getType());

        holder.itemView.setOnClickListener(view -> {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(currentMovie);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView year;
        public TextView type;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            year = view.findViewById(R.id.year);
            type = view.findViewById(R.id.type);
        }
    }
}