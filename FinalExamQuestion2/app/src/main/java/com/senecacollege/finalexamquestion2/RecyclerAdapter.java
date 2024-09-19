package com.senecacollege.finalexamquestion2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private final List<String> data = Arrays.asList("Apple", "Banana", "Cherry", "Date", "Grapes");

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String currentFruit = data.get(position);
        holder.fruitName.setText(currentFruit);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            fruitName = view.findViewById(R.id.fruitName);

        }
    }
}
