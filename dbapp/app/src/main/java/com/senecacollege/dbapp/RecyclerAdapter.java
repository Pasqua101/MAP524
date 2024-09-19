package com.senecacollege.dbapp;

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

    private ArrayList<User> users;
    private OnItemClickListener onItemClickListener;


    public RecyclerAdapter(ArrayList<User> users) {
        this.users = users;
    }
    public void setData(ArrayList<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(User user);
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
        User currentUser = users.get(position);
        holder.firstName.setText(currentUser.getFirstName());
        holder.lastName.setText(String.valueOf(currentUser.getLastName()));


        holder.itemView.setOnClickListener(view -> {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(currentUser);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName;
        public TextView lastName;

        public ViewHolder(View view) {
            super(view);
            firstName = view.findViewById(R.id.firstNameTextView);
            lastName = view.findViewById(R.id.lastnameTextView);
        }
    }
}
