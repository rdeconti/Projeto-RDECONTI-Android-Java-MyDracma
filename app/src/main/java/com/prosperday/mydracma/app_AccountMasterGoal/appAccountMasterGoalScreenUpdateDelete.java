package com.prosperday.mydracma.app_AccountMasterGoal;

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
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_Authorization.appAuthorizationLogIn;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization;
import com.prosperday.mydracma.app_Firebase.appFirebaseCheckPersistence;
import com.prosperday.mydracma.app_Support.appSupportHandlingDatabaseError;
import com.prosperday.mydracma.app_Support.appSupportMenuGeneral;

import static com.prosperday.mydracma.app_AccountMasterGoal.appAccountMasterGoalReportAdapter.chooseFromUser;

public class appAccountMasterGoalScreenUpdateDelete extends AppCompatActivity {

    public static Context currentContext;
    private ProgressBar progressBar;
    private EditText inputName;
    private EditText inputDatePlanned;
    private EditText inputDateRealized;
    private EditText inputValuePlanned;
    private EditText inputValueRealized;
    private EditText inputSavingRate;
    private Spinner spnType;
    private Spinner spnPriority;
    private String strType;
    private String strPriority;
    private String strName;
    private String strDatePlanned;
    private String strDateRealized;
    private String strValuePlanned;
    private String strValueRealized;
    private String strSavingRate;
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
        setContentView(R.layout.app_account_master_goal_screen_update_delete);

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
        inputName = findViewById(R.id.editTextName);
        inputDatePlanned = findViewById(R.id.editTextDatePlanned);
        inputDateRealized = findViewById(R.id.editTextDateRealized);
        inputValuePlanned = findViewById(R.id.editTextValuePlanned);
        inputValueRealized = findViewById(R.id.editTextValueRealized);
        inputSavingRate = findViewById(R.id.editTextSavingRate);

        /* Treat spinner Type */
        spnType = findViewById(R.id.spnType);
        String[] lstType = getResources().getStringArray(R.array.GoalType);
        ArrayAdapter adpType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstType);
        adpType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnType.setAdapter(adpType);
        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strType = (String) spnType.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_Type, Toast.LENGTH_LONG).show();
            }
        });

        /* Treat spinner Priority */
        spnPriority = findViewById(R.id.spnPriority);
        String[] lstPriority = getResources().getStringArray(R.array.GoalPriority);
        ArrayAdapter adpPriority = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstPriority);
        adpPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPriority.setAdapter(adpPriority);
        spnPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strPriority = (String) spnPriority.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), R.string.msg_Priority, Toast.LENGTH_LONG).show();
            }
        });

        /* Set Firebase */
        DatabaseReference myDatabaseReference = FirebaseDatabase.getInstance().getReference("Goal");

        /* Obtain data from Firebase by query */
        Query myQueryData = myDatabaseReference.orderByChild("GoalKey").equalTo(chooseFromUser);

        myQueryData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {

                strFromFirebase = (String) dataSnapshot.child("GoalType").getValue();
                ArrayAdapter adpType = (ArrayAdapter) spnType.getAdapter();
                intSpinnerPosition = adpType.getPosition(strFromFirebase);
                spnType.setSelection(intSpinnerPosition);

                strFromFirebase = (String) dataSnapshot.child("GoalPriority").getValue();
                ArrayAdapter adpPriority = (ArrayAdapter) spnPriority.getAdapter();
                intSpinnerPosition = adpPriority.getPosition(strFromFirebase);
                spnPriority.setSelection(intSpinnerPosition);

                inputName.setText((String) dataSnapshot.child("GoalName").getValue());
                inputDatePlanned.setText((String) dataSnapshot.child("GoalDatePlanned").getValue());
                inputDateRealized.setText((String) dataSnapshot.child("GoalDateRealized").getValue());
                inputValuePlanned.setText((String) dataSnapshot.child("GoalValuePlanned").getValue());
                inputValueRealized.setText((String) dataSnapshot.child("GoalValueRealized").getValue());
                inputSavingRate.setText((String) dataSnapshot.child("GoalSavingRate").getValue());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {

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
                AlertDialog.Builder builder = new AlertDialog.Builder(appAccountMasterGoalScreenUpdateDelete.this);
                builder.setTitle(R.string.lbl_Confirmation);
                builder.setMessage(R.string.msg_AreYouSureDelete);
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        progressBar.setVisibility(View.VISIBLE);
                        /* TODO DELETE JUST WHAT IS NOT IN USE */
                        boolean returnFirebase = appAccountMasterGoalFirebaseMaintain.appGoalFirebaseDelete();
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

                strName = inputName.getText().toString().trim();
                strDatePlanned = inputDatePlanned.getText().toString().trim();
                strDateRealized = inputDateRealized.getText().toString().trim();
                strValuePlanned = inputValuePlanned.getText().toString().trim();
                strValueRealized = inputValueRealized.getText().toString().trim();
                strSavingRate = inputSavingRate.getText().toString().trim();

                if (strType.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Type, Toast.LENGTH_LONG).show();
                    return;
                }

                if (strPriority.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Priority, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strName)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Name, Toast.LENGTH_LONG).show();
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

                if (TextUtils.isEmpty(strSavingRate)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_SavingRate, Toast.LENGTH_LONG).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                boolean returnFirebase = appAccountMasterGoalFirebaseMaintain.appGoalFirebaseUpdate(
                        strName,
                        strType,
                        strDatePlanned,
                        strDateRealized,
                        strValuePlanned,
                        strValueRealized,
                        strPriority,
                        strSavingRate);

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

    /* Treat spinner Type */
    private class spnTypeItemSelection implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            strType = (String) spnType.getSelectedItem();
            if (strType.equals(getString(R.string.lbl_ChooseOneOption)))
                Toast.makeText(getApplicationContext(), R.string.msg_Type, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getApplicationContext(), R.string.msg_Type, Toast.LENGTH_LONG).show();
        }
    }

    /* Treat spinner Priority */
    private class spnPriorityItemSelection implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            strPriority = (String) spnPriority.getSelectedItem();
            if (strPriority.equals(getString(R.string.lbl_ChooseOneOption)))
                Toast.makeText(getApplicationContext(), R.string.msg_Priority, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getApplicationContext(), R.string.msg_Priority, Toast.LENGTH_LONG).show();
        }
    }
}
