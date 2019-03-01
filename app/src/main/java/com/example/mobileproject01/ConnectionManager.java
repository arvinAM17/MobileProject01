package com.example.mobileproject01;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ConnectionManager {
    Cloud cloud = new Cloud("ConnectionManager Thread");


    ArrayList<Integer> load(final int n){
        final ArrayList<Integer> loadArr=new ArrayList<Integer>();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = n+1; i <= n+10; i++)
                    loadArr.add(Integer.valueOf(i));


            }
    };
        cloud.postRunnable(task,100);
        return loadArr;
}}
