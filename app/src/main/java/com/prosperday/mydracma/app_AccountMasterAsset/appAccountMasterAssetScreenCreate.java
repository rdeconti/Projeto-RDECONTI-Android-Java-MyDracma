package com.prosperday.mydracma.app_AccountMasterAsset;

import android.content.Context;
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
import android.widget.EditText;
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
import com.prosperday.mydracma.app_AccountMasterChart.appAccountMasterChartFirebaseModel;
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

public class appAccountMasterAssetScreenCreate extends AppCompatActivity {

    public static Context currentContext;
    ArrayAdapter<String> adpTransaction;
    ArrayAdapter<String> adpCategory;
    ArrayAdapter<String> adpSubCategory;
    List<String> listTransaction;
    List<String> listCategory;
    List<String> listSubCategory;
    private ProgressBar progressBar;
    private Spinner spnTransaction;
    private Spinner spnCategory;
    private Spinner spnSubCategory;
    private String strTransaction;
    private String strCategory;
    private String strSubCategory;
    private String strTransactionEN;
    private String strCategoryEN;
    private String strSubCategoryEN;
    private String strTransactionSP;
    private String strCategorySP;
    private String strSubCategorySP;
    private String strTransactionPT;
    private String strCategoryPT;
    private String strSubCategoryPT;
    private EditText inputName;
    private EditText inputStartDate;
    private EditText inputFinishDate;
    private EditText inputExpireDate;
    private EditText inputInitialValue;
    private EditText inputLimitValue;
    private EditText inputTaxes;
    private EditText inputInterest;
    private String strName;
    private String strStartDate;
    private String strFinishDate;
    private String strExpireDate;
    private String strInitialValue;
    private String strLimitValue;
    private String strTaxes;
    private String strInterest;

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
        setContentView(R.layout.app_account_master_screen_create);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnCreate = findViewById(R.id.btnCreate);

        /* Set progress bar */
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        /* Set Fields */
        inputName = findViewById(R.id.editTextName);
        inputStartDate = findViewById(R.id.editTextStartDate);
        inputFinishDate = findViewById(R.id.editTextFinishDate);
        inputExpireDate = findViewById(R.id.editTextExpireDate);
        inputInitialValue = findViewById(R.id.editTextInitialValue);
        inputLimitValue = findViewById(R.id.editTextLimitValue);
        inputTaxes = findViewById(R.id.editTextTaxes);
        inputInterest = findViewById(R.id.editTextInterest);

        /* Set progress bar */
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        /* Create Array list to spinners */
        listTransaction = new ArrayList<>();
        listCategory = new ArrayList<>();
        listSubCategory = new ArrayList<>();

        /* Get spinner data from firebase */
        DatabaseReference firebaseAccountChart = FirebaseDatabase.getInstance().getReference("AccountChart");

        /* Obtain data to recycler view */
        firebaseAccountChart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTransaction.clear();
                listCategory.clear();
                listSubCategory.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        appAccountMasterChartFirebaseModel snapshotDetails = dataSnapshot.getValue(appAccountMasterChartFirebaseModel.class);
                        switch (userLanguage) {
                            case "EN":
                                listTransaction.add(snapshotDetails.getAccountChartTransactionEN());
                                listCategory.add(snapshotDetails.getAccountChartCategoryEN());
                                listSubCategory.add(snapshotDetails.getAccountChartSubCategoryEN());
                                break;
                            case "SP":
                                listTransaction.add(snapshotDetails.getAccountChartTransactionSP());
                                listCategory.add(snapshotDetails.getAccountChartCategorySP());
                                listSubCategory.add(snapshotDetails.getAccountChartSubCategorySP());
                                break;
                            case "PT":
                                listTransaction.add(snapshotDetails.getAccountChartTransactionPT());
                                listCategory.add(snapshotDetails.getAccountChartCategoryPT());
                                listSubCategory.add(snapshotDetails.getAccountChartSubCategoryPT());
                                break;
                            default:
                                listTransaction.add(snapshotDetails.getAccountChartTransactionEN());
                                listCategory.add(snapshotDetails.getAccountChartCategoryEN());
                                listSubCategory.add(snapshotDetails.getAccountChartSubCategoryEN());
                                break;
                        }
                    }

                    /* Eliminate duplicated entries from lists */
                    HashSet<String> hashSet = new HashSet<String>();

                    hashSet.clear();
                    hashSet.addAll(listTransaction);
                    listTransaction.clear();
                    listTransaction.addAll(hashSet);

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
                    adpTransaction = new ArrayAdapter<>(appAccountMasterAssetScreenCreate.this, android.R.layout.simple_spinner_item, listTransaction);
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

                    /* Treat spinner Category */
                    spnCategory = findViewById(R.id.spnCategory);
                    adpCategory = new ArrayAdapter<>(appAccountMasterAssetScreenCreate.this, android.R.layout.simple_spinner_item, listCategory);
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
                    adpSubCategory = new ArrayAdapter<>(appAccountMasterAssetScreenCreate.this, android.R.layout.simple_spinner_item, listSubCategory);
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

                strName = inputName.getText().toString().trim();
                strStartDate = inputStartDate.getText().toString().trim();
                strFinishDate = inputFinishDate.getText().toString().trim();
                strExpireDate = inputExpireDate.getText().toString().trim();
                strInitialValue = inputInitialValue.getText().toString().trim();
                strLimitValue = inputLimitValue.getText().toString().trim();
                strTaxes = inputTaxes.getText().toString().trim();
                strInterest = inputInterest.getText().toString().trim();

                /* Verification: user choose valid option? */
                if (strTransaction.equals(getString(R.string.lbl_ChooseOneOption))) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Transaction, Toast.LENGTH_LONG).show();
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
                        strCategoryEN = strCategory;
                        strSubCategoryEN = strSubCategory;
                        break;
                    case "SP":
                        strTransactionSP = strTransaction;
                        strCategorySP = strCategory;
                        strSubCategorySP = strSubCategory;
                        break;
                    case "PT":
                        strTransactionPT = strTransaction;
                        strCategoryPT = strCategory;
                        strSubCategoryPT = strSubCategory;
                        break;
                    default:
                        strTransactionEN = strTransaction;
                        strCategoryEN = strCategory;
                        strSubCategoryEN = strSubCategory;
                        break;
                }

                if (TextUtils.isEmpty(strName)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Name, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strStartDate)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_StartDate, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strFinishDate)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_FinishDate, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strExpireDate)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_ExpireDate, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strInitialValue)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_InitialValue, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strLimitValue)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_LimitValue, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strTaxes)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Taxes, Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(strInterest)) {
                    Toast.makeText(getApplicationContext(), R.string.msg_Interest, Toast.LENGTH_LONG).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                boolean returnFirebase = appAccountMasterAssetFirebaseMaintain.appAccountMasterFirebaseCreate(
                        strTransaction,
                        strCategory,
                        strSubCategory,
                        strName,
                        strStartDate,
                        strFinishDate,
                        strExpireDate,
                        strInitialValue,
                        strLimitValue,
                        strTaxes,
                        strInterest);

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
        inputName.setText(null);
        inputStartDate.setText(null);
        inputFinishDate.setText(null);
        inputExpireDate.setText(null);
        inputInitialValue.setText(null);
        inputLimitValue.setText(null);
        inputTaxes.setText(null);
        inputInterest.setText(null);
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
