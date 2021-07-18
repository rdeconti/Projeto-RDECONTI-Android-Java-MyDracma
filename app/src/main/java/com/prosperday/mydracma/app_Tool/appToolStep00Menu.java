package com.prosperday.mydracma.app_Tool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_Authorization.appAuthorizationLogIn;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckPersistence;
import com.prosperday.mydracma.app_Support.appSupportMenuGeneral;

public class appToolStep00Menu extends AppCompatActivity {

    public static Context currentContext;

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

        /* Verifying if Firebase persistence is ON */
        if (FirebaseApp.getApps(this).isEmpty()) {
            boolean returnCode1 = appFirebaseCheckPersistence.NotNull();
            if (!returnCode1) {
                Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), appAuthorizationLogIn.class));
                finish();
            }
        }

        /* Set Layout */
        setContentView(R.layout.app_tool_step00_menu);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnReset = findViewById(R.id.btnReset);
        Button btnBackup = findViewById(R.id.btnBackup);
        Button btnRestore = findViewById(R.id.btnRestore);
        Button btnDownload = findViewById(R.id.btnDownload);
        Button btnUpload = findViewById(R.id.btnUpload);
        Button btnTemplate = findViewById(R.id.btnTemplate);

        // Treat Reset
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appToolStep01Reset.class));
            }
        });

        // Treat Backup
        btnBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appToolStep02Backup.class));
            }
        });

        // Treat Restore
        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appToolStep03Restore.class));
            }
        });

        // Treat Download
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appToolStep04Download.class));
            }
        });

        // Treat Upload
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appToolStep05Upload.class));
            }
        });

        // Treat template
        btnTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appToolStep06Template.class));
            }
        });
    }

    /* Inflate menu toolbar */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_navigation_general, menu);
        return true;
    }

    /* Treat options from action bar and menu */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new appSupportMenuGeneral(item);
        return super.onOptionsItemSelected(item);
    }
}
