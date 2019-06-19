package com.example.sarath.cseiimdpjntuk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SubjectsRecyclerAdapter extends RecyclerView.Adapter<SubjectsRecyclerAdapter.SubjectsRecyclerViewholder> {
    Context context;
    List<Subjects_files_upload> mfiles;
    public  SubjectsRecyclerAdapter(Context context, List<Subjects_files_upload> mfiles)
    {
        this.context = context;
        this.mfiles = mfiles;
    }
    @NonNull
    @Override
    public SubjectsRecyclerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.umlfiles_recyclerview_xml,parent,false);
        return new SubjectsRecyclerViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubjectsRecyclerViewholder holder, int position) {
    final Subjects_files_upload subjects_files_upload = mfiles.get(position);
    holder.name_of_filee.setText(subjects_files_upload.getName_of_file());
    holder.download_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "file will be download "+subjects_files_upload.getRandom_name(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setType(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(subjects_files_upload.getUri()));
            context.startActivity(intent);
        }
    });
    }


    @Override
    public int getItemCount() {
        return mfiles.size();
    }
    public class SubjectsRecyclerViewholder extends RecyclerView.ViewHolder{
        TextView name_of_filee;
        ImageView download_button;
        public SubjectsRecyclerViewholder(View itemView) {
            super(itemView);
            name_of_filee = (TextView) itemView.findViewById(R.id.name_of_filexml);
            download_button = (ImageView) itemView.findViewById(R.id.download_button_for_file);
        }
    }
}
