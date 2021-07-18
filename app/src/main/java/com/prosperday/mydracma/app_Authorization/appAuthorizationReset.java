package com.prosperday.mydracma.app_Authorization;
// ---------------------------------------------------------------
// Created by Rosemeire Deconti
// Created on 05/01/2018
// Created at 15:02
// Package is com.prosperday.mydracma
// Project is MyDracma
// ----------------------------------------------------------------

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization;

public class appAuthorizationReset extends AppCompatActivity {

    private EditText inputEmail;
    private FirebaseAuth authorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Verifying if user is logged on app */
        boolean returnCode = appFirebaseCheckAuthorization.NotNull();

        /* False = User not logged in app, ask by LogIn */
        if (!returnCode) {
            Toast.makeText(getApplicationContext(), R.string.msg_user_please_login, Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), appAuthorizationLogIn.class));
            finish();
        }

        // Starts Firebase
        authorization = FirebaseAuth.getInstance();

        /* Set Layout */
        setContentView(R.layout.app_authorization_reset);

        /* Set Fields */
        inputEmail = findViewById(R.id.email);

        /* Set button */
        Button btnBack = findViewById(R.id.btnDelete);
        Button btnReset = findViewById(R.id.btnReset);

        /* Set progress bar */
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        /* Treat Delete */
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Treat Reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_user_email, Toast.LENGTH_LONG).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Reset
                authorization.sendPasswordResetEmail(email.trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    Toast.makeText(getApplicationContext(), R.string.msg_user_reset_success, Toast.LENGTH_LONG).show();
                                    authorization.signOut();
                                    progressBar.setVisibility(View.INVISIBLE);
                                    startActivity(new Intent(getApplicationContext(), appAuthorizationLogIn.class));
                                    finish();

                                } else {

                                    Toast.makeText(getApplicationContext(), R.string.msg_user_reset_failed, Toast.LENGTH_LONG).show();
                                    finish();

                                }
                            }
                        });
            }
        });
    }
}
