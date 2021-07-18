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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_AccountMasterChart.appAccountMasterChartFirebaseMaintain;
import com.prosperday.mydracma.app_AccountMaster.appAccountMasterFirebaseMaintain;
import com.prosperday.mydracma.app_AccountTransaction.appAccountTransactionFirebaseMaintain;
import com.prosperday.mydracma.app_AccountTransfer.appAccountTransferFirebaseMaintain;
import com.prosperday.mydracma.app_Authorization.appAuthorizationLogIn;
import com.prosperday.mydracma.app_AccountMasterBudget.appAccountMasterBudgetFirebaseMaintain;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckPersistence;
import com.prosperday.mydracma.app_AccountMasterGoal.appAccountMasterGoalFirebaseMaintain;
import com.prosperday.mydracma.app_Support.appSupportHandlingExceptionError;
import com.prosperday.mydracma.app_Support.appSupportMenuGeneral;
import com.prosperday.mydracma.app_User.appUserFirebaseMaintain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class appToolStep01Reset extends AppCompatActivity {

    public String NodeKey;

    private static final String TAG = "ToolStep01Reset ";

    private DatabaseReference firebaseAccountMaster;
    private DatabaseReference firebaseAccountTransaction;
    private DatabaseReference firebaseAccountTransfer;
    private DatabaseReference firebaseBudget;
    private DatabaseReference firebaseGoal;
    private DatabaseReference firebaseUser;

    private String strTransaction;
    private String strType;
    private String strClass;
    private String strCategory;
    private String strSubCategory;
    private String strColor;

    private String strTransactionEN;
    private String strTypeEN;
    private String strClassEN;
    private String strCategoryEN;
    private String strSubCategoryEN;

    private String strTransactionSP;
    private String strTypeSP;
    private String strClassSP;
    private String strCategorySP;
    private String strSubCategorySP;

    private String strTransactionPT;
    private String strTypePT;
    private String strClassPT;
    private String strCategoryPT;
    private String strSubCategoryPT;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Verifying if user is logged on app */
        boolean returnCode = appFirebaseCheckAuthorization.NotNull();
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
        setContentView(R.layout.app_tool_step01_reset);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnReset = findViewById(R.id.btnReset);

        /* Set progress bar */
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        /* Treat Reset */
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.INVISIBLE);
                reset_SharedPreferences();
                reset_AccountChart();
                reset_AccountMaster();
                reset_AccountTransaction();
                reset_AccountTransfer();
                reset_Budget();
                reset_Goal();
                reset_User();
                create_AccountChartStandard();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    /* Reset Shared Preferences */
    private void reset_SharedPreferences() {

        Toast.makeText(getApplicationContext(), R.string.msg_SharedPreferencesDeleted, Toast.LENGTH_LONG).show();
    }

    /* Reset Account Chart */
    private void reset_AccountChart() {
        progressBar.setVisibility(View.VISIBLE);
        boolean returnFirebase = appAccountMasterChartFirebaseMaintain.appAccountChartFirebaseReset();
        progressBar.setVisibility(View.INVISIBLE);
        if (returnFirebase) {
            Toast.makeText(getApplicationContext(), R.string.msg_deleted, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
        }
    }

    /* Reset Account Master */
    private void reset_AccountMaster() {
        progressBar.setVisibility(View.VISIBLE);
        boolean returnFirebase = appAccountMasterFirebaseMaintain.appAccountMasterFirebaseReset();
        progressBar.setVisibility(View.INVISIBLE);
        if (returnFirebase) {
            Toast.makeText(getApplicationContext(), R.string.msg_deleted, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
        }
    }

    /* Reset Account Transaction */
    private void reset_AccountTransaction() {
        progressBar.setVisibility(View.VISIBLE);
        boolean returnFirebase = appAccountTransactionFirebaseMaintain.appAccountTransactionFirebaseReset();
        progressBar.setVisibility(View.INVISIBLE);
        if (returnFirebase) {
            Toast.makeText(getApplicationContext(), R.string.msg_deleted, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
        }
    }

    /* Reset Account Transfer */
    private void reset_AccountTransfer() {
        progressBar.setVisibility(View.VISIBLE);
        boolean returnFirebase = appAccountTransferFirebaseMaintain.appAccountTransferFirebaseReset();
        progressBar.setVisibility(View.INVISIBLE);
        if (returnFirebase) {
            Toast.makeText(getApplicationContext(), R.string.msg_deleted, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
        }
    }

    /* Reset Budget */
    private void reset_Budget() {
        progressBar.setVisibility(View.VISIBLE);
        boolean returnFirebase = appAccountMasterBudgetFirebaseMaintain.appBudgetFirebaseReset();
        progressBar.setVisibility(View.INVISIBLE);
        if (returnFirebase) {
            Toast.makeText(getApplicationContext(), R.string.msg_deleted, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
        }
    }

    /* Reset Goal */
    private void reset_Goal() {
        progressBar.setVisibility(View.VISIBLE);
        boolean returnFirebase = appAccountMasterGoalFirebaseMaintain.appGoalFirebaseReset();
        progressBar.setVisibility(View.INVISIBLE);
        if (returnFirebase) {
            Toast.makeText(getApplicationContext(), R.string.msg_deleted, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
        }
    }

    /* Reset User */
    private void reset_User() {
        progressBar.setVisibility(View.VISIBLE);
        boolean returnFirebase = appUserFirebaseMaintain.appUserFirebaseReset();
        progressBar.setVisibility(View.INVISIBLE);
        if (returnFirebase) {
            Toast.makeText(getApplicationContext(), R.string.msg_deleted, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
        }
    }

    /* Create Account Chart */
    private void create_AccountChartStandard() {

        DatabaseReference firebaseAccountChart = FirebaseDatabase.getInstance().getReference("AccountChart");

        int adjustInitialPosition = 4;
        int adjustFinalPosition = 0;

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new InputStreamReader(getAssets().open("StandardAccountChart")));
            String lineFromFile;

            while ((lineFromFile = reader.readLine()) != null) {

                /* Get Account Chart: English */
                int initialPosition = lineFromFile.indexOf("#01#") + adjustInitialPosition;
                int finalPosition = lineFromFile.indexOf("#02#") - adjustFinalPosition;
                strTransactionEN = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#02#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#03#") - adjustFinalPosition;
                strTypeEN = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#03#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#04#") - adjustFinalPosition;
                strClassEN = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#04#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#05#") - adjustFinalPosition;
                strCategoryEN = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#05#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#06#") - adjustFinalPosition;
                strSubCategoryEN = lineFromFile.substring(initialPosition, finalPosition);

                /* Get Account Chart: Portuguese */
                initialPosition = lineFromFile.indexOf("#06#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#07#") - adjustFinalPosition;
                strTransactionPT = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#07#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#08#") - adjustFinalPosition;
                strTypePT = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#08#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#09#") - adjustFinalPosition;
                strClassPT = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#09#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#10#") - adjustFinalPosition;
                strCategoryPT = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#10#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#11#") - adjustFinalPosition;
                strSubCategoryPT = lineFromFile.substring(initialPosition, finalPosition);

                /* Get Account Chart: Spanish */
                initialPosition = lineFromFile.indexOf("#11#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#12#") - adjustFinalPosition;
                strTransactionSP = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#12#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#13#") - adjustFinalPosition;
                strTypeSP = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#13#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#14#") - adjustFinalPosition;
                strClassSP = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#14#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#15#") - adjustFinalPosition;
                strCategorySP = lineFromFile.substring(initialPosition, finalPosition);

                initialPosition = lineFromFile.indexOf("#15#") + adjustInitialPosition;
                finalPosition = lineFromFile.indexOf("#16#") - adjustFinalPosition;
                strSubCategorySP = lineFromFile.substring(initialPosition, finalPosition);

                /* Register Account Chart */
                register_AccountChart();
            }

        } catch (IOException error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);

        } finally {

            if (reader != null) {

                try {

                    reader.close();

                } catch (IOException error) {

                    String ClassName = String.class.getName();
                    new appSupportHandlingExceptionError(ClassName, error);

                }
            }
        }
        Toast.makeText(getApplicationContext(), R.string.msg_AccountChartCreated, Toast.LENGTH_LONG).show();
    }

    /* Register Account Chart */
    private void register_AccountChart() {

        boolean returnFirebase = appAccountMasterChartFirebaseMaintain.appAccountChartFirebaseCreate(
                strTransactionEN,
                strTypeEN,
                strClassEN,
                strCategoryEN,
                strSubCategoryEN,
                strTransactionSP,
                strTypeSP,
                strClassSP,
                strCategorySP,
                strSubCategorySP,
                strTransactionPT,
                strTypePT,
                strClassPT,
                strCategoryPT,
                strSubCategoryPT);

        if (returnFirebase) {
            Toast.makeText(getApplicationContext(), R.string.msg_AccountChartOnCreation, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
        }
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
        Context currentContext = appToolStep01Reset.this;
        new appSupportMenuGeneral(item);
        return super.onOptionsItemSelected(item);
    }
}
