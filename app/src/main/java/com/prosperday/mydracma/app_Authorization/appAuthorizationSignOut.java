package com.prosperday.mydracma.app_Authorization;
// ---------------------------------------------------------------
// Created by Rosemeire Deconti
// Created on 05/01/2018
// Created at 15:02
// Package is com.prosperday.mydracma
// Project is MyDracma
// ----------------------------------------------------------------

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_Configuration.appConfigurationLimit;
import com.prosperday.mydracma.app_Configuration.appConfigurationNotification;
import com.prosperday.mydracma.app_FinancialHealth.appFinancialHealthStep00Menu;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization;
import com.prosperday.mydracma.app_User.appUserScreenUpdateDelete;

public class appAuthorizationSignOut extends AppCompatActivity {

    @SuppressLint("ShowToast")
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
        FirebaseAuth authorization = FirebaseAuth.getInstance();
        final FirebaseUser user = authorization.getCurrentUser();

        /* Set Layout */
        setContentView(R.layout.app_authorization_singout);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnSigOut = findViewById(R.id.btnChange);
        Button btnBack = findViewById(R.id.btnDelete);

        /* Set progress bar */
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        // Change email
        btnSigOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user != null) {

                    progressBar.setVisibility(View.VISIBLE);

                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), getString(R.string.msg_user_signOut_success), Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), appAuthorizationSignIn.class));
                                        progressBar.setVisibility(View.INVISIBLE);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), getString(R.string.msg_user_signOut_failed), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /* Inflate menu toolbar */
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_navigation_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /* Treat options from action bar and menu */
        switch (item.getItemId()) {

            /* Treat toolbar: Icons */
            case R.id.actionHome:
                startActivity(new Intent(getApplicationContext(), appFinancialHealthStep00Menu.class));
                break;
            case R.id.actionLogout:
                startActivity(new Intent(getApplicationContext(), appAuthorizationLogOut.class));
                break;
            case R.id.actionNotification:
                // TODO: develop alerts
                Toast.makeText(getApplicationContext(), R.string.msg_toBeDeveloped, Toast.LENGTH_LONG).show();
                break;

            // Treat toolbar: User
            case R.id.actionUserChangeEmail:
                startActivity(new Intent(getApplicationContext(), appAuthorizationChangeEmail.class));
                break;
            case R.id.actionUserChangePassword:
                startActivity(new Intent(getApplicationContext(), appAuthorizationChangePassword.class));
                break;
            case R.id.actionUserLimit:
                startActivity(new Intent(getApplicationContext(), appConfigurationLimit.class));
                break;
            case R.id.actionUserNotification:
                startActivity(new Intent(getApplicationContext(), appConfigurationNotification.class));
                break;
            case R.id.actionUserReset:
                startActivity(new Intent(getApplicationContext(), appAuthorizationReset.class));
                break;
            case R.id.actionUserSignOut:
                startActivity(new Intent(getApplicationContext(), appAuthorizationSignOut.class));
                break;
            case R.id.actionUserMaintain:
                startActivity(new Intent(getApplicationContext(), appUserScreenUpdateDelete.class));
                break;

            /* Treat toolbar: option not treated yet */
            default:
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
                break;
        }

        /* End toolbar treatment */
        return super.onOptionsItemSelected(item);
    }
}
