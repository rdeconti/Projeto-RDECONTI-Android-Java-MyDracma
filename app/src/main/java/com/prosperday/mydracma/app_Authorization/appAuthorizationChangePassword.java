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
import com.google.firebase.auth.FirebaseUser;
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization;

public class appAuthorizationChangePassword extends AppCompatActivity {

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

        // Set Firebase
        authorization = FirebaseAuth.getInstance();
        final FirebaseUser user = authorization.getCurrentUser();

        /* Set Layout */
        setContentView(R.layout.app_authorization_change_password);

        /* Set button */
        Button btnChange = findViewById(R.id.btnChange);
        Button btnBack = findViewById(R.id.btnDelete);

        /* Set Fields */
        final EditText inputOldPassword = findViewById(R.id.old_password);
        final EditText inputNewPassword = findViewById(R.id.new_password);

        /* Set progress bar */
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        // Change email
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Verification: old email and new email
                String OldPassword = inputOldPassword.getText().toString();
                String NewPassword = inputNewPassword.getText().toString();

                if (TextUtils.isEmpty(OldPassword)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_user_changePassword_old_empty, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(NewPassword)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_user_changePassword_new_empty, Toast.LENGTH_LONG).show();
                    return;
                }

                if (NewPassword.equals(OldPassword)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_user_changePassword_different, Toast.LENGTH_LONG).show();
                    return;
                }

                if (NewPassword.length() < 6) {
                    Toast.makeText(getApplicationContext(), R.string.msg_password_short, Toast.LENGTH_LONG).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                user.updatePassword(NewPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), R.string.msg_user_changePassword_success, Toast.LENGTH_LONG).show();
                            authorization.signOut();
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext(), appAuthorizationLogIn.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.msg_user_changePassword_failed, Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        /* Treat Delete */
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
