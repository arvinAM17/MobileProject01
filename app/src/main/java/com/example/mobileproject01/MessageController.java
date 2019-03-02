package com.example.mobileproject01;

import android.content.Context;
import android.os.SystemClock;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MessageController {

    private static MessageController SINGLE_INSTANCE = null;
    public static MessageController getInstance(Context context, LinearLayout linearLayout,OnComplete onComplete) {
        if (SINGLE_INSTANCE == null) {
            synchronized(MessageController.class) {
                SINGLE_INSTANCE = new MessageController(context,linearLayout,onComplete);
            }
        }
        return SINGLE_INSTANCE;
    }



    Context context;
    LinearLayout linearLayout;
    OnComplete onComplete;
    ArrayList<Integer> array = new ArrayList<Integer>();
    ConnectionManager connectionManager;
    StorageManager storageManager;

    private MessageController(Context context, LinearLayout linearLayout,OnComplete onComplete){
        this.context = context;
        this.linearLayout = linearLayout;
        this.onComplete = onComplete;
        storageManager =StorageManager.getInstance(context,onComplete,this,linearLayout);
        connectionManager = new ConnectionManager(onComplete,this,linearLayout);
    }


    void fetch(Boolean fromCache) {
        if (fromCache){
            array.addAll(storageManager.load());
            storageManager.save(array.size());
        }

        else {
            array.addAll(connectionManager.load(array.size()));
            storageManager.save(array.size());
        }

        //TODO bepors ferestadano
    }

    void clear(){
        array.clear();
    }

}
