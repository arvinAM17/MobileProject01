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
    InputStream inputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;

    private StorageManager(Context context){
        this.context = context;

        try {

//            outputStreamWriter.write("shiiiit");
//            outputStreamWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }







    void save(final int n){
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            outputStreamWriter = new OutputStreamWriter(context.openFileOutput("loadNumber.txt", Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        storage.postRunnable(new Runnable() {
            @Override
            public void run() {



                try {
                    if(n==0){
//                        inputStream = context.openFileInput("loadNumber.txt");
//                        outputStreamWriter.write("");
                    }

                    else
                        outputStreamWriter.write(Integer.toString(n));

                    countDownLatch.countDown();




                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });





        try {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    ArrayList<Integer> load(){
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            inputStream = context.openFileInput("loadNumber.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        loadArr.clear();


        storage.postRunnable(new Runnable() {
            @Override
            public void run() {

                String ret = "";

                try {

                    if ( inputStream != null ) {

                        String receiveString = "";
                        StringBuilder stringBuilder = new StringBuilder();

                        while ( (receiveString = bufferedReader.readLine()) != null ) {
                            stringBuilder.append(receiveString);
                        }




                        ret = stringBuilder.toString();





                    }



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

        try {
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadArr;
    }
}
