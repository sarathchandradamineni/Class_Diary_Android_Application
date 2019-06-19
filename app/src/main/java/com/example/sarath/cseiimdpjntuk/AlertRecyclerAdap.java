package com.example.sarath.cseiimdpjntuk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AlertRecyclerAdap extends RecyclerView.Adapter<AlertRecyclerAdap.AlertRecyclerViewHolder> {
    public Context context;
    List<Alertobj> alertobjs;
    public AlertRecyclerAdap(Context context,List<Alertobj> alertobjs)
    {
        this.context = context;
        this.alertobjs = alertobjs;
    }
    @NonNull
    @Override
    public AlertRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.alertsitem,parent,false);
        return new AlertRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlertRecyclerViewHolder holder, int position) {
        Alertobj alertobj = alertobjs.get(position);
        holder.subname.setText(alertobj.getSubject());
        holder.subdesc.setText(alertobj.getDescription());
        holder.subdeadline.setText(alertobj.getDeadline());
    }

    @Override
    public int getItemCount() {
        return alertobjs.size();
    }

    public class AlertRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView subname,subdesc,subdeadline;
        public AlertRecyclerViewHolder(View itemView) {
            super(itemView);
            subname = (TextView)itemView.findViewById(R.id.alert_sub);
            subdesc = (TextView)itemView.findViewById(R.id.alert_description);
            subdeadline = (TextView)itemView.findViewById(R.id.alert_deadline);
        }
    }
}
