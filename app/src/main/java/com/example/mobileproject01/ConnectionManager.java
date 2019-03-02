package com.example.mobileproject01;

import android.widget.LinearLayout;

import java.util.ArrayList;

public class ConnectionManager {
    OnComplete onComplete;
    MessageController messageController;
    LinearLayout linearLayout;

    ConnectionManager(OnComplete onComplete,MessageController messageController, LinearLayout linearLayout){
        this.onComplete = onComplete;
        this.messageController=messageController;
        this.linearLayout=linearLayout;
    }
    Worker cloud = new Worker("ConnectionManager Thread");


    ArrayList<Integer> load(final int n){
        final ArrayList<Integer> loadArr=new ArrayList<Integer>();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = n+1; i <= n+10; i++)
                    loadArr.add(Integer.valueOf(i));

                onComplete.show(messageController,linearLayout);


            }
    };
        cloud.postRunnable(task,100);

        return loadArr;
}}
