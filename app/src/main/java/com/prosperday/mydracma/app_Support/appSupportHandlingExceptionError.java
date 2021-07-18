package com.prosperday.mydracma.app_Support;

import android.util.Log;

import java.util.Calendar;

public class appSupportHandlingExceptionError {

    public appSupportHandlingExceptionError(String stringActivity, Exception error) {
        Log.e("MyDracma Error "," Error date/time: " + Calendar.getInstance());
        Log.e("MyDracma Error "," Error activity: " + stringActivity);
        Log.e("MyDracma Error "," Error cause: " + error.getCause());
        Log.e("MyDracma Error "," Error message: " + error.getMessage());
        Log.e("MyDracma Error "," Error class: " + error.getClass());
    }
}
