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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.prosperday.mydracma.R;

public class appAuthorizationSignIn extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPassword;
    private FirebaseAuth authorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start Firebase authentication
        authorization = FirebaseAuth.getInstance();

        /* Set Layout */
        setContentView(R.layout.app_authorization_signin);

        /* Set Fields */
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);

        /* Set button */
        Button btnLogIn = findViewById(R.id.btnLogIn);
        Button btnSignUp = findViewById(R.id.btnSignIn);
        Button btnReset = findViewById(R.id.btnReset);

        /* Set progress bar */
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        // Treat Reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appAuthorizationReset.class));
                finish();
            }
        });

        // Treat LogIn
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appAuthorizationLogIn.class));
                finish();
            }
        });

        // Treat SignUp
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verifying: email and password
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // TODO: VERIFY IF EMAIL IS VALID
                // TODO: VERIFY IF EMAIL EXISTS

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_user_email, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_user_password, Toast.LENGTH_LONG).show();
                    return;
                }
                // TODO: VERIFY STRONG PASSWORD
                // TODO: VERIFY OTHER RULES
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), R.string.msg_password_short, Toast.LENGTH_LONG).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Start creation of user
                authorization.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(appAuthorizationSignIn.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {

                                    Toast.makeText(getApplicationContext(), getString(R.string.msg_user_signIn_failed) + task.getException(),
                                            Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), getString(R.string.msg_user_signIn_success) + task.isSuccessful(), Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                    startActivity(new Intent(getApplicationContext(), appAuthorizationLogIn.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }

}
