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

    private static StorageManager SINGLE_INSTANCE = null;
    public ArrayList<Post> posts = new ArrayList<Post>();
    public ArrayList<Comment> comments = new ArrayList<Comment>();
    public DatabaseManager dbManager;

    public static StorageManager getInstance(Context context) {
        if (SINGLE_INSTANCE == null) {
            synchronized (StorageManager.class) {
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

    private StorageManager(Context context) {
        this.context = context;
        dbManager = new DatabaseManager(this.context);

        try {

//            outputStreamWriter.write("shiiiit");
//            outputStreamWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    void savePosts(final ArrayList<Post> posts) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        storage.postRunnable(new Runnable() {
            @Override
            public void run() {
                dbManager.insertPosts(posts);
            }
        });


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    void saveComments(final ArrayList<Comment> comments) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        storage.postRunnable(new Runnable() {
            @Override
            public void run() {
                dbManager.insertComments(comments);
            }
        });


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    ArrayList<Post> loadPosts() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        storage.postRunnable(new Runnable() {
            @Override
            public void run() {
                posts = dbManager.getPosts();
                countDownLatch.countDown();

            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return posts;
    }

    ArrayList<Comment> loadComment(final int id) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        storage.postRunnable(new Runnable() {
            @Override
            public void run() {
                comments = dbManager.getComments(id);
                countDownLatch.countDown();

            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return comments;
    }
}
