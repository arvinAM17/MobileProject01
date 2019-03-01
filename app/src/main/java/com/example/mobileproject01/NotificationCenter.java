package com.example.mobileproject01;

import java.util.ArrayList;

public class NotificationCenter {
    private Message message;
    private ArrayList<Observer> observers;

    public NotificationCenter() {
        message = new Message();
        observers = new ArrayList<Observer>();
    }

    public NotificationCenter(Message message) {
        this.message = message;
        observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer observer) {
        if(!this.observers.contains(observer))
            this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setMessage(Message message) {
        this.message = message;
        this.updateObservers();
    }

    private void updateObservers() {
        for (Observer o:
             this.observers) {
            o.update(this.message);
        }
    }
}
