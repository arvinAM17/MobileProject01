package com.example.mobileproject01;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class StorageManager {

    Storage storage = new Storage("StorageManager Thread");
    Context context;

    StorageManager(Context context){
        this.context = context;
    }
    void save(final int n){


        storage.postRunnable(new Runnable() {
            @Override
            public void run() {

                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("loadNumber.txt", Context.MODE_PRIVATE));

                    if(n==0)
                        outputStreamWriter.write("");
                    else
                        outputStreamWriter.write(Integer.toString(n));
                    outputStreamWriter.close();
                }
                catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
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




            }
        });

        return loadArr;
    }
}
