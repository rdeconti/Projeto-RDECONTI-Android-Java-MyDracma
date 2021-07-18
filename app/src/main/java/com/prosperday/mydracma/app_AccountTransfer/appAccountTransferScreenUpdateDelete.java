package com.prosperday.mydracma.app_AccountTransfer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_AccountMaster.appAccountMasterFirebaseModel;
import com.prosperday.mydracma.app_Authorization.appAuthorizationLogIn;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckPersistence;
import com.prosperday.mydracma.app_Support.appSupportHandlingDatabaseError;
import com.prosperday.mydracma.app_Support.appSupportMenuGeneral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static com.prosperday.mydracma.app_AccountTransfer.appAccountTransferReportAdapter.chooseFromUser;

public class appAccountTransferScreenUpdateDelete extends AppCompatActivity {

    public static Context currentContext;

    ArrayAdapter<String> adpAccountFrom;
    ArrayAdapter<String> adpAccountTo;

    List<String> listAccountFrom;
    List<String> listAccountTo;

    private ProgressBar progressBar;

    private Spinner spnAccountFrom;
    private Spinner spnAccountTo;
    private Spinner spnPeriod;
    private Spinner spnAutomatic;
    private Spinner spnReminder;

    private String strAccountFrom;
    private String strAccountTo;
    private String strPeriod;
    private String strAutomatic;
    private String strReminder;
    private String strDatePlanned;
    private String strDateRealized;
    private String strValuePlanned;
    private String strValueRealized;
    private String strOccurrence;
    private String strYear;
    private String strMonth;
    private String strDay;

    private EditText inputDatePlanned;
    private EditText inputDateRealized;
    private EditText inputValuePlanned;
    private EditText inputValueRealized;
    private EditText inputOccurrence;
    
    private String strFromFirebase;
    private int intSpinnerPosition;

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
        setContentView(R.layout.app_account_transfer_screen_update_delete);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);

        /* Set progress bar */
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        /* Set Fields */
        inputDatePlanned = findViewById(R.id.editTextDatePlanned);
        inputDateRealized = findViewById(R.id.editTextDateRealized);
        inputValuePlanned = findViewById(R.id.editTextValuePlanned);
        inputValueRealized = findViewById(R.id.editTextValueRealized);
        inputOccurrence = findViewById(R.id.editTextOccurrence);

        /* Create Array list to spinner AccountFrom and AccountTo */
        listAccountFrom = new ArrayList<>();
        listAccountTo = new ArrayList<>();

        /* Get spinner data from firebase */
        DatabaseReference firebaseAccountMaster = FirebaseDatabase.getInstance().getReference("AccountMaster");

        /* Obtain data to recycler view */
        firebaseAccountMaster.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listAccountFrom.clear();
                listAccountTo.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        appAccountMasterFirebaseModel snapshotDetails = dataSnapshot.getValue(appAccountMasterFirebaseModel.class);
                        listAccountFrom.add(snapshotDetails.getAccountMasterName());
                        listAccountTo.add(snapshotDetails.getAccountMasterName());
                    }

                    /* Eliminate duplicated entries from lists */
                    HashSet<String> hashSet = new HashSet<String>();

                    hashSet.clear();
                    hashSet.addAll(listAccountFrom);
                    listAccountFrom.clear();
                    listAccountFrom.addAll(hashSet);

                    hashSet.clear();
                    hashSet.addAll(listAccountTo);
                    listAccountTo.clear();
                    listAccountTo.addAll(hashSet);

                    /* Sort array list spinner base ascending order */
                    Collections.sort(listAccountFrom, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return 0;
                        }
                    });

                    Collections.sort(listAccountTo, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return 0;
                        }
                    });

                    /* Treat spinner Account From */
                    spnAccountFrom = findViewById(R.id.spnAccountFrom);
                    adpAccountFrom = new ArrayAdapter<>(appAccountTransferScreenUpdateDelete.this, android.R.layout.simple_spinner_item, listAccountFrom);
                    adpAccountFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnAccountFrom.setAdapter(adpAccountFrom);
                    spnAccountFrom.setSelection(0);

                    spnAccountFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strAccountFrom = (String) spnAccountFrom.getSelectedItem();
                        }

                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), R.string.msg_Account, Toast.LENGTH_LONG).show();
                        }
                    });

                    /* Treat spinner Account To */
                    spnAccountTo = findViewById(R.id.spnAccountTo);
                    adpAccountTo = new ArrayAdapter<>(appAccountTransferScreenUpdateDelete.this, android.R.layout.simple_spinner_item, listAccountTo);
                    adpAccountTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnAccountTo.setAdapter(adpAccountTo);
                    spnAccountTo.setSelection(0);

                    spnAccountTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strAccountTo = (String) spnAccountTo.getSelectedItem();
                        }

                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), R.string.msg_Account, Toast.LENGTH_LONG).show();
                        }
                    });

                    /* Treat spinner Period */
                    spnPeriod = findViewById(R.id.spnPeriod);
                    String[] lstPeriod = getResources().getStringArray(R.array.Period);
                    ArrayAdapter adpPeriod = new ArrayAdapter<>(appAccountTransferScreenUpdateDelete.this, android.R.layout.simple_spinner_item, lstPeriod);
                    adpPeriod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnPeriod.setAdapter(adpPeriod);
                    spnPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strPeriod = (String) spnPeriod.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), R.string.msg_Period, Toast.LENGTH_LONG).show();
                        }
                    });

                    /* Treat spinner Reminder */
                    spnReminder = findViewById(R.id.spnReminder);
                    String[] lstReminder = getResources().getStringArray(R.array.YesNo);
                    ArrayAdapter adpReminder = new ArrayAdapter<>(appAccountTransferScreenUpdateDelete.this, android.R.layout.simple_spinner_item, lstReminder);
                    adpReminder.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnReminder.setAdapter(adpReminder);
                    spnReminder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strReminder = (String) spnReminder.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), R.string.msg_Reminder, Toast.LENGTH_LONG).show();
                        }
                    });

                    /* Treat spinner Automatic */
                    spnAutomatic = findViewById(R.id.spnAutomatic);
                    String[] lstAutomatic = getResources().getStringArray(R.array.YesNo);
                    ArrayAdapter adpAutomatic = new ArrayAdapter<>(appAccountTransferScreenUpdateDelete.this, android.R.layout.simple_spinner_item, lstAutomatic);
                    adpAutomatic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnAutomatic.setAdapter(adpAutomatic);
                    spnAutomatic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strAutomatic = (String) spnAutomatic.getSelectedItem();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), R.string.msg_Automatic, Toast.LENGTH_LONG).show();
                        }
                    });

                    /* Set Firebase */
                    DatabaseReference myDatabaseReference = FirebaseDatabase.getInstance().getReference("AccountTransfer");

                    /* Obtain data from Firebase by query */
                    Query myQueryData = myDatabaseReference.orderByChild("AccountTransferKey").equalTo(chooseFromUser);
                    myQueryData.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {

                            strFromFirebase = (String) dataSnapshot.child("AccountTransferAccountFrom").getValue();
                            ArrayAdapter adpAccountFrom = (ArrayAdapter) spnAccountFrom.getAdapter();
                            intSpinnerPosition = adpAccountFrom.getPosition(strFromFirebase);
                            spnAccountFrom.setSelection(intSpinnerPosition);

                            strFromFirebase = (String) dataSnapshot.child("AccountTransferAccountTo").getValue();
                            ArrayAdapter adpAccountTo = (ArrayAdapter) spnAccountTo.getAdapter();
                            intSpinnerPosition = adpAccountTo.getPosition(strFromFirebase);
                            spnAccountTo.setSelection(intSpinnerPosition);

                            strFromFirebase = (String) dataSnapshot.child("AccountTransferPeriod").getValue();
                            ArrayAdapter adpPeriod = (ArrayAdapter) spnPeriod.getAdapter();
                            intSpinnerPosition = adpPeriod.getPosition(strFromFirebase);
                            spnPeriod.setSelection(intSpinnerPosition);

                            strFromFirebase = (String) dataSnapshot.child("AccountTransferReminder").getValue();
                            ArrayAdapter adpReminder = (ArrayAdapter) spnReminder.getAdapter();
                            intSpinnerPosition = adpReminder.getPosition(strFromFirebase);
                            spnReminder.setSelection(intSpinnerPosition);

                            strFromFirebase = (String) dataSnapshot.child("AccountTransferAutomatic").getValue();
                            ArrayAdapter adpAutomatic = (ArrayAdapter) spnAutomatic.getAdapter();
                            intSpinnerPosition = adpAutomatic.getPosition(strFromFirebase);
                            spnAutomatic.setSelection(intSpinnerPosition);

                            inputDatePlanned.setText((String) dataSnapshot.child("AccountTransferDatePlanned").getValue());
                            inputDateRealized.setText((String) dataSnapshot.child("AccountTransferDateRealized").getValue());
                            inputValuePlanned.setText((String) dataSnapshot.child("AccountTransferYear").getValue());
                            inputValueRealized.setText((String) dataSnapshot.child("AccountTransferYear").getValue());
                            inputOccurrence.setText((String) dataSnapshot.child("AccountTransferOccurrence").getValue());

                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            new appSupportHandlingDatabaseError(this.getClass().getSimpleName(), databaseError);
                            Toast.makeText(getApplicationContext(),R.string.msg_error_firebase,Toast.LENGTH_LONG).show();
                            finish();
                        }

                    });

                } else {
                    Toast.makeText(getApplicationContext(), R.string.msg_CreateAppData, Toast.LENGTH_LONG).show();
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
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(appAccountTransferScreenUpdateDelete.this);
                builder.setTitle(R.string.lbl_Confirmation);
                builder.setMessage(R.string.msg_AreYouSureDelete);
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.VISIBLE);
                        /* TODO DELETE JUST WHAT IS NOT IN USE */
                        boolean returnFirebase = appAccountTransferFirebaseMaintain.appAccountTransferFirebaseDelete();
                        progressBar.setVisibility(View.INVISIBLE);
                        if (returnFirebase) {
                            Toast.makeText(getApplicationContext(), R.string.msg_deleted, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss();
                        finish();
                    }
                });
                builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                finish();
            }
        });

        /* Treat Update */
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strDatePlanned = inputDatePlanned.getText().toString().trim();
                strDateRealized = inputDateRealized.getText().toString().trim();
                strValuePlanned = inputValuePlanned.getText().toString().trim();
                strValueRealized = inputValueRealized.getText().toString().trim();
                strOccurrence = inputOccurrence.getText().toString().trim();

                /* strYear = strDateRealized.substring(0, 3);
                strMonth = strDateRealized.substring(7, 8);
                strDay = strDateRealized.substring(12, 13); */

                strYear = "2018";
                strMonth = "06";
                strDay = "01";

                if (strAccountFrom.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_AccountFrom, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strAccountTo.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_AccountTo, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strPeriod.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Period, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strAutomatic.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Automatic, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strReminder.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Reminder, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strDatePlanned)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_DatePlanned, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strDateRealized)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_DateRealized, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strValuePlanned)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_ValuePlanned, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strValueRealized)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_ValueRealized, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strOccurrence)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Occurrence, Toast.LENGTH_LONG).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                boolean returnFirebase = appAccountTransferFirebaseMaintain.appAccountTransferFirebaseUpdate(
                        strYear,
                        strMonth,
                        strDay,
                        strAccountFrom,
                        strAccountTo,
                        strDatePlanned,
                        strDateRealized,
                        strValuePlanned,
                        strValueRealized,
                        strPeriod,
                        strOccurrence,
                        strReminder,
                        strAutomatic);

                progressBar.setVisibility(View.INVISIBLE);

                if (returnFirebase) {
                    Toast.makeText(getApplicationContext(), R.string.msg_updated, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
                }

                finish();
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
