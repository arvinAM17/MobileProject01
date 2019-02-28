package com.example.mobileproject01;

import java.util.ArrayList;

public class MessageController {
    ArrayList<Integer> array = new ArrayList<Integer>();
    ConnectionManager connectionManager = new ConnectionManager();
    StorageManager storageManager = new StorageManager();

    private void fetch(Boolean fromCache) {
        if (fromCache)
            array.addAll(storageManager.load());
        else {
            array.addAll(connectionManager.load());
            storageManager.save(array.get(array.size() - 1));
        }

        //TODO bepors ferestadano
    }

}
