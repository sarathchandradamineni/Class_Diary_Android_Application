package com.example.sarath.cseiimdpjntuk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class alerts extends AppCompatActivity {
    RecyclerView rv;
    List<Alertobj> alertobjs;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
        rv = (RecyclerView)findViewById(R.id.alertsrecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);
        alertobjs = new ArrayList<Alertobj>();
        databaseReference = FirebaseDatabase.getInstance().getReference("classes");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Alertobj alertobj =  ds.getValue(Alertobj.class);
                    alertobjs.add(alertobj);
                }
                //TImelineRecyclerAdapter madapter = new TImelineRecyclerAdapter(getActivity().getApplicationContext(),uploadTimelineslist);
                AlertRecyclerAdap madapter = new AlertRecyclerAdap(getApplicationContext(),alertobjs);
                rv.setAdapter(madapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
