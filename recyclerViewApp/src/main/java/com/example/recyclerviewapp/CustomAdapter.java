package com.example.recyclerviewapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(DataModel item);
    }

    private final ArrayList<DataModel> dataset;
    private final OnItemClickListener listener;

    public CustomAdapter(ArrayList<DataModel> dataset, OnItemClickListener listener){
        this.dataset = dataset;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewDetails = holder.textViewDetails;
        ImageView imageView = holder.imageView;
        CardView cardView = holder.cardView;

        textViewName.setText(dataset.get(position).getName());
        textViewDetails.setText(dataset.get(position).getDetails());
        Bitmap bitmap = BitmapFactory.decodeByteArray(dataset.get(position).getImage(), 0, dataset.get(position).getImage().length);
        imageView.setImageBitmap(bitmap);

        holder.bind(dataset.get(position), listener);
    }


    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textViewName;
        TextView textViewDetails;
        ImageView imageView;

        public MyViewHolder(View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewCardPage);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDetails = itemView.findViewById(R.id.textViewDetails);
            imageView = itemView.findViewById(R.id.imageViewCard);
        }
        public void bind(final DataModel item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}
