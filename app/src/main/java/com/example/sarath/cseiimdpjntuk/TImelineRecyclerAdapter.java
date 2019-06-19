package com.example.sarath.cseiimdpjntuk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class TImelineRecyclerAdapter extends RecyclerView.Adapter <TImelineRecyclerAdapter.TimeLineViewHolder>{
    private Context context;
    private List<UploadTimeline> muploads;
    public TImelineRecyclerAdapter(Context context,List<UploadTimeline> muploads)
    {
        this.context = context;
        this.muploads = muploads;
    }
    @NonNull
    @Override
    public TimeLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.imagerecycler_timeline,parent,false);
        return new TimeLineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeLineViewHolder holder, int position) {
        UploadTimeline uploadTimelinecurrent = muploads.get((muploads.size()-1)-position);
        holder.name_person.setText("BY "+uploadTimelinecurrent.getName_of_person());
        holder.description_of_image.setText(uploadTimelinecurrent.getImagedescription());
        Picasso.with(context).load(uploadTimelinecurrent.getImage_URL()).fit().centerCrop().into(holder.image_timeline);
    }

    @Override
    public int getItemCount() {
        //Log.i("sizeofffffff", String.valueOf(muploads.size()));
        return muploads.size();
    }

    public class TimeLineViewHolder extends RecyclerView.ViewHolder{
        public TextView name_person;
        public TextView description_of_image;
        public ImageView image_timeline;
        public TimeLineViewHolder(View itemView) {
            super(itemView);
            name_person = (TextView) itemView.findViewById(R.id.textView_by);
            description_of_image = (TextView) itemView.findViewById(R.id.textView_description);
            image_timeline = (ImageView) itemView.findViewById(R.id.image_timeline_retrieve);
        }
    }
}
