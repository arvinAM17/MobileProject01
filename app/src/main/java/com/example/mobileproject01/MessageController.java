package com.example.mobileproject01;

import android.content.Context;
import android.os.SystemClock;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MessageController {

    NotificationCenter notificationCenter;

    private static MessageController SINGLE_INSTANCE = null;
    public static MessageController getInstance(Context context,NotificationCenter notificationCenter) {
        if (SINGLE_INSTANCE == null) {
            synchronized(MessageController.class) {
                SINGLE_INSTANCE = new MessageController(context,notificationCenter);
            }
        }
        return SINGLE_INSTANCE;
    }



    Context context;
    ArrayList<Integer> array = new ArrayList<Integer>();
    ConnectionManager connectionManager;
    StorageManager storageManager;

    private MessageController(Context context,NotificationCenter notificationCenter){
        this.notificationCenter = notificationCenter;
        this.context = context;
        storageManager =StorageManager.getInstance(context);
        connectionManager = new ConnectionManager();
    }


    void fetch(Boolean fromCache) {
        if (fromCache){
            array.addAll(storageManager.load());
            notificationCenter.dataLoaded();

            storageManager.save(array.size());
        }

        else {
            array.addAll(connectionManager.load(array.size()));


            notificationCenter.dataLoaded();
            storageManager.save(array.size());
        }

    }

    void clear(){
        array.clear();
    }

}
