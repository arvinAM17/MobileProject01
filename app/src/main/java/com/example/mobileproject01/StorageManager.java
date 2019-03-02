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

public class StorageManager {

    private static StorageManager SINGLE_INSTANCE = null;
    public static StorageManager getInstance(Context context, OnComplete onComplete , MessageController messageController, LinearLayout linearLayout) {
        if (SINGLE_INSTANCE == null) {
            synchronized(StorageManager.class) {
                SINGLE_INSTANCE = new StorageManager(context,onComplete,messageController,linearLayout);
            }
        }
        return SINGLE_INSTANCE;
    }

    Worker storage = new Worker("StorageManager Thread");
    Context context;
    OutputStreamWriter outputStreamWriter;
    OnComplete onComplete;
    MessageController messageController;
    LinearLayout linearLayout;
    FileOutputStream fileOutputStream;

    private StorageManager(Context context, OnComplete onComplete , MessageController messageController, LinearLayout linearLayout){
        this.context = context;
        this.onComplete = onComplete;
        this.messageController=messageController;
        this.linearLayout=linearLayout;





        try {
//            outputStreamWriter = new OutputStreamWriter(context.openFileOutput("loadNumber.txt", Context.MODE_PRIVATE));
            fileOutputStream = context.openFileOutput("loadNumber.txt",context.MODE_PRIVATE);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    void save(final int n){


        storage.postRunnable(new Runnable() {
            @Override
            public void run() {

//                try {
//
//
//                    if(n==0)
//                        outputStreamWriter.write("");
//                    else
//                        outputStreamWriter.write(Integer.toString(n));
//                    outputStreamWriter.close();
//                }
//                catch (IOException e) {
//                    Log.e("Exception", "File write failed: " + e.toString());
//                }

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                try {
                    if(n==0)
                        writer.write("");
                    else
                        writer.write(Integer.toString(n));
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }
    ArrayList<Integer> load(){
        final ArrayList<Integer> loadArr=new ArrayList<Integer>();

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


                onComplete.show(messageController,linearLayout);

            }
        });


        return loadArr;
    }
}
