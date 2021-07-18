package com.prosperday.mydracma.app_FinancialHealth;

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

public class appFinancialHealthStep00Menu extends AppCompatActivity {

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
        setContentView(R.layout.app_financial_health_step00_menu);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnIncome = findViewById(R.id.btnIncome);
        Button btnOutcome = findViewById(R.id.btnOutcome);
        Button btnCashFlow = findViewById(R.id.btnCashFlow);
        Button btnRetirement = findViewById(R.id.btnRetirement);
        Button btnEmergency = findViewById(R.id.btnEmergency);
        Button btnGoal = findViewById(R.id.btnGoal);
        Button btnInsurance = findViewById(R.id.btnInsurance);
        Button btnBudget = findViewById(R.id.btnBudget);
        Button btnNetWorth = findViewById(R.id.btnNetWorth);
        Button btnNotification = findViewById(R.id.btnNotification);
        Button btnAsset = findViewById(R.id.btnAssets);
        Button btnLoan = findViewById(R.id.btnLoan);

        /* Verifying Financial Health*/
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean shr_FinancialHealthIncome = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthIncome), false);
        if (!shr_FinancialHealthIncome) {
            btnIncome.setBackgroundColor(Color.RED);
            btnIncome.setTextColor(Color.WHITE);
        } else {
            btnIncome.setBackgroundColor(Color.BLUE);
            btnIncome.setTextColor(Color.WHITE);
        }

        boolean shr_FinancialHealthOutcome = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthOutcome), false);
        if (!shr_FinancialHealthOutcome) {
            btnOutcome.setBackgroundColor(Color.RED);
            btnOutcome.setTextColor(Color.WHITE);
        } else {
            btnOutcome.setBackgroundColor(Color.BLUE);
            btnOutcome.setTextColor(Color.WHITE);
        }

        boolean shr_FinancialHealthCashFlow = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthCashFlow), false);
        if (!shr_FinancialHealthCashFlow) {
            btnCashFlow.setBackgroundColor(Color.RED);
            btnCashFlow.setTextColor(Color.WHITE);
        } else {
            btnCashFlow.setBackgroundColor(Color.BLUE);
            btnCashFlow.setTextColor(Color.WHITE);
        }

        boolean shr_FinancialHealthRetirement = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthRetirement), false);
        if (!shr_FinancialHealthRetirement) {
            btnRetirement.setBackgroundColor(Color.RED);
            btnRetirement.setTextColor(Color.WHITE);
        } else {
            btnRetirement.setBackgroundColor(Color.BLUE);
            btnRetirement.setTextColor(Color.WHITE);
        }

        boolean shr_FinancialHealthEmergency = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthEmergency), false);
        if (!shr_FinancialHealthEmergency) {
            btnEmergency.setBackgroundColor(Color.RED);
            btnEmergency.setTextColor(Color.WHITE);
        } else {
            btnEmergency.setBackgroundColor(Color.BLUE);
            btnEmergency.setTextColor(Color.WHITE);
        }

        boolean shr_FinancialHealthGoal = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthGoal), false);
        if (!shr_FinancialHealthGoal) {
            btnGoal.setBackgroundColor(Color.RED);
            btnGoal.setTextColor(Color.WHITE);
        } else {
            btnGoal.setBackgroundColor(Color.BLUE);
            btnGoal.setTextColor(Color.WHITE);
        }

        boolean shr_FinancialHealthInsurance = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthInsurance), false);
        if (!shr_FinancialHealthInsurance) {
            btnInsurance.setBackgroundColor(Color.RED);
            btnInsurance.setTextColor(Color.WHITE);
        } else {
            btnInsurance.setBackgroundColor(Color.BLUE);
            btnInsurance.setTextColor(Color.WHITE);
        }

        boolean shr_FinancialHealthBudget = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthBudget), false);
        if (!shr_FinancialHealthBudget) {
            btnBudget.setBackgroundColor(Color.RED);
            btnBudget.setTextColor(Color.WHITE);
        } else {
            btnBudget.setBackgroundColor(Color.BLUE);
            btnBudget.setTextColor(Color.WHITE);
        }

        boolean shr_FinancialHealthNetWorth = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthNetWorth), false);
        if (!shr_FinancialHealthNetWorth) {
            btnNetWorth.setBackgroundColor(Color.RED);
            btnNetWorth.setTextColor(Color.WHITE);
        } else {
            btnNetWorth.setBackgroundColor(Color.BLUE);
            btnNetWorth.setTextColor(Color.WHITE);
        }

        boolean shr_FinancialHealthNotification = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthNotification), false);
        if (!shr_FinancialHealthNotification) {
            btnNotification.setBackgroundColor(Color.RED);
            btnNotification.setTextColor(Color.WHITE);
        } else {
            btnNotification.setBackgroundColor(Color.BLUE);
            btnNotification.setTextColor(Color.WHITE);
        }

        boolean shr_FinancialHealthAsset = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthAsset), false);
        if (!shr_FinancialHealthAsset) {
            btnAsset.setBackgroundColor(Color.RED);
            btnAsset.setTextColor(Color.WHITE);
        } else {
            btnAsset.setBackgroundColor(Color.BLUE);
            btnAsset.setTextColor(Color.WHITE);
        }

        boolean shr_FinancialHealthLoan = sharedPreferences.getBoolean(getString(R.string.shr_FinancialHealthLoan), false);
        if (!shr_FinancialHealthLoan) {
            btnLoan.setBackgroundColor(Color.RED);
            btnLoan.setTextColor(Color.WHITE);
        } else {
            btnLoan.setBackgroundColor(Color.BLUE);
            btnLoan.setTextColor(Color.WHITE);
        }

        // Treat button
        btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
            }
        });

        // Treat button
        btnOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
            }
        });

        // Treat button
        btnCashFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
            }
        });

        // Treat button
        btnRetirement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
            }
        });

        // Treat button
        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
            }
        });

        // Treat button
        btnGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
            }
        });

        // Treat button
        btnInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
            }
        });

        // Treat button
        btnBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
                finish();
            }
        });

        // Treat button
        btnNetWorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
            }
        });

        // Treat button
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
            }
        });

        // Treat button
        btnAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
            }
        });

        // Treat button
        btnLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
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
        currentContext = appFinancialHealthStep00Menu.this;
        new appSupportMenuGeneral(item);
        return super.onOptionsItemSelected(item);
    }
}
