package com.example.mobileproject01;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.numbers1);
//        ArrayList<Integer> arr = new ArrayList<Integer>();
//        for (int i = 1; i <= 10; i++)
//            arr.add(Integer.valueOf(i));


//        TextView textView = new TextView(this);
//        textView.setText(arr.get(0));
//        linearLayout.addView(textView);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.numbers);
        ArrayList<Integer> arr;

        StorageManager storageManager = new StorageManager(this);

        arr= storageManager.load();
        SystemClock.sleep(300);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }

//        StorageManager storageManager = new StorageManager();
//        storageManager.save(10);
    }
}
