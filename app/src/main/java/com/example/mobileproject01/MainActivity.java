package com.example.mobileproject01;

import android.content.Context;
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
    MessageController messageController;
    NotificationCenter notificationCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.numbers);
        Button clear = (Button) findViewById(R.id.clear);
        Button get = (Button) findViewById(R.id.get);
        Button refresh = (Button) findViewById(R.id.refresh);

        notificationCenter = new NotificationCenter();
        messageController = MessageController.getInstance(MainActivity.this, notificationCenter);
        notificationCenter.register(this);


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
                messageController.fetch(false);

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                messageController.fetch(true);


            }
        });


    }


    @Override
    public void update() {

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
        System.out.println("on destroy" + "im dieiiiiinggggg");
        notificationCenter.unregister(this);

    }
}
