package com.example.avjindersinghsekhon.minimaltodo;

import android.app.Application;

public class MinimalToDo extends Application {

    private static MinimalToDo app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static MinimalToDo getApp(){
        return app;
    }
}
