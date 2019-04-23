package com.example.mobileproject01;

import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

public class ConnectionManager {
    Worker cloud = new Worker("ConnectionManager Thread");
    ArrayList<Integer> loadArr = new ArrayList<Integer>();
    ArrayList<Comment> loadCom = new ArrayList<Comment>();
    ArrayList<Post> loadPost = new ArrayList<Post>();

    ConnectionManager() {

    }


    ArrayList<Integer> load(final int n) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);


        Runnable task = new Runnable() {
            @Override
            public void run() {
                loadArr.clear();
                for (int i = n + 1; i <= n + 10; i++)
                    loadArr.add(Integer.valueOf(i));

                countDownLatch.countDown();


            }

        };
        cloud.postRunnable(task, 100);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return loadArr;


    }

    ArrayList<Post> lostPosts() {
        final URL[] url = new URL[1];
        final URLConnection[] urlConnection = new URLConnection[1];
        final BufferedReader[] bufferedReader = new BufferedReader[1];
        final InputStreamReader[] inputStreamReader = new InputStreamReader[1];


        final CountDownLatch countDownLatch = new CountDownLatch(1);


        Runnable task = new Runnable() {
            @Override
            public void run() {
                loadPost.clear();
                String content = null;
                try {
                    content = readStringFromURL("https://jsonplaceholder.typicode.com/posts");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String[] posts = content.split(Pattern.quote("},"));


                Post post = new Post();

                for (int i = 0; i < posts.length; i++) {
                    String[] temp = posts[i].split(Pattern.quote(","));
                    post.setUserId( Integer.parseInt(temp[0].split(Pattern.quote(": "))[1]));

                    post.setId( Integer.parseInt(temp[1].split(Pattern.quote(": "))[1]));

                    String str = temp[2].split(Pattern.quote(": \""))[1];
//                    str = str.split()
                    post.setTitle(str);


                    str = temp[3].split(Pattern.quote(": \""))[1];
                    str.replace('\"',' ');
                    post.setBody(str);
                    System.out.println(post.getTitle());
                    loadPost.add(post);
                    post = new Post();
                }


                countDownLatch.countDown();


            }

        };
        cloud.postRunnable(task);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (bufferedReader[0] != null) {
            try {
                bufferedReader[0].close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loadPost;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String readStringFromURL(String requestURL) throws IOException {
        try (Scanner scanner = new Scanner(new URL(requestURL).openStream(),
                StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

}


