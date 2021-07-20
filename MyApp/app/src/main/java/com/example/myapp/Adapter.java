package com.example.myapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private ArrayList<Item> list;

    public Adapter(ArrayList<Item> list) {
        this.list=list;
    }

    @NonNull
    @Override
    public Adapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        Holder holder = new Holder(view);


        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Holder holder, int position) {

        holder.textView.setText(list.get(position).getContent());
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.itemView.setTag(position);


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });
}

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);

       }
    public void remove(int position) {
        try {
            list.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }


    public class Holder extends RecyclerView.ViewHolder {
        protected TextView textView;
        protected ImageView imageView;
        public Holder(View view) {
            super(view);
            this.imageView=(ImageView) view.findViewById(R.id.image);
            this.textView=(TextView) view.findViewById(R.id.text);
        }
    }
}
