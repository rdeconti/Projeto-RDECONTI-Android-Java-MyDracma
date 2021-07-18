package com.prosperday.mydracma.app_Configuration;
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
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_AccountMasterChart.appAccountMasterChartReport;
import com.prosperday.mydracma.app_AccountMaster.appAccountMasterReport;
import com.prosperday.mydracma.app_AccountTransaction.appAccountTransactionReport;
import com.prosperday.mydracma.app_AccountTransfer.appAccountTransferReport;
import com.prosperday.mydracma.app_Authorization.appAuthorizationLogIn;
import com.prosperday.mydracma.app_Authorization.appAuthorizationLogOut;
import com.prosperday.mydracma.app_AccountMasterBudget.appAccountMasterBudgetReport;
import com.prosperday.mydracma.app_FinancialHealth.appFinancialHealthStep00Menu;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckPersistence;
import com.prosperday.mydracma.app_AccountMasterGoal.appAccountMasterGoalReport;
import com.prosperday.mydracma.app_Support.appSupportHandlingDatabaseError;
import com.prosperday.mydracma.app_User.appUserFirebaseModel;
import com.prosperday.mydracma.app_User.appUserReport;

import java.util.List;

import static com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization.myUserId;

public class appConfigurationLimit extends AppCompatActivity {

    List<appUserFirebaseModel> user;
    private FirebaseAuth myAuthorization;
    private DatabaseReference myDatabaseReference;
    private EditText editText_LowerLimitEmergency;
    private EditText editText_UpperLimitEmergency;
    private EditText editText_LowerLimitRetirement;
    private EditText editText_UpperLimitRetirement;
    private EditText editText_LowerLimitIncome;
    private EditText editText_UpperLimitIncome;
    private EditText editText_LowerLimitOutcome;
    private EditText editText_UpperLimitOutcome;
    private EditText editText_LowerLimitGoal;
    private EditText editText_UpperLimitGoal;
    private EditText editText_LowerLimitBudget;
    private EditText editText_UpperLimitBudget;

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
        setContentView(R.layout.app_configuration_limit);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnInsert = findViewById(R.id.btnCreate);
        Button btnBack = findViewById(R.id.btnDelete);

        /* Set Fields */
        editText_LowerLimitEmergency = findViewById(R.id.editText_LowerLimitEmergency);
        editText_UpperLimitEmergency = findViewById(R.id.editText_UpperLimitEmergency);
        editText_LowerLimitRetirement = findViewById(R.id.editText_LowerLimitRetirement);
        editText_UpperLimitRetirement = findViewById(R.id.editText_UpperLimitRetirement);
        editText_LowerLimitIncome = findViewById(R.id.editText_LowerLimitIncome);
        editText_UpperLimitIncome = findViewById(R.id.editText_UpperLimitIncome);
        editText_LowerLimitOutcome = findViewById(R.id.editText_LowerLimitOutcome);
        editText_UpperLimitOutcome = findViewById(R.id.editText_UpperLimitOutcome);
        editText_LowerLimitGoal = findViewById(R.id.editText_LowerLimitGoal);
        editText_UpperLimitGoal = findViewById(R.id.editText_UpperLimitGoal);
        editText_LowerLimitBudget = findViewById(R.id.editText_LowerLimitBudget);
        editText_UpperLimitBudget = findViewById(R.id.editText_UpperLimitBudget);

        /* Set Firebase */
        FirebaseDatabase myFirebaseDatabase = FirebaseDatabase.getInstance();

        /* Get Firebase database reference node "user" for myUserId */
        myDatabaseReference = myFirebaseDatabase.getReference("").child(myUserId);

        /* Obtain data from Firebase by query */
        myDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {

                        editText_LowerLimitEmergency.setText((String) noteSnapshot.child("userLimitLowerEmergency").getValue());
                        editText_UpperLimitEmergency.setText((String) noteSnapshot.child("userLimitUpperEmergency").getValue());
                        editText_LowerLimitRetirement.setText((String) noteSnapshot.child("userLimitLowerRetirement").getValue());
                        editText_UpperLimitRetirement.setText((String) noteSnapshot.child("userLimitUpperRetirement").getValue());
                        editText_LowerLimitIncome.setText((String) noteSnapshot.child("userLimitLowerIncome").getValue());
                        editText_UpperLimitIncome.setText((String) noteSnapshot.child("userLimitUpperIncome").getValue());
                        editText_LowerLimitOutcome.setText((String) noteSnapshot.child("userLimitLowerOutcome").getValue());
                        editText_UpperLimitOutcome.setText((String) noteSnapshot.child("userLimitUpperOutcome").getValue());
                        editText_LowerLimitGoal.setText((String) noteSnapshot.child("userLimitLowerGoal").getValue());
                        editText_UpperLimitGoal.setText((String) noteSnapshot.child("userLimitUpperGoal").getValue());
                        editText_LowerLimitBudget.setText((String) noteSnapshot.child("userLimitLowerBudget").getValue());
                        editText_UpperLimitBudget.setText((String) noteSnapshot.child("userLimitUpperBudget").getValue());

                    }

                } else {

                    editText_LowerLimitEmergency.setText(null);
                    editText_UpperLimitEmergency.setText(null);
                    editText_LowerLimitRetirement.setText(null);
                    editText_UpperLimitRetirement.setText(null);
                    editText_LowerLimitIncome.setText(null);
                    editText_UpperLimitIncome.setText(null);
                    editText_LowerLimitOutcome.setText(null);
                    editText_UpperLimitOutcome.setText(null);
                    editText_LowerLimitGoal.setText(null);
                    editText_UpperLimitGoal.setText(null);
                    editText_LowerLimitBudget.setText(null);
                    editText_UpperLimitBudget.setText(null);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                new appSupportHandlingDatabaseError(this.getClass().getSimpleName(), databaseError);
                Toast.makeText(getApplicationContext(),R.string.msg_error_firebase,Toast.LENGTH_LONG).show();
                finish();
            }
        });

        /* Treat Delete */
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /* Treat Update */
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Treat input fields */
                String stringLowerLimitEmergency = editText_LowerLimitEmergency.getText().toString().trim();
                String stringUpperLimitEmergency = editText_UpperLimitEmergency.getText().toString().trim();
                String stringLowerLimitRetirement = editText_LowerLimitRetirement.getText().toString().trim();
                String stringUpperLimitRetirement = editText_UpperLimitRetirement.getText().toString().trim();
                String stringLowerLimitIncome = editText_LowerLimitIncome.getText().toString().trim();
                String stringUpperLimitIncome = editText_UpperLimitIncome.getText().toString().trim();
                String stringLowerLimitOutcome = editText_LowerLimitOutcome.getText().toString().trim();
                String stringUpperLimitOutcome = editText_UpperLimitOutcome.getText().toString().trim();
                String stringLowerLimitGoal = editText_LowerLimitGoal.getText().toString().trim();
                String stringUpperLimitGoal = editText_UpperLimitGoal.getText().toString().trim();
                String stringLowerLimitBudget = editText_LowerLimitBudget.getText().toString().trim();
                String stringUpperLimitBudget = editText_UpperLimitBudget.getText().toString().trim();

                /* Verifying: user filled up input fields? */
                if (TextUtils.isEmpty(stringLowerLimitEmergency)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitEmergency)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringLowerLimitRetirement)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitRetirement)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringLowerLimitIncome)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitIncome)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringLowerLimitOutcome)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitOutcome)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringLowerLimitGoal)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitGoal)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringLowerLimitBudget)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitBudget)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                /* Update Firebase data */
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerEmergency").setValue(stringLowerLimitEmergency);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperEmergency").setValue(stringUpperLimitEmergency);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerRetirement").setValue(stringLowerLimitRetirement);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperRetirement").setValue(stringUpperLimitRetirement);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerIncome").setValue(stringLowerLimitIncome);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperIncome").setValue(stringUpperLimitIncome);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerOutcome").setValue(stringLowerLimitOutcome);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperOutcome").setValue(stringUpperLimitOutcome);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerGoal").setValue(stringLowerLimitGoal);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperGoal").setValue(stringUpperLimitGoal);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerBudget").setValue(stringLowerLimitBudget);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperBudget").setValue(stringUpperLimitBudget);

                Toast.makeText(getApplicationContext(), R.string.msg_updated, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat Create */
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Treat input fields */
                String stringLowerLimitEmergency = editText_LowerLimitEmergency.getText().toString().trim();
                String stringUpperLimitEmergency = editText_UpperLimitEmergency.getText().toString().trim();
                String stringLowerLimitRetirement = editText_LowerLimitRetirement.getText().toString().trim();
                String stringUpperLimitRetirement = editText_UpperLimitRetirement.getText().toString().trim();
                String stringLowerLimitIncome = editText_LowerLimitIncome.getText().toString().trim();
                String stringUpperLimitIncome = editText_UpperLimitIncome.getText().toString().trim();
                String stringLowerLimitOutcome = editText_LowerLimitOutcome.getText().toString().trim();
                String stringUpperLimitOutcome = editText_UpperLimitOutcome.getText().toString().trim();
                String stringLowerLimitGoal = editText_LowerLimitGoal.getText().toString().trim();
                String stringUpperLimitGoal = editText_UpperLimitGoal.getText().toString().trim();
                String stringLowerLimitBudget = editText_LowerLimitBudget.getText().toString().trim();
                String stringUpperLimitBudget = editText_UpperLimitBudget.getText().toString().trim();

                /* Verifying: user filled up input fields? */
                if (TextUtils.isEmpty(stringLowerLimitEmergency)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitEmergency)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringLowerLimitRetirement)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitRetirement)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringLowerLimitIncome)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitIncome)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringLowerLimitOutcome)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitOutcome)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringLowerLimitGoal)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitGoal)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringLowerLimitBudget)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LowerLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(stringUpperLimitBudget)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_UpperLimit, Toast.LENGTH_LONG).show();
                    return;
                }

                /* Update Firebase data */
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerEmergency").setValue(stringLowerLimitEmergency);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperEmergency").setValue(stringUpperLimitEmergency);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerRetirement").setValue(stringLowerLimitRetirement);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperRetirement").setValue(stringUpperLimitRetirement);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerIncome").setValue(stringLowerLimitIncome);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperIncome").setValue(stringUpperLimitIncome);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerOutcome").setValue(stringLowerLimitOutcome);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperOutcome").setValue(stringUpperLimitOutcome);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerGoal").setValue(stringLowerLimitGoal);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperGoal").setValue(stringUpperLimitGoal);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitLowerBudget").setValue(stringLowerLimitBudget);
                myDatabaseReference.child(appFirebaseCheckAuthorization.myUserId).child("userLimitUpperBudget").setValue(stringUpperLimitBudget);

                Toast.makeText(getApplicationContext(), R.string.msg_inserted, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /* Inflate menu toolbar */
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_navigation_general, menu);
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

            /* Treat toolbar: Options */
            case R.id.actionAccountCategoryReport:
                startActivity(new Intent(getApplicationContext(), appAccountMasterChartReport.class));
                break;
            case R.id.actionAccountMasterReport:
                startActivity(new Intent(getApplicationContext(), appAccountMasterReport.class));
                break;
            case R.id.actionAccountTransactionReport:
                startActivity(new Intent(getApplicationContext(), appAccountTransactionReport.class));
                break;
            case R.id.actionAccountTransferReport:
                startActivity(new Intent(getApplicationContext(), appAccountTransferReport.class));
                break;
            case R.id.actionBudgetReport:
                startActivity(new Intent(getApplicationContext(), appAccountMasterBudgetReport.class));
                break;
            case R.id.actionUser:
                startActivity(new Intent(getApplicationContext(), appUserReport.class));
                break;
            case R.id.actionGoalReport:
                startActivity(new Intent(getApplicationContext(), appAccountMasterGoalReport.class));
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
