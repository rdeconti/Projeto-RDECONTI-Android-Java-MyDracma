package com.prosperday.mydracma.app_Authorization;
// ---------------------------------------------------------------
// Created by Rosemeire Deconti
// Created on 05/01/2018
// Created at 15:02
// Package is com.prosperday.mydracma
// Project is MyDracma
// ----------------------------------------------------------------

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_FinancialHealth.appFinancialHealthStep00Menu;
import com.prosperday.mydracma.app_Navigation.app_Navigation;
import com.prosperday.mydracma.app_Support.appSupportCurrentDateTime;

public class appAuthorizationLogIn extends AppCompatActivity {

    public static String userLoginDate;
    public static String userLoginTime;
    public static String userLanguage;

    private EditText inputEmail;
    private EditText inputPassword;
    private FirebaseAuth authorization;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* TODO CREATE CLASS TO RESET SHARED PREFERENCES */
        /* Verifying Terms and Conditions user agreement */
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean userAgreement = sharedPreferences.getBoolean(getString(R.string.lbl_UserAgreement), false);

        AlertDialog.Builder builder = new AlertDialog.Builder(appAuthorizationLogIn.this);

        if (!userAgreement) {
            builder.setTitle(R.string.lbl_UserAgreement);
            builder.setPositiveButton(R.string.lbl_Yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(getString(R.string.lbl_UserAgreement), true);
                    editor.apply();
                }
            });
            builder.setNegativeButton(R.string.lbl_No, null);
            builder.setMessage(R.string.txt_TermsAndConditions);
            builder.show();
        }

        /* Set Layout */
        setContentView(R.layout.app_authorization_login);

        /* Set Fields */
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);

        /* Set button */
        Button btnSignUp = findViewById(R.id.btnSignIn);
        Button btnLogIn = findViewById(R.id.btnLogIn);
        Button btnReset = findViewById(R.id.btnReset);

        /* Set progress bar */
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        // Start FireBase authentication
        authorization = FirebaseAuth.getInstance();

        // TODO: SEND WELCOME EMAIL TO USER
        // Treat LogIn
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Verification: email and password
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_user_email, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_user_password, Toast.LENGTH_LONG).show();
                    return;
                }

                if (password.length() < 6) {
                    inputPassword.setError(getString(R.string.msg_password_short));
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                authorization.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(appAuthorizationLogIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), getString(R.string.msg_user_logIn_failed), Toast.LENGTH_LONG).show();
                                    return;
                                }
                                // Set date and time of LogIn
                                new appSupportCurrentDateTime();

                                /* TODO GET DATA FROM USER (READ USER CODE FROM SCREEN */
                                userLanguage = "EN";

                                progressBar.setVisibility(View.INVISIBLE);

                                // Start APP
                                startActivity(new Intent(getApplicationContext(), app_Navigation.class));
                                finish();
                            }
                        });
            }
        });

        // Treat SignUp
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appAuthorizationSignIn.class));
                finish();
            }
        });

        // Treat Reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appAuthorizationReset.class));
                finish();
            }
        });
    }
}