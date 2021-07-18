package com.prosperday.mydracma.app_User;
// ---------------------------------------------------------------
// Created by Rosemeire Deconti
// Created on 05/01/2018
// Created at 15:02
// Package is com.prosperday.mydracma
// Project is MyDracma
// ----------------------------------------------------------------

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_Authorization.appAuthorizationLogIn;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckPersistence;
import com.prosperday.mydracma.app_Support.appSupportMenuGeneral;

public class appUserScreenCreate extends AppCompatActivity {

    public static Context currentContext;
    private ProgressBar progressBar;
    private EditText inputCode;
    private EditText inputName;
    private EditText inputPhone;
    private EditText inputBirthday;
    private Spinner spnLanguage;
    private Spinner spnCurrency;
    private Spinner spnSessionTime;
    private Spinner spnAdvertising;
    private Spinner spnTimeFormat;
    private Spinner spnDateFormat;
    private Spinner spnDecimalFormat;
    private String strCode;
    private String strName;
    private String strPhone;
    private String strBirthday;
    private String strLanguage;
    private String strCurrency;
    private String strSessionTime;
    private String strAdvertising;
    private String strTimeFormat;
    private String strDateFormat;
    private String strDecimalFormat;

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
        setContentView(R.layout.app_user_screen_create);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnCreate = findViewById(R.id.btnCreate);

        /* Set progress bar */
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        /* Set Edit text field */
        inputCode = findViewById(R.id.editTextCode);
        inputName = findViewById(R.id.editTextName);
        inputPhone = findViewById(R.id.editTextPhone);
        inputBirthday = findViewById(R.id.editTextBirthday);

        /* Treat spinner language */
        spnLanguage = findViewById(R.id.spnLanguage);
        String[] lstLanguage = getResources().getStringArray(R.array.Language);
        ArrayAdapter adpLanguage = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstLanguage);
        adpLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLanguage.setAdapter(adpLanguage);
        spnLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strLanguage = (String) spnLanguage.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_Language, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner currency */
        spnCurrency = findViewById(R.id.spnCurrency);
        String[] lstCurrency = getResources().getStringArray(R.array.Currency);
        ArrayAdapter adpCurrency = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstCurrency);
        adpCurrency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCurrency.setAdapter(adpCurrency);
        spnCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCurrency = (String) spnCurrency.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_Currency, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner session time */
        spnSessionTime = findViewById(R.id.spnSessionTime);
        String[] lstSessionTime = getResources().getStringArray(R.array.SessionTime);
        ArrayAdapter adpSessionTime = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstSessionTime);
        adpSessionTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSessionTime.setAdapter(adpSessionTime);
        spnSessionTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strSessionTime = (String) spnSessionTime.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_SessionTime, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner advertising */
        spnAdvertising = findViewById(R.id.spnAdvertising);
        String[] lstAdvertising = getResources().getStringArray(R.array.YesNo);
        ArrayAdapter adpAdvertising = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstAdvertising);
        adpAdvertising.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAdvertising.setAdapter(adpAdvertising);
        spnAdvertising.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAdvertising = (String) spnAdvertising.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_Advertising, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner time format */
        spnTimeFormat = findViewById(R.id.spnTimeFormat);
        String[] lstTimeFormat = getResources().getStringArray(R.array.TimeFormat);
        ArrayAdapter adpTimeFormat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstTimeFormat);
        adpTimeFormat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTimeFormat.setAdapter(adpTimeFormat);
        spnTimeFormat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strTimeFormat = (String) spnTimeFormat.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_TimeFormat, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner date format */
        spnDateFormat = findViewById(R.id.spnDateFormat);
        String[] lstDateFormat = getResources().getStringArray(R.array.DateFormat);
        ArrayAdapter adpDateFormat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstDateFormat);
        adpDateFormat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDateFormat.setAdapter(adpDateFormat);
        spnDateFormat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strDateFormat = (String) spnDateFormat.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_DateFormat, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner decimal format */
        spnDecimalFormat = findViewById(R.id.spnDecimalFormat);
        String[] lstDecimalFormat = getResources().getStringArray(R.array.DecimalFormat);
        ArrayAdapter adpDecimalFormat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstDecimalFormat);
        adpDecimalFormat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDecimalFormat.setAdapter(adpDecimalFormat);
        spnDecimalFormat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strDecimalFormat = (String) spnDecimalFormat.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_DecimalFormat, Toast.LENGTH_LONG).show();
            }
        });

        /* Set Firebase */
        DatabaseReference myDatabaseReference = FirebaseDatabase.getInstance().getReference("User");

        /* Clean screen field */
        clean_screen_fields();

        /* Treat Create */
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strCode = inputCode.getText().toString().trim();
                strName = inputName.getText().toString().trim();
                strPhone = inputPhone.getText().toString().trim();
                strBirthday = inputBirthday.getText().toString().trim();

                if (strLanguage.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Language, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strCurrency.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Currency, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strSessionTime.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_SessionTime, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strAdvertising.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Advertising, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strTimeFormat.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_TimeFormat, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strDateFormat.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_DateFormat, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strDecimalFormat.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_DecimalFormat, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strCode)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Code, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strName)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Name, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strPhone)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Phone, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strBirthday)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Birthday, Toast.LENGTH_LONG).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                boolean returnFirebase = appUserFirebaseMaintain.appUserFirebaseCreate(
                        strCode,
                        strName,
                        strPhone,
                        strBirthday,
                        strLanguage,
                        strCurrency,
                        strSessionTime,
                        strAdvertising,
                        strTimeFormat,
                        strDateFormat,
                        strDecimalFormat);

                progressBar.setVisibility(View.INVISIBLE);

                if (returnFirebase) {
                    Toast.makeText(getApplicationContext(), R.string.msg_created, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
                }

                clean_screen_fields();
                finish();
            }
        });
    }

    /* Clean screen field */
    private void clean_screen_fields() {
        inputCode.setText(null);
        inputName.setText(null);
        inputPhone.setText(null);
        inputBirthday.setText(null);
        spnLanguage.setSelection(0);
        spnCurrency.setSelection(0);
        spnSessionTime.setSelection(0);
        spnAdvertising.setSelection(0);
        spnTimeFormat.setSelection(0);
        spnAdvertising.setSelection(0);
        spnTimeFormat.setSelection(0);
        spnDateFormat.setSelection(0);
        spnDecimalFormat.setSelection(0);
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
