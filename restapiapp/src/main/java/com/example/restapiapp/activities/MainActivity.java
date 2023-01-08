package com.example.restapiapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.restapiapp.R;
import com.example.restapiapp.adapters.CustumAdapter;
import com.example.restapiapp.models.State;
import com.example.restapiapp.services.DataService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataService ds = new DataService();
        ArrayList<State> arr = ds.getStateArr();

        CustumAdapter ca = new CustumAdapter(arr);
        RecyclerView recyclerView = (RecyclerView)  findViewById(R.id.appRecyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(lm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(ca);
    }
}