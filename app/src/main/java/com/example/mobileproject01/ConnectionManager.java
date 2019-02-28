package com.example.mobileproject01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ConnectionManager {
    Cloud cloud = new Cloud("ConnectionManager Thread");
    ConnectionManager(){

    }


    ArrayList<Integer> load(){
        cloud.postRunnable(new Runnable() {
            @Override
            public void run() {

            }
        } , 100);
        return new ArrayList<>();
    }
}
