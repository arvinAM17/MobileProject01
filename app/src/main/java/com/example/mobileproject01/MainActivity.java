package com.example.mobileproject01;

import android.content.Context;
import android.content.res.Configuration;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Observer {
    LinearLayout linearLayout;

    NotificationCenter notificationCenter=new NotificationCenter();
    MessageController messageController =MessageController.getInstance(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        linearLayout = (LinearLayout) findViewById(R.id.numbers);
        Button clear = (Button) findViewById(R.id.clear);
        Button get = (Button) findViewById(R.id.get);
        Button refresh = (Button) findViewById(R.id.refresh);
        notificationCenter.register(MainActivity.this);


















        notificationCenter.dataLoaded();


        clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                messageController.clear();
                linearLayout.removeAllViews();
            }
        });


        get.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                messageController.fetch(false,notificationCenter);


            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                messageController.fetch(true,notificationCenter);



            }
        });


    }


    @Override
    public void update() {
        System.out.println(messageController.connectionManager.lostPosts().get(0));

        linearLayout.removeAllViews();
        for (int i = 0; i < messageController.array.size(); i++) {
            TextView tv = new TextView(getApplicationContext()); // Prepare textview object programmatically
            tv.setText(Integer.toString(messageController.array.get(i)));
            tv.setId(i);
            linearLayout.addView(tv); // Add to ViewGroup using this method
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationCenter.unregister(this);

    }


}
