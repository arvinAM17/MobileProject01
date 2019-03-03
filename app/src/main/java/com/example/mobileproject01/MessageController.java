package com.example.mobileproject01;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MessageController {

    NotificationCenter notificationCenter;

    private static MessageController SINGLE_INSTANCE = null;

    public static MessageController getInstance(Context context, NotificationCenter notificationCenter) {
        if (SINGLE_INSTANCE == null) {
            synchronized (MessageController.class) {
                SINGLE_INSTANCE = new MessageController(context, notificationCenter);
            }
        }
        return SINGLE_INSTANCE;
    }


    Context context;
    ArrayList<Integer> array = new ArrayList<Integer>();
    ConnectionManager connectionManager;
    StorageManager storageManager;

    private MessageController(Context context, NotificationCenter notificationCenter) {
        this.notificationCenter = notificationCenter;
        this.context = context;
        storageManager = StorageManager.getInstance(context);
        connectionManager = new ConnectionManager();
    }


    void fetch(Boolean fromCache) {
        if (fromCache) {
//            array.clear();
            array.addAll(storageManager.load());

            notificationCenter.dataLoaded();

            storageManager.save(array.size());
            System.out.println("int too file bade load" +readFromFile(context));
        } else {
            array.addAll(connectionManager.load(array.size()));


            notificationCenter.dataLoaded();
            storageManager.save(array.size());
            System.out.println("int too file bade load" +readFromFile(context));
        }

    }

    void clear() {
        array.clear();
        storageManager.save(array.size());
    }


    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("loadNumber.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}