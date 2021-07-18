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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class appConfigurationNotification extends AppCompatActivity {

    List<appUserFirebaseModel> user;
    private FirebaseAuth myAuthorization;
    private DatabaseReference myDatabaseReference;

    private Spinner spnEmergency;
    private Spinner spnIncome;
    private Spinner spnOutcome;
    private Spinner spnBillToPay;
    private Spinner spnBillNotPaid;
    private Spinner spnRetirement;
    private Spinner spnGoal;
    private Spinner spnBudget;

    private String strEmergency;
    private String strIncome;
    private String strOutcome;
    private String strBillToPay;
    private String strBillNotPaid;
    private String strRetirement;
    private String strGoal;
    private String strBudget;    

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
        setContentView(R.layout.app_configuration_notification);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnBack = findViewById(R.id.btnDelete);

        /* Treat spinner Emergency */
        spnEmergency = findViewById(R.id.spnEmergency);
        String[] lstEmergency = getResources().getStringArray(R.array.YesNo);
        ArrayAdapter adpEmergency = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstEmergency);
        adpEmergency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEmergency.setAdapter(adpEmergency);
        spnEmergency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strEmergency = (String) spnEmergency.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_Emergency, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner Retirement */
        spnRetirement = findViewById(R.id.spnRetirement);
        String[] lstRetirement = getResources().getStringArray(R.array.YesNo);
        ArrayAdapter adpRetirement = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstRetirement);
        adpRetirement.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRetirement.setAdapter(adpRetirement);
        spnRetirement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strRetirement = (String) spnRetirement.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_Retirement, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner Income */
        spnIncome = findViewById(R.id.spnIncome);
        String[] lstIncome = getResources().getStringArray(R.array.YesNo);
        ArrayAdapter adpIncome = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstIncome);
        adpIncome.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIncome.setAdapter(adpIncome);
        spnIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strIncome = (String) spnIncome.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_Income, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner Outcome */
        spnOutcome = findViewById(R.id.spnOutcome);
        String[] lstOutcome = getResources().getStringArray(R.array.YesNo);
        ArrayAdapter adpOutcome = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstOutcome);
        adpOutcome.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOutcome.setAdapter(adpOutcome);
        spnOutcome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strOutcome = (String) spnOutcome.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_Outcome, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner Goal */
        spnGoal = findViewById(R.id.spnGoal);
        String[] lstGoal = getResources().getStringArray(R.array.YesNo);
        ArrayAdapter adpGoal = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstGoal);
        adpGoal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGoal.setAdapter(adpGoal);
        spnGoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGoal = (String) spnGoal.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_Goal, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner Budget */
        spnBudget = findViewById(R.id.spnBudget);
        String[] lstBudget = getResources().getStringArray(R.array.YesNo);
        ArrayAdapter adpBudget = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstBudget);
        adpBudget.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBudget.setAdapter(adpBudget);
        spnBudget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strBudget = (String) spnBudget.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_Budget, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner BillToPay */
        spnBillToPay = findViewById(R.id.spnBillToPay);
        String[] lstBillToPay = getResources().getStringArray(R.array.YesNo);
        ArrayAdapter adpBillToPay = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstBillToPay);
        adpBillToPay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBillToPay.setAdapter(adpBillToPay);
        spnBillToPay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strBillToPay = (String) spnBillToPay.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_BillToPay, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner BillNotPaid */
        spnBillNotPaid = findViewById(R.id.spnBillNotPaid);
        String[] lstBillNotPaid = getResources().getStringArray(R.array.YesNo);
        ArrayAdapter adpBillNotPaid = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstBillNotPaid);
        adpBillNotPaid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBillNotPaid.setAdapter(adpBillNotPaid);
        spnBillNotPaid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strBillNotPaid = (String) spnBillNotPaid.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_BillNotPaid, Toast.LENGTH_LONG).show();
            }
        });

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
                        spnEmergency.setSelection(0);
                        spnRetirement.setSelection(0);
                        spnIncome.setSelection(0);
                        spnOutcome.setSelection(0);
                        spnGoal.setSelection(0);
                        spnBudget.setSelection(0);
                        spnBillToPay.setSelection(0);
                        spnBillNotPaid.setSelection(0);
                    }
                } else {
                    spnEmergency.setSelection(0);
                    spnRetirement.setSelection(0);
                    spnIncome.setSelection(0);
                    spnOutcome.setSelection(0);
                    spnGoal.setSelection(0);
                    spnBudget.setSelection(0);
                    spnBillToPay.setSelection(0);
                    spnBillNotPaid.setSelection(0);
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

                /* Verifying: user filled up input fields? */
                if (strEmergency.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Emergency, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strRetirement.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Retirement, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strIncome.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Income, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strOutcome.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Outcome, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strGoal)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Goal, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strBudget.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Budget, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strBillToPay.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_BillToPay, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strBillNotPaid.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_BillNotPaid, Toast.LENGTH_LONG).show();
                    return;
                }

                /* Update Firebase data */
                myDatabaseReference.child(myUserId).child("UserNotificationEmergency").setValue(strEmergency);
                myDatabaseReference.child(myUserId).child("UserNotificationRetirement").setValue(strRetirement);
                myDatabaseReference.child(myUserId).child("UserNotificationIncome").setValue(strIncome);
                myDatabaseReference.child(myUserId).child("UserNotificationOutcome").setValue(strOutcome);
                myDatabaseReference.child(myUserId).child("UserNotificationGoal").setValue(strGoal);
                myDatabaseReference.child(myUserId).child("UserNotificationBudget").setValue(strBudget);
                myDatabaseReference.child(myUserId).child("UserNotificationBillToPay").setValue(strBillToPay);
                myDatabaseReference.child(myUserId).child("UserNotificationBillNotPaid").setValue(strBillNotPaid);

                Toast.makeText(getApplicationContext(), R.string.msg_updated, Toast.LENGTH_LONG).show();
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