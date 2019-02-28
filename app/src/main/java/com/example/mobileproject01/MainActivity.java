package com.example.mobileproject01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.numbers);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++)
            arr.add(Integer.valueOf(i));
        for (int i = 0; i < 8; i++) {
            TextView textView = new TextView(this);
            textView.setText(i + ". " + arr.get(i).toString());
            linearLayout.addView(textView);
        }

//        StorageManager storageManager = new StorageManager();
//        storageManager.save(10);
    }
}
