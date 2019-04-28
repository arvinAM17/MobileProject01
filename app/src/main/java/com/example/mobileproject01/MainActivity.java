package com.example.mobileproject01;


import android.content.Context;
import android.content.res.Configuration;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Observer {
    LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private boolean IsGridView = false;


    NotificationCenter notificationCenter = new NotificationCenter();
    MessageController messageController = MessageController.getInstance(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button changeView = (Button) findViewById(R.id.change_view);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(linearLayoutManager);
        changeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IsGridView = !IsGridView;
                if (IsGridView)
                    recyclerView.setLayoutManager(gridLayoutManager);
                else
                    recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

        notificationCenter.register(MainActivity.this);

        notificationCenter.dataLoaded();

    }


    @Override
    public void update() {

        linearLayout.removeAllViews();
        for (int i = 0; i < messageController.array.size(); i++) {
            TextView tv = new TextView(getApplicationContext()); // Prepare textview object programmatically
            tv.setText(Integer.toString(messageController.array.get(i)));
            tv.setId(i);
            recyclerView.addView(tv); // Add to ViewGroup using this method
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationCenter.unregister(this);

    }


}
