package com.example.inventory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private static final String TAG = "tag";
    private ArrayList<String> mDescription;
    private ArrayList<String> mLocation;
    private ArrayList<String> mQuantity;
    private Context mContext;

    private OnItemListener mOnItemListener;


    public RecyclerAdapter(ArrayList<String> mDescription, ArrayList<String> mLocation, ArrayList<String> mQuantity, Context mContext, OnItemListener onItemListener) {
        Log.d(TAG, "RecyclerAdapter called");
        this.mDescription = mDescription;
        this.mLocation = mLocation;
        this.mQuantity = mQuantity;
        this.mContext = mContext;

        this.mOnItemListener = onItemListener;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout, parent, false);
        ViewHolder holder = new ViewHolder(view, mOnItemListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: called");

        holder.description.setText(mDescription.get(position));
        holder.location.setText(mLocation.get(position));
        holder.quantity.setText(mQuantity.get(position));

    }

    @Override
    public int getItemCount() {
        return mDescription.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView description;
        TextView location;
        TextView quantity;
        RelativeLayout parentLayout;
        OnItemListener onItemListener;


        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            description = itemView.findViewById(R.id.tvItemDescription);
            location = itemView.findViewById(R.id.tvItemLocation);
            quantity = itemView.findViewById(R.id.tvItemQuantity);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition());
        }

    }

    public interface OnItemListener{
        void onItemClick(int position);
    }

}
