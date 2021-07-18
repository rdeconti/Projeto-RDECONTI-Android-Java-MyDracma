package com.prosperday.mydracma.app_VirtualAssistant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class appVirtualAssistantStep00Menu extends AppCompatActivity {

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
        setContentView(R.layout.app_virtual_assistant_step00_menu);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnUser = findViewById(R.id.btnUser);
        Button btnAsset = findViewById(R.id.btnAsset);
        Button btnAccount = findViewById(R.id.btnAccount);
        Button btnIncome = findViewById(R.id.btnIncome);
        Button btnOutcome = findViewById(R.id.btnOutcome);
        Button btnGoal = findViewById(R.id.btnGoal);

        /* Verifying the Agreements to be completed by users */
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean shr_UserAgreement01 = sharedPreferences.getBoolean(getString(R.string.shr_UserAgreement01), false);
        if (!shr_UserAgreement01) {
            btnUser.setBackgroundColor(Color.RED);
            btnUser.setTextColor(Color.WHITE);
        } else {
            btnUser.setBackgroundColor(Color.BLUE);
            btnUser.setTextColor(Color.WHITE);
        }

        boolean shr_UserAgreement02 = sharedPreferences.getBoolean(getString(R.string.shr_UserAgreement02), false);
        if (!shr_UserAgreement02) {
            btnAsset.setBackgroundColor(Color.RED);
            btnAsset.setTextColor(Color.WHITE);
        } else {
            btnAsset.setBackgroundColor(Color.BLUE);
            btnAsset.setTextColor(Color.WHITE);
        }

        boolean shr_UserAgreement03 = sharedPreferences.getBoolean(getString(R.string.shr_UserAgreement03), false);
        if (!shr_UserAgreement03) {
            btnAccount.setBackgroundColor(Color.RED);
            btnAccount.setTextColor(Color.WHITE);
        } else {
            btnAccount.setBackgroundColor(Color.BLUE);
            btnAccount.setTextColor(Color.WHITE);
        }

        boolean shr_UserAgreement04 = sharedPreferences.getBoolean(getString(R.string.shr_UserAgreement03), false);
        if (!shr_UserAgreement04) {
            btnIncome.setBackgroundColor(Color.RED);
            btnIncome.setTextColor(Color.WHITE);
        } else {
            btnIncome.setBackgroundColor(Color.BLUE);
            btnIncome.setTextColor(Color.WHITE);
        }

        boolean shr_UserAgreement05 = sharedPreferences.getBoolean(getString(R.string.shr_UserAgreement03), false);
        if (!shr_UserAgreement05) {
            btnOutcome.setBackgroundColor(Color.RED);
            btnOutcome.setTextColor(Color.WHITE);
        } else {
            btnOutcome.setBackgroundColor(Color.BLUE);
            btnOutcome.setTextColor(Color.WHITE);
        }

        boolean shr_UserAgreement06 = sharedPreferences.getBoolean(getString(R.string.shr_UserAgreement03), false);
        if (!shr_UserAgreement06) {
            btnGoal.setBackgroundColor(Color.RED);
            btnGoal.setTextColor(Color.WHITE);
        } else {
            btnGoal.setBackgroundColor(Color.BLUE);
            btnGoal.setTextColor(Color.WHITE);
        }

        // Treat button
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appVirtualAssistantStep01User.class));
            }
        });

        // Treat button
        btnAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appVirtualAssistantStep02Asset.class));
            }
        });

        // Treat button
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appVirtualAssistantStep03Account.class));
            }
        });

        // Treat button
        btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appVirtualAssistantStep04Income.class));
            }
        });

        // Treat button
        btnOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appVirtualAssistantStep05Outcome.class));
            }
        });

        // Treat button
        btnGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appVirtualAssistantStep06Goal.class));
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
