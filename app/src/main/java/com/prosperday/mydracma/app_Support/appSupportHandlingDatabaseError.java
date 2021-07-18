package com.prosperday.mydracma.app_Support;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DatabaseError;

import java.util.Calendar;

public class appSupportHandlingDatabaseError {

    public static Context currentContext;

    public appSupportHandlingDatabaseError(String stringActivity, DatabaseError databaseError) {
        Log.e("MyDracma Error "," Error date/time: " + Calendar.getInstance());
        Log.e("MyDracma Error "," Error activity: " + stringActivity);
        Log.e("MyDracma Error "," Error code: " + databaseError.getCode());
        Log.e("MyDracma Error "," Error details: " + databaseError.getDetails());
        Log.e("MyDracma Error "," Error message: " + databaseError.getMessage());
    }
}
