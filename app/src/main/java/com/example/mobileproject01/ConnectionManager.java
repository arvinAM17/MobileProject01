package com.example.mobileproject01;

import android.os.SystemClock;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class ConnectionManager {
    ArrayList<Integer> loadArr=new ArrayList<Integer>();

    ConnectionManager(){

    }
    Worker cloud = new Worker("ConnectionManager Thread");


    ArrayList<Integer> load(final int n){
        final CountDownLatch countDownLatch = new CountDownLatch(1);




        Runnable task = new Runnable() {
            @Override
            public void run() {
                loadArr.clear();
                for (int i = n+1; i <= n+10; i++)
                    loadArr.add(Integer.valueOf(i));

                countDownLatch.countDown();



            }

    };
        cloud.postRunnable(task,100);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return loadArr;


}}


