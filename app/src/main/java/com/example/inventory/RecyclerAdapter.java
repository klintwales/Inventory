package com.example.inventory;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private static final String TAG = "tag";
    private ArrayList<String> mDescription;
    private ArrayList<String> mLocation;
    private ArrayList<String> mQuantity;
    private Context mContext;
    private Integer version;

    private OnItemListener mOnItemListener;

    public RecyclerAdapter(ArrayList<String> mDescription, ArrayList<String> mLocation, ArrayList<String> mQuantity, Context mContext, OnItemListener onItemListener) {
        Log.d(TAG, "Items RecyclerAdapter called");
        version = 1;
        this.mDescription = mDescription;
        this.mLocation = mLocation;
        this.mQuantity = mQuantity;
        this.mContext = mContext;

        this.mOnItemListener = onItemListener;


    }

    public RecyclerAdapter(ArrayList<String> mLocation, Context mContext, OnItemListener onItemListener) {
        Log.d(TAG, "Locations RecyclerAdapter called");
        version = 2;
        this.mLocation = mLocation;
        Log.d(TAG, mLocation + "");
        this.mContext = mContext;

        this.mOnItemListener = onItemListener;


    }

    public RecyclerAdapter(ArrayList<String> mDescription, ArrayList<String> mQuantity, Context mContext, OnItemListener onItemListener){
        Log.d(TAG, "Items by location RecyclerAdapter called");
        version = 3;
        this.mDescription = mDescription;
        this.mQuantity = mQuantity;
        this.mContext = mContext;

        this.mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        if(version == 1) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout, parent, false);
        }
        if(version ==2){
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locations_layout, parent, false);
        }
        if(version ==3){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_by_location_layout, parent, false);
        }

        ViewHolder holder = new ViewHolder(view, mOnItemListener, version);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: called");
        Log.d(TAG, "Version: " + version);

        if(version == 1){
            Log.d(TAG, "version 1");
            holder.description.setText(mDescription.get(position));
            holder.quantity.setText(mQuantity.get(position));
            holder.location.setText(mLocation.get(position));
        }
        if(version == 2){
            Log.d(TAG, "version 2");
            holder.location.setText(mLocation.get(position));
        }
        if(version == 3){
            Log.d(TAG, "version 3");
            Log.d(TAG, "Description: " +  mDescription.get(position));
            holder.description.setText(mDescription.get(position));
            Log.d(TAG, "Quantity: " + mQuantity.get(position) + "");
            holder.quantity.setText(mQuantity.get(position));
        }


    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount version: " + version);
        if(version == 1 || version == 2) {
            Log.d(TAG, "getItemCount mLocation:  " + mLocation.size());
            return mLocation.size();
        }else{
            Log.d(TAG, "getItemCount mDescription:  " + mDescription.size());
            return mDescription.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView description;
        TextView location;
        TextView quantity;
        RelativeLayout parentLayout;
        OnItemListener onItemListener;


        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener, int version) {
            super(itemView);
            if(version == 1) {
                description = itemView.findViewById(R.id.tvItemDescription);
                location = itemView.findViewById(R.id.tvItemLocation);
                quantity = itemView.findViewById(R.id.tvItemQuantity);
            }
            if(version == 2){
                location = itemView.findViewById(R.id.tvAddItemLocation);
            }
            if(version == 3){
                description = itemView.findViewById(R.id.tvItemByLocationDescription);
                quantity = itemView.findViewById(R.id.tvItemByLocationQuantity);
            }


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
