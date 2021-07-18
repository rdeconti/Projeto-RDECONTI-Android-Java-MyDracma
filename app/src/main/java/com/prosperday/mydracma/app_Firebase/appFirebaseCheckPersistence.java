package com.prosperday.mydracma.app_Firebase;
// ---------------------------------------------------------------
// Created by Rosemeire Deconti
// Created on 15/03/2018
// Created at 15:02
// Package is com.prosperday.mydracma
// Project is MyDracma
// ----------------------------------------------------------------

import com.google.firebase.database.FirebaseDatabase;
import com.prosperday.mydracma.app_Support.appSupportHandlingExceptionError;

import java.util.Objects;

public class appFirebaseCheckPersistence {

    public static String myFirebasePersistence;

    public static boolean NotNull() {

        try {

            if (!Objects.equals(myFirebasePersistence, "TRUE")) {
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                myFirebasePersistence = "TRUE";
                return true;
            } else {
                return true;
            }

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;
        }
    }
}
