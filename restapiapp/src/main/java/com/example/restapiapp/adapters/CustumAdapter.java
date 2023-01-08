package com.example.restapiapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restapiapp.R;
import com.example.restapiapp.models.State;

import java.util.ArrayList;

public class CustumAdapter extends RecyclerView.Adapter<CustumAdapter.MyViewHolder> {

    private ArrayList<State> dataset;

    public CustumAdapter(ArrayList<State> dataset) {
        this.dataset = dataset;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewNativeName;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewNativeName = itemView.findViewById(R.id.textViewNativeName);
            imageViewIcon = itemView.findViewById(R.id.imageViewFlag);
        }
    }

    @NonNull
    @Override
    public CustumAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustumAdapter.MyViewHolder holder, int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewNativeName = holder.textViewNativeName;
        ImageView imageViewIcon = holder.imageViewIcon;

        textViewName.setText(dataset.get(position).getName());
        textViewNativeName.setText(dataset.get(position).getNativeName());
        imageViewIcon.setImageBitmap(dataset.get(position).getFlag());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
