package com.example.sarath.cseiimdpjntuk;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class Friends extends Fragment {
    ArrayList<String> names;
    ArrayList<String> roll_no;
    RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.friends, container, false);
        names = new ArrayList<String>(Arrays.asList("D SARATH CHANDRA", "K MURTHY NAIDU", "V VINEESHA", "M BHARGAV KRISHNA","P CHAKRAVARTHI","A SRI LEKHA","D AKHILA SAI","B PRANAVI","A V A S MANIKANTA","T SAI AJITH","s. v. s. sukesh", "T. subhash", "Vishnu vardhan reddy.A", "om sai teja chennupati", "B. Ganesh", "sri ranganath reddy sabbella", "K S S Manoj", "poolla aravind", "mahesh paruchuri", "DVSS AJAY"));
        roll_no = new ArrayList<String>(Arrays.asList("15026a0501", "15026a0502", "15026a0503", "15026a0504", "15026a0505","15026a0506","15026a0507","15026a0508","15026a0511","15026a0512", "15026a0513", "15026a0516", "15026a0518", "15026a0519", "15026a0521", "15026a0524", "15026a0525", "15026a0532", "15026a0533", "15026a0539"));
        rv = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        //layout for recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);


        //setting adapter for recycler view
        CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(), names, roll_no);
        rv.setAdapter(customAdapter);

        return rootView;
    }
}
