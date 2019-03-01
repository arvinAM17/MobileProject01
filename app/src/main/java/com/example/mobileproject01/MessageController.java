package com.example.mobileproject01;

import android.content.Context;

import java.util.ArrayList;

public class MessageController {
    Context context;

    MessageController(Context context){
        this.context = context;
    }
    ArrayList<Integer> array = new ArrayList<Integer>();
    ConnectionManager connectionManager = new ConnectionManager();
    StorageManager storageManager = new StorageManager(context);

    private void fetch(Boolean fromCache) {
        if (fromCache)
            array.addAll(storageManager.load());
        else {
            array.addAll(connectionManager.load(array.size()));
            storageManager.save(array.size());
        }

        //TODO bepors ferestadano
    }

}
