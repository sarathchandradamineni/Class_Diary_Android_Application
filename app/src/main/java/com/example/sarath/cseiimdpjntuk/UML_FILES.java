package com.example.sarath.cseiimdpjntuk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class UML_FILES extends AppCompatActivity {
    RecyclerView rv;
    List<Subjects_files_upload> files;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uml__files);
        rv = (RecyclerView)findViewById(R.id.umlfiles_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        files = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("subjects/UML and DP");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Subjects_files_upload subjects_files_upload =  ds.getValue(Subjects_files_upload.class);
                    files.add(subjects_files_upload);
                }
                SubjectsRecyclerAdapter subjectsRecyclerAdapter = new SubjectsRecyclerAdapter(getApplicationContext(),files);
                rv.setAdapter(subjectsRecyclerAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
