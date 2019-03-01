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

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnComplete{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.numbers);
        Button clear = (Button) findViewById(R.id.clear);
        Button get = (Button) findViewById(R.id.get);
        Button refresh = (Button) findViewById(R.id.refresh);
        final MessageController messageController = new MessageController(this,linearLayout,this);


//        clear.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                messageController.clear();
//                SystemClock.sleep(400);
//                linearLayout.removeAllViews();
//            }
//        });



        get.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                messageController.fetch(false);

            }
        });

        refresh.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                messageController.fetch(true);


            }
        });






//        for (int i = 0; i <10 ; i++) {
//            arr.add(Integer.valueOf(i));
//        }
//

//
//        StorageManager storageManager = new StorageManager(this);
//        ArrayList arr;
//
//
//        arr= storageManager.load();
//        SystemClock.sleep(300);
//        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i));
//        }
//        storageManager.save(15);
//        SystemClock.sleep(300);
//        arr=storageManager.load();
//        SystemClock.sleep(300);
//
//        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i));
//        }
//        storageManager.save(15);

//        StorageManager storageManager = new StorageManager();
//        storageManager.save(10);
    }

    @Override
    public void show(final MessageController messageController, final LinearLayout linearLayout) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < messageController.array.size(); i++) {
                    TextView tv = new TextView(getApplicationContext()); // Prepare textview object programmatically
                    tv.setText(Integer.toString(messageController.array.get(i)));
                    tv.setId(i);
                    linearLayout.addView(tv); // Add to your ViewGroup using this method
                }
            }
        });

    }
}
