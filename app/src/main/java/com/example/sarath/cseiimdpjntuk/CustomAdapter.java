package com.example.sarath.cseiimdpjntuk;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList<String> personNames,roll_no;
    Context context;
    public CustomAdapter(Context context, ArrayList<String> personNames, ArrayList<String> roll_no) {
        this.context = context;
        this.personNames = personNames;
        this.roll_no = roll_no;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friendsrecycler, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        holder.name.setText(personNames.get(position));
        holder.roll.setText(roll_no.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "name is "+personNames.get(position)+" roll number is "+roll_no.get(position), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context,Details.class);
                i.putExtra("roll",roll_no.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return roll_no.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,roll;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.nameStu);
            roll = (TextView) itemView.findViewById(R.id.rollStu);
        }
    }
}
