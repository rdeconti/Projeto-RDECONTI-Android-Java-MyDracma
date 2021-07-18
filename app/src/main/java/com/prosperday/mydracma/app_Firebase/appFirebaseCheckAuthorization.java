package com.prosperday.mydracma.app_Firebase;
// ---------------------------------------------------------------
// Created by Rosemeire Deconti
// Created on 15/03/2018
// Created at 15:02
// Package is com.prosperday.mydracma
// Project is MyDracma
// ----------------------------------------------------------------

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.prosperday.mydracma.app_Support.appSupportHandlingExceptionError;
import com.prosperday.mydracma.app_User.appUserFirebaseModel;

import java.util.List;

public class appFirebaseCheckAuthorization {

    public static String userEmail;
    public static String myUserId;
    public static String myUserCode;
    public static String myUserLanguage;

    List<appUserFirebaseModel> user;

    public static boolean NotNull() {

        try {
            // Starts Firebase authorization
            FirebaseAuth authorization = FirebaseAuth.getInstance();

            // Get user data from Firebase
            FirebaseUser user = authorization.getCurrentUser();

            // User not null = User logged in app
            if (user != null) {

                userEmail = user.getEmail();
                myUserId = user.getUid();

                // TODO Get user data code and user language

                myUserCode = "USR01";
                myUserLanguage = "PT";

                return true;

            } else {

                return false;

            }

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;
        }
    }
}
