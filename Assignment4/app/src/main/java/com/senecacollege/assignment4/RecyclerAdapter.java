package com.senecacollege.assignment4;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<User> users;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public User getUserAtPosition(int position) {
        if (users != null && position >= 0 && position < users.size()) {
            return users.get(position);
        }
        else {
            return null;
        }
    }

    public void removeUserAtPosition(int position) {
        if (position >= 0 && position < users.size()) {
            users.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateUser(User updatedUser) {
        int position = users.indexOf(updatedUser);
        if (position != -1) {
            users.set(position, updatedUser);
            notifyItemChanged(position);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView phoneNumberTextView;
        private final TextView cityTextView;
        private final TextView companyNameTextView;
        private final TextView processStatusTextView;

        public ViewHolder(View view) {
            super(view);

            nameTextView = view.findViewById(R.id.name);
            phoneNumberTextView = view.findViewById(R.id.phoneNumber);
            cityTextView = view.findViewById(R.id.city);
            companyNameTextView = view.findViewById(R.id.companyName);
            processStatusTextView = view.findViewById(R.id.processStatus);

        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getPhoneNumberTextView() {
            return phoneNumberTextView;
        }

        public TextView getCityTextView() {
            return cityTextView;
        }

        public TextView getCompanyNameTextView() {
            return companyNameTextView;
        }

        public TextView getProcessStatusTextView() {
            return processStatusTextView;
        }
    }

    public RecyclerAdapter(List<User> users) {
        this.users = users;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(
                viewGroup.getContext())
                .inflate(R.layout.recycler_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        User user = users.get(position);

        viewHolder.getNameTextView().setText(user.getName());
        viewHolder.getPhoneNumberTextView().setText(user.getPhone());
        viewHolder.getCityTextView().setText(user.getAddress().getCity());
        viewHolder.getCompanyNameTextView().setText(user.getCompany().getName());
        viewHolder.getProcessStatusTextView().setText(user.getProcessStatus());

        int backgroundColour = getBackgroundColour(user.getProcessStatus());
        viewHolder.itemView.setBackgroundColor(backgroundColour);

        // Setting a click listener
        viewHolder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null){
                onItemClickListener.onItemClick(user);
            }
        });

    }

    private int getBackgroundColour(String processStatus) {
        int colour = 0; // Default colour

        switch (processStatus) {
            case "AWAITED":
                colour = Color.YELLOW;
                break;
            case "COMPLETED":
                colour = Color.GREEN;
                break;
            case "DENIED":
                colour = Color.RED;
                break;
        }
        return colour;
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setData(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }
}
