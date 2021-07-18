package com.prosperday.mydracma.app_AccountMasterChart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prosperday.mydracma.R;
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

import static com.prosperday.mydracma.app_Authorization.appAuthorizationLogIn.userLanguage;

public class appAccountMasterChartScreenCreate extends AppCompatActivity {

    private static final String TAG01 = "Activity Lifecycle";
    public static Context currentContext;
    ArrayAdapter<String> adpTransaction;
    ArrayAdapter<String> adpType;
    ArrayAdapter<String> adpClass;
    ArrayAdapter<String> adpCategory;
    ArrayAdapter<String> adpSubCategory;
    List<String> listTransaction;
    List<String> listType;
    List<String> listClass;
    List<String> listCategory;
    List<String> listSubCategory;
    private ProgressBar progressBar;
    private Spinner spnTransaction;
    private Spinner spnType;
    private Spinner spnClass;
    private Spinner spnCategory;
    private Spinner spnSubCategory;
    private String strTransaction;
    private String strType;
    private String strClass;
    private String strCategory;
    private String strSubCategory;
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
        setContentView(R.layout.app_account_master_chart_screen_create);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnCreate = findViewById(R.id.btnCreate);

        /* Set progress bar */
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        /* Create Array list to spinners */
        listTransaction = new ArrayList<>();
        listType = new ArrayList<>();
        listClass = new ArrayList<>();
        listCategory = new ArrayList<>();
        listSubCategory = new ArrayList<>();

        /* Get spinner data from firebase */
        DatabaseReference firebaseAccountChart = FirebaseDatabase.getInstance().getReference("AccountChart");

        /* Obtain data to recycler view */
        firebaseAccountChart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTransaction.clear();
                listType.clear();
                listClass.clear();
                listCategory.clear();
                listSubCategory.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        appAccountMasterChartFirebaseModel snapshotDetails = dataSnapshot.getValue(appAccountMasterChartFirebaseModel.class);
                        switch (userLanguage) {
                            case "EN":
                                listTransaction.add(snapshotDetails.getAccountChartTransactionEN());
                                listType.add(snapshotDetails.getAccountChartTypeEN());
                                listClass.add(snapshotDetails.getAccountChartClassEN());
                                listCategory.add(snapshotDetails.getAccountChartCategoryEN());
                                listSubCategory.add(snapshotDetails.getAccountChartSubCategoryEN());
                                break;
                            case "SP":
                                listTransaction.add(snapshotDetails.getAccountChartTransactionSP());
                                listType.add(snapshotDetails.getAccountChartTypeSP());
                                listClass.add(snapshotDetails.getAccountChartClassSP());
                                listCategory.add(snapshotDetails.getAccountChartCategorySP());
                                listSubCategory.add(snapshotDetails.getAccountChartSubCategorySP());
                                break;
                            case "PT":
                                listTransaction.add(snapshotDetails.getAccountChartTransactionPT());
                                listType.add(snapshotDetails.getAccountChartTypePT());
                                listClass.add(snapshotDetails.getAccountChartClassPT());
                                listCategory.add(snapshotDetails.getAccountChartCategoryPT());
                                listSubCategory.add(snapshotDetails.getAccountChartSubCategoryPT());
                                break;
                            default:
                                listTransaction.add(snapshotDetails.getAccountChartTransactionEN());
                                listType.add(snapshotDetails.getAccountChartTypeEN());
                                listClass.add(snapshotDetails.getAccountChartClassEN());
                                listCategory.add(snapshotDetails.getAccountChartCategoryEN());
                                listSubCategory.add(snapshotDetails.getAccountChartSubCategoryEN());
                                break;
                        }
                    }

                    /* Eliminate duplicated entries from lists */
                    HashSet<String> hashSet = new HashSet<>();

                    hashSet.clear();
                    hashSet.addAll(listTransaction);
                    listTransaction.clear();
                    listTransaction.addAll(hashSet);

                    hashSet.clear();
                    hashSet.addAll(listType);
                    listType.clear();
                    listType.addAll(hashSet);

                    hashSet.clear();
                    hashSet.addAll(listClass);
                    listClass.clear();
                    listClass.addAll(hashSet);

                    hashSet.clear();
                    hashSet.addAll(listCategory);
                    listCategory.clear();
                    listCategory.addAll(hashSet);

                    hashSet.clear();
                    hashSet.addAll(listSubCategory);
                    listSubCategory.clear();
                    listSubCategory.addAll(hashSet);

                    /* Sort array list spinner base ascending order */
                    Collections.sort(listTransaction, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return 0;
                        }
                    });

                    Collections.sort(listType, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return 0;
                        }
                    });

                    Collections.sort(listClass, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return 0;
                        }
                    });

                    Collections.sort(listCategory, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return 0;
                        }
                    });

                    Collections.sort(listSubCategory, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return 0;
                        }
                    });

                    /* Treat spinner Transaction */
                    spnTransaction = findViewById(R.id.spnTransaction);
                    adpTransaction = new ArrayAdapter<>(appAccountMasterChartScreenCreate.this, android.R.layout.simple_spinner_item, listTransaction);
                    adpTransaction.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnTransaction.setAdapter(adpTransaction);
                    spnTransaction.setSelection(0);


                    spnTransaction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strTransaction = (String) spnTransaction.getSelectedItem();
                        }

                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), R.string.msg_Transaction, Toast.LENGTH_LONG).show();
                        }
                    });

                    /* Treat spinner Class */
                    spnClass = findViewById(R.id.spnClass);
                    adpClass = new ArrayAdapter<>(appAccountMasterChartScreenCreate.this, android.R.layout.simple_spinner_item, listClass);
                    adpClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnClass.setAdapter(adpClass);
                    spnClass.setSelection(0);

                    spnClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strClass = (String) spnClass.getSelectedItem();
                        }

                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), R.string.msg_Class, Toast.LENGTH_LONG).show();
                        }
                    });

                    /* Treat spinner Type */
                    spnType = findViewById(R.id.spnType);
                    adpType = new ArrayAdapter<>(appAccountMasterChartScreenCreate.this, android.R.layout.simple_spinner_item, listType);
                    adpType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnType.setAdapter(adpType);
                    spnType.setSelection(0);

                    spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strType = (String) spnType.getSelectedItem();
                        }

                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), R.string.msg_Type, Toast.LENGTH_LONG).show();
                        }
                    });

                    /* Treat spinner Category */
                    spnCategory = findViewById(R.id.spnCategory);
                    adpCategory = new ArrayAdapter<>(appAccountMasterChartScreenCreate.this, android.R.layout.simple_spinner_item, listCategory);
                    adpCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnCategory.setAdapter(adpCategory);
                    spnCategory.setSelection(0);

                    spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strCategory = (String) spnCategory.getSelectedItem();
                        }

                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), R.string.msg_Category, Toast.LENGTH_LONG).show();
                        }
                    });

                    /* Treat spinner SubCategory */
                    spnSubCategory = findViewById(R.id.spnSubCategory);
                    adpSubCategory = new ArrayAdapter<>(appAccountMasterChartScreenCreate.this, android.R.layout.simple_spinner_item, listSubCategory);
                    adpSubCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnSubCategory.setAdapter(adpSubCategory);
                    spnSubCategory.setSelection(0);

                    spnSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            strSubCategory = (String) spnSubCategory.getSelectedItem();
                        }

                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), R.string.msg_SubCategory, Toast.LENGTH_LONG).show();
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

        /* Treat Create */
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Verification: user choose valid option? */
                if (strTransaction.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Transaction, Toast.LENGTH_LONG).show();
                    return;
                }

                /* Verification: user choose valid option? */
                if (strType.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Type, Toast.LENGTH_LONG).show();
                    return;
                }

                /* Verification: user choose valid option? */
                if (strClass.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Class, Toast.LENGTH_LONG).show();
                    return;
                }

                /* Verification: user choose valid option? */
                if (strCategory.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Category, Toast.LENGTH_LONG).show();
                    return;
                }

                /* Verification: user choose valid option? */
                if (strSubCategory.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_SubCategory, Toast.LENGTH_LONG).show();
                    return;
                }

                /* Format data informed by user */
                switch (userLanguage) {
                    case "EN":
                        strTransactionEN = strTransaction;
                        strTypeEN = strType;
                        strClassEN = strClass;
                        strCategoryEN = strCategory;
                        strSubCategoryEN = strSubCategory;
                        break;
                    case "SP":
                        strTransactionSP = strTransaction;
                        strTypeSP = strType;
                        strClassSP = strClass;
                        strCategorySP = strCategory;
                        strSubCategorySP = strSubCategory;
                        break;
                    case "PT":
                        strTransactionPT = strTransaction;
                        strTypePT = strType;
                        strClassPT = strClass;
                        strCategoryPT = strCategory;
                        strSubCategoryPT = strSubCategory;
                        break;
                    default:
                        strTransactionEN = strTransaction;
                        strTypeEN = strType;
                        strClassEN = strClass;
                        strCategoryEN = strCategory;
                        strSubCategoryEN = strSubCategory;
                        break;
                }

                /* TODO: VERIFY IF ITEM ALREADY EXISTS */
                /* Check if data from user already exists in APP database
                if (listTransaction.contains(strTransaction)
                  & listType.contains(strType)
                  & listClass.contains(strClass)
                  & listCategory.contains(strCategory)
                  & listSubCategory.contains(strSubCategory)){
                        Toast.makeText(getApplicationContext(), com.prosperday.mydracma.R.string.msg_error_duplicated, Toast.LENGTH_LONG).show();
                        return;
                }
                */

                /* Update APP database */
                progressBar.setVisibility(View.VISIBLE);

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

                progressBar.setVisibility(View.INVISIBLE);

                if (returnFirebase) {
                    Toast.makeText(getApplicationContext(), R.string.msg_created, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.msg_error_firebase, Toast.LENGTH_LONG).show();
                }

                /* Update array list spinner base */
                listTransaction.add(strTransaction);
                listType.add(strType);
                listClass.add(strClass);
                listCategory.add(strCategory);
                listSubCategory.add(strSubCategory);

                clean_screen_fields();
                finish();
            }
        });
    }

    /* Clean screen field */
    private void clean_screen_fields() {
        spnTransaction.setSelection(0);
        spnType.setSelection(0);
        spnClass.setSelection(0);
        spnCategory.setSelection(0);
        spnSubCategory.setSelection(0);
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
