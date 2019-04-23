package com.example.mobileproject01;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ArrayAdapter;
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

    public static MessageController getInstance(Context context) {
        if (SINGLE_INSTANCE == null) {
            synchronized (MessageController.class) {
                SINGLE_INSTANCE = new MessageController(context);
            }
        }
        return SINGLE_INSTANCE;
    }


    Context context;
    ArrayList<Comment> comments = new ArrayList<Comment>();
    ArrayList<Post> posts = new ArrayList<Post>();
    ArrayList<Integer> array = new ArrayList<Integer>();
    ConnectionManager connectionManager;
    StorageManager storageManager;

    private MessageController(Context context) {
        this.context = context;
        storageManager = StorageManager.getInstance(context);
        connectionManager = new ConnectionManager();

    }


    void fetch(Boolean fromCache,NotificationCenter notificationCenter) {

        if (fromCache) {
            array.addAll(storageManager.load(array.size()));
            notificationCenter.dataLoaded();

        } else {
            array.addAll(connectionManager.load(array.size()));







            posts.addAll(connectionManager.loadPost);











            notificationCenter.dataLoaded();
            storageManager.save(array.size());
        }

    }

    void clear() {
        array.clear();
    }

}