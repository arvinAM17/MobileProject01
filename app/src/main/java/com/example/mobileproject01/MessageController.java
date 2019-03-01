package com.example.mobileproject01;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MessageController {
    Context context;
    LinearLayout linearLayout;
    OnComplete onComplete;

    MessageController(Context context, LinearLayout linearLayout,OnComplete onComplete){
        this.context = context;
        this.linearLayout = linearLayout;
        this.onComplete = onComplete;
    }
    ArrayList<Integer> array = new ArrayList<Integer>();
    ConnectionManager connectionManager = new ConnectionManager(onComplete,this,linearLayout);
    StorageManager storageManager = new StorageManager(context,onComplete,this,linearLayout);

    void fetch(Boolean fromCache) {
        if (fromCache){
            array.addAll(storageManager.load());
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
