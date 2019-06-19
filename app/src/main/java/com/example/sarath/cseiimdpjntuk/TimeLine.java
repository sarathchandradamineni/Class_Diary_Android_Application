package com.example.sarath.cseiimdpjntuk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.*;

public class TimeLine extends Fragment {
    TextView share_something;
    List<UploadTimeline> uploadTimelineslist;
    DatabaseReference databaseReference;
    RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.timeline, container, false);
        share_something = (TextView) rootView.findViewById(R.id.share_something);

        share_something.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(),share_image.class);
                startActivity(i);
            }
        });
        rv= (RecyclerView) rootView.findViewById(R.id.recyclerView_timeline_retrieve);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        uploadTimelineslist = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Timeline uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    UploadTimeline up =  ds.getValue(UploadTimeline.class);
                    uploadTimelineslist.add(up);
                }
                TImelineRecyclerAdapter madapter = new TImelineRecyclerAdapter(getActivity().getApplicationContext(),uploadTimelineslist);
                rv.setAdapter(madapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity().getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
