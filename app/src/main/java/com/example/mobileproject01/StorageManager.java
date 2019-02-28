package com.example.mobileproject01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StorageManager {
    File file;
    FileWriter fileWriter;
    Storage storage = new Storage("StorageManager Thread");
    void save(int n){
        storage.postRunnable(new Runnable() {
            @Override
            public void run() {
                file = new File("loadedNumber.txt");
                try {
                    fileWriter = new FileWriter(file);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    fileWriter.write(10);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    ArrayList<Integer> load(){

        storage.postRunnable(new Runnable() {
            @Override
            public void run() {

            }
        });

        return new ArrayList<>();
    }
}
