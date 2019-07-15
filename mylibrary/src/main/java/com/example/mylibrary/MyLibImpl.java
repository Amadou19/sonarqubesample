package com.example.mylibrary;

import android.content.Context;
import android.util.Log;

public class MyLibImpl {

    private Context context;

    public  MyLibImpl(Context c) {
        context = c;
    }

    public void printLog(String message) {
        Log.d("MyLibImpl", message);
    }
}
