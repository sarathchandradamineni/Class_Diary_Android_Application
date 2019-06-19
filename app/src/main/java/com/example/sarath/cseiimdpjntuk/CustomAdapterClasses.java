package com.example.sarath.cseiimdpjntuk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class CustomAdapterClasses  extends RecyclerView.Adapter<CustomAdapterClasses.MyViewHolder>{
    Context context;
    ArrayList<String> buttons;
    public CustomAdapterClasses(Context context,ArrayList<String> buttons)
    {
        this.context = context;
        this.buttons = buttons;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.classesrecycler, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.but.setText(buttons.get(position));
        // implement setOnClickListener event on item view.
    }
    @Override
    public int getItemCount() {
        return buttons.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        Button but;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
          but = (Button) itemView.findViewById(R.id.but);
        }
    }
}
