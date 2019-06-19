package com.example.sarath.cseiimdpjntuk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Details extends AppCompatActivity {
    ArrayList<String> details;
    //TextView tv;
    TextView nameDet,emailDet,phoneDet,dobDet,speciDet;
    ImageView profilepic;
    DatabaseReference df;
    //ListView det;
    //ArrayAdapter<String> adap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i  = getIntent();
        String rollno = i.getStringExtra("roll");
        nameDet = (TextView) findViewById(R.id.nameDet);
        profilepic = (ImageView) findViewById(R.id.profilepic);
        emailDet = (TextView) findViewById(R.id.emailDet);
        phoneDet = (TextView) findViewById(R.id.phoneDet);
        dobDet = (TextView) findViewById(R.id.dobDet);
        speciDet = (TextView) findViewById(R.id.speciDet);
        //tv = (TextView) findViewById(R.id.testroll);
        //tv.setText(rollno);
        //det = (ListView)findViewById(R.id.listdetails);
        details = new ArrayList<String>();
        //adap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,details);
        //det.setAdapter(adap);
        df = FirebaseDatabase.getInstance().getReference("users/"+rollno);
        df.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String detailsstring = dataSnapshot.getValue(String.class);
                if(dataSnapshot.getKey().equals("name"))
                {
                    nameDet.setText("NAME : "+detailsstring);
                }
                else if(dataSnapshot.getKey().equals("phone number"))
                {
                    phoneDet.setText("PHONE : "+detailsstring);
                }
                else if(dataSnapshot.getKey().equals("date of birth"))
                {
                    dobDet.setText("DATE OF BIRTH : "+detailsstring);
                }
                else if(dataSnapshot.getKey().equals("email"))
                { emailDet.setText("EMAIL : "+detailsstring);
                }
                else if(dataSnapshot.getKey().equals("sepcialization"))
                {
                    speciDet.setText("SPECIALIZATION : "+detailsstring);
                }
                else if(dataSnapshot.getKey().equals("image_download_url"))
                {
                    Picasso.with(getApplicationContext()).load(detailsstring).fit().centerCrop().into(profilepic);
                }
                details.add(detailsstring);
                //adap.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       /* nameDet.setText(details.get(2));
        phoneDet.setText(details.get(3));
        emailDet.setText(details.get(1));
        dobDet.setText(details.get(0));
        speciDet.setText(details.get(4));*/

    }
}
