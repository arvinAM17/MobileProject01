package com.example.mobileproject01;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class StorageManager {
    ArrayList<Integer> loadArr=new ArrayList<Integer>();

    private static StorageManager SINGLE_INSTANCE = null;
    public static StorageManager getInstance(Context context) {
        if (SINGLE_INSTANCE == null) {
            synchronized(StorageManager.class) {
                SINGLE_INSTANCE = new StorageManager(context);
            }
        }
        return SINGLE_INSTANCE;
    }

    Worker storage = new Worker("StorageManager Thread");
    Context context;
    OutputStreamWriter outputStreamWriter;
    FileOutputStream fileOutputStream;

    private StorageManager(Context context){
        this.context = context;





        try {
            fileOutputStream = context.openFileOutput("loadNumber.txt",context.MODE_PRIVATE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    void save(final int n){


        storage.postRunnable(new Runnable() {
            @Override
            public void run() {


                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                try {
                    if(n==0)
                        writer.write("");
                    else
                        writer.write(Integer.toString(n));
//                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }
    ArrayList<Integer> load(){
        final CountDownLatch countDownLatch = new CountDownLatch(1);


        storage.postRunnable(new Runnable() {
            @Override
            public void run() {

                String ret = "";

                try {
                    InputStream inputStream = context.openFileInput("loadNumber.txt");

                    if ( inputStream != null ) {
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String receiveString = "";
                        StringBuilder stringBuilder = new StringBuilder();

                        while ( (receiveString = bufferedReader.readLine()) != null ) {
                            stringBuilder.append(receiveString);
                        }

                        inputStream.close();
                        ret = stringBuilder.toString();
                    }

//                    System.out.println("in to fileeee "+ret);

                    if (ret == ""){
                        for (int i = 1; i <= 10; i++)
                            loadArr.add(Integer.valueOf(i));
                    }
                    else{
                        int n = Integer.parseInt(ret);

                        for (int i = 1; i <= n; i++)
                            loadArr.add(Integer.valueOf(i));
                    }



                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return loadArr;
    }
}
