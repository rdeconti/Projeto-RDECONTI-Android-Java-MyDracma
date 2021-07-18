package com.prosperday.mydracma.app_Tool;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
import com.prosperday.mydracma.app_Support.appSupportCurrentDateTime;
import com.prosperday.mydracma.app_Support.appSupportHandlingDatabaseError;
import com.prosperday.mydracma.app_Support.appSupportHandlingExceptionError;
import com.prosperday.mydracma.app_Support.appSupportMenuGeneral;
import com.prosperday.mydracma.app_User.appUserFirebaseMaintain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myExpireDate;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myFinishDate;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myPlannedDate;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myRealizedDate;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myStartDate;

public class appToolStep06Template extends AppCompatActivity {

    public static Context currentContext;

    private DatabaseReference firebaseAccountChart;
    private DatabaseReference firebaseAccountMaster;
    private DatabaseReference firebaseAccountTransaction;
    private DatabaseReference firebaseAccountTransfer;
    private DatabaseReference firebaseBudget;
    private DatabaseReference firebaseGoal;
    private DatabaseReference firebaseUser;

    private String stepProcessing;
    private String errorDetected;

    private String strTransaction;
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

    private String strName;

    private String strDatePlanned;
    private String strDateRealized;
    private String strValuePlanned;
    private String strValueRealized;
    private String strYear;
    private String strMonth;

    private String strDay;
    private String strAccount;
    private String strPeriod;
    private String strOccurrence;
    private String strReminder;
    private String strAutomatic;

    private String strStartDate;
    private String strFinishDate;
    private String strExpireDate;
    private String strInitialValue;
    private String strLimitValue;
    private String strTaxes;
    private String strInterest;

    private String strType;
    private String strPriority;
    private String strSavingRate;

    private String strCode;
    private String strPhone;
    private String strBirthday;
    private String strLanguage;
    private String strCurrency;
    private String strSessionTime;
    private String strAdvertising;
    private String strTimeFormat;
    private String strDateFormat;
    private String strDecimalFormat;

    private int counterLoopDo;
    private int counterRegister;

    private int counterIncome;
    private int counterAsset;
    private int counterInvestment;
    private int counterOutcomeFinancial;
    private int counterOutcomeHealth;
    private int counterOutcomePet;
    private int counterOutcomeFeeding;
    private int counterOutcomeTransportation;
    private int counterOutcomeMobility;
    private int counterOutcomeHousing;
    private int counterOutcomeEducation;
    private int counterOutcomeEntertainment;
    private int counterOutcomePersonalCare;
    private int counterOutcomeSubscriptions;
    private int counterOutcomeCharityGifts;
    private int counterBudget;
    private int counterGoal;
    private int counterUser;

    private int counterPeriodDo;
    private int counterYear;
    private int counterMonth;
    private int counterDay;

    private int counterProperty;
    private int counterJewels;
    private int counterCar;
    private int counterDependents;
    private int counterResponsible;
    private int counterStudents;
    private int counterWorker;
    private int counterRetired;
    private int counterPet;

    private int fromYear;
    private int fromMonth;
    private int fromDay;
    private int toYear;
    private int toMonth;
    private int toDay;

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
        setContentView(R.layout.app_tool_step06_template);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnTemplate = findViewById(R.id.btnTemplate);

        /* Set progress bar */
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);

        /* TODO: Adjust screen to user inform quantities
        /* Set Questioner answers */
        fromYear = 2017;
        fromMonth = 1;
        fromDay = 1;
        toYear = 2018;
        toMonth = 12;
        toDay = 31;
        counterResponsible = 2;
        counterDependents = 4;
        counterStudents = 2;
        counterWorker = 2;
        counterRetired = 2;
        counterProperty = 5;
        counterJewels = 10;
        counterCar = 2;
        counterPet = 2;
        counterBudget = 24;
        counterGoal = 20;
        counterInvestment = 20;

        /* Set quantity of registers to be created during the periods */
        counterIncome = counterWorker + counterRetired;
        counterAsset = counterCar + counterProperty + counterJewels;
        counterOutcomeFinancial = counterInvestment * 2;
        counterOutcomeHealth = counterDependents + counterResponsible;
        counterOutcomePet = counterPet;
        counterOutcomeFeeding = 1;
        counterOutcomeTransportation = counterDependents + counterResponsible;
        counterOutcomeMobility = counterCar;
        counterOutcomeHousing = counterProperty;
        counterOutcomeEducation = counterStudents;
        counterOutcomeEntertainment = (counterDependents + counterResponsible) * 3;
        counterOutcomePersonalCare = counterDependents + counterResponsible;
        counterOutcomeSubscriptions = counterDependents + counterResponsible;
        counterOutcomeCharityGifts = counterDependents + counterResponsible;
        counterUser = counterDependents + counterResponsible;

        /* Treat Create Template - AsyncTask */
        btnTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateTemplate().execute();
            }
        });
    }

    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */

    public class CreateTemplate extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), R.string.msg_TemplateStarted, Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {

            /*  ---------- RESET APP DATA ---------- */

            /* Reset Account Chart */
            stepProcessing = "Reset Account Chart";
            boolean returnResetAccountChart = appAccountMasterChartFirebaseMaintain.appAccountChartFirebaseReset();
            if (!returnResetAccountChart) return ("Error");

            /* Reset Account Master */
            stepProcessing = "Reset Account Master";
            boolean returnResetAccountMaster = appAccountMasterFirebaseMaintain.appAccountMasterFirebaseReset();
            if (!returnResetAccountMaster) return ("Error");

            /* Reset Account Transaction */
            stepProcessing = "Reset Account Transaction";
            boolean returnResetAccountTransaction = appAccountTransactionFirebaseMaintain.appAccountTransactionFirebaseReset();
            if (!returnResetAccountTransaction) return ("Error");

            /* Reset Account Transfer */
            stepProcessing = "Reset Account Transfer";
            boolean returnResetAccountTransfer = appAccountTransferFirebaseMaintain.appAccountTransferFirebaseReset();
            if (!returnResetAccountTransfer) return ("Error");

            /* Reset Budget */
            stepProcessing = "Reset Budget";
            boolean returnResetBudget = appAccountMasterBudgetFirebaseMaintain.appBudgetFirebaseReset();
            if (!returnResetBudget) return ("Error");

            /* Reset Goal */
            stepProcessing = "Reset Goal";
            boolean returnResetGoal = appAccountMasterGoalFirebaseMaintain.appGoalFirebaseReset();
            if (!returnResetGoal) return ("Error");

            /* Reset User */
            stepProcessing = "Reset User";
            boolean returnResetUser = appUserFirebaseMaintain.appUserFirebaseReset();
            if (!returnResetUser) return ("Error");

            /*  ---------- CREATE APP DATA ---------- */

            /* Create Account Chart Standard */
            stepProcessing = "Create Account Chart";
            boolean returnCreateAccountChart = create_AccountChartStandard();
            if (!returnCreateAccountChart) return ("Error");

            /* Create Goal */
            stepProcessing = "Create Goal";
            boolean returnCreateGoal = create_Goal();
            if (!returnCreateGoal) return ("Error");

            /* Create User */
            stepProcessing = "Create User";
            boolean returnCreateUser = create_User();
            if (!returnCreateUser) return ("Error");

            errorDetected = "NoError";

            /* Set Firebase */
            DatabaseReference firebaseAccountChart = FirebaseDatabase.getInstance().getReference("AccountChart");

            /* Create Account Master, Income, Asset, Transaction from Account Chart */
            firebaseAccountChart.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshotAccountCategory) {
                    if (dataSnapshotAccountCategory.exists()) {
                        for (DataSnapshot readerSnapshot : dataSnapshotAccountCategory.getChildren()) {

                            strTransaction = (String) readerSnapshot.child("AccountChartTransactionEN").getValue();
                            strCategory = (String) readerSnapshot.child("AccountChartCategoryEN").getValue();
                            strSubCategory = (String) readerSnapshot.child("AccountChartSubCategoryEN").getValue();

                            Log.w("CHECK PROCESS ", "dataSnapshotAccountCategory: " + strTransaction + " " + strCategory);

                            stepProcessing = "Create Budget";
                            boolean returnCreateBudget = create_Budget();
                            if (!returnCreateBudget) {
                                errorDetected = "Error";
                                break;
                            }

                            if (strTransaction.equals("Investment")){
                                stepProcessing = "Create Investment";
                                boolean returnCreateInvestment = create_Investment();
                                if (!returnCreateInvestment) {
                                    errorDetected = "Error";
                                    break;
                                }
                            }

                            if (strTransaction.equals("Income")){
                                stepProcessing = "Create Income";
                                boolean returnCreateIncome = create_Income();
                                if (!returnCreateIncome) {
                                    errorDetected = "Error";
                                    break;
                                }
                            }

                            if (strTransaction.equals("Asset")){
                                stepProcessing = "Create Asset";
                                boolean returnCreateAsset = create_Asset();
                                if (!returnCreateAsset) {
                                    errorDetected = "Error";
                                    break;
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("Financial")) {
                                    stepProcessing = "Create Outcome Financial";
                                    boolean returnCreateOutcomeFinancial = create_OutcomeFinancial();
                                    if (!returnCreateOutcomeFinancial) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("Health")) {
                                    stepProcessing = "Create Outcome Health";
                                    boolean returnCreateOutcomeHealth = create_OutcomeHealth();
                                    if (!returnCreateOutcomeHealth) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("Pet")) {
                                    stepProcessing = "Create Outcome Pet";
                                    boolean returnCreateOutcomePet = create_OutcomePet();
                                    if (!returnCreateOutcomePet) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("Feeding")) {
                                    stepProcessing = "Create Outcome Feeding";
                                    boolean returnCreateOutcomeFeeding = create_OutcomeFeeding();
                                    if (!returnCreateOutcomeFeeding) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("Transportation")) {
                                    stepProcessing = "Create Outcome Transportation";
                                    boolean returnCreateOutcomeTransportation = create_OutcomeTransportation();
                                    if (!returnCreateOutcomeTransportation) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("Mobility")) {
                                    stepProcessing = "Create Outcome Mobility";
                                    boolean returnCreateOutcomeMobility = create_OutcomeMobility();
                                    if (!returnCreateOutcomeMobility) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("Housing")) {
                                    stepProcessing = "Create Outcome Housing";
                                    boolean returnCreateOutcomeHousing = create_OutcomeHousing();
                                    if (!returnCreateOutcomeHousing) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("Education")) {
                                    stepProcessing = "Create Outcome Education";
                                    boolean returnCreateOutcomeEducation = create_OutcomeEducation();
                                    if (!returnCreateOutcomeEducation) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("Entertainment")) {
                                    stepProcessing = "Create Outcome Entertainment";
                                    boolean returnCreateOutcomeEntertainment = create_OutcomeEntertainment();
                                    if (!returnCreateOutcomeEntertainment) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("PersonalCare")) {
                                    stepProcessing = "Create Outcome PersonalCare";
                                    boolean returnCreateOutcomePersonalCare = create_OutcomePersonalCare();
                                    if (!returnCreateOutcomePersonalCare) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("Subscriptions")) {
                                    stepProcessing = "Create Outcome Subscriptions";
                                    boolean returnCreateOutcomeSubscriptions = create_OutcomeSubscriptions();
                                    if (!returnCreateOutcomeSubscriptions) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }

                            if (strTransaction.equals("Outcome")){
                                if (strCategory.equals("CharityGifts")) {
                                    stepProcessing = "Create Outcome CharityGifts";
                                    boolean returnCreateOutcomeCharityGifts = create_OutcomeCharityGifts();
                                    if (!returnCreateOutcomeCharityGifts) {
                                        errorDetected = "Error";
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    new appSupportHandlingDatabaseError(this.getClass().getSimpleName(), databaseError);
                    errorDetected = "Error";
                }
            });

            /* Return from Asynchronous Task */
            if (errorDetected.equals("Error")){
                return "Error";
            }else{
                return "Finished";
            }
        }

        @Override
        protected void onPostExecute(String result){

            Log.w("CHECK PROCESS ", "Total of created registers: " + counterRegister );

            progressBar.setVisibility(View.INVISIBLE);
            if (result.equals("Error")){
                Toast.makeText(getApplicationContext(),stepProcessing + getString(R.string.lbl_Error),Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), R.string.msg_TemplateCreated,Toast.LENGTH_LONG).show();
            }

            finish();
        }
    }

    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */

    /* Create Goal */
    private boolean create_Goal() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseGoal = insert_Goal();
            if (!returnFirebaseGoal) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterGoal);
        return true;
    }

    /* Create User */
    private boolean create_User() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseUser = insert_User();
            if (!returnFirebaseUser) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterUser);
        return true;
    }

    /* Create Budget */
    private boolean create_Budget() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseBudget = insert_Budget();
            if (!returnFirebaseBudget) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterBudget);
        return true;
    }

    /* Create Investment */
    private boolean create_Investment() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseInvestment = insert_AccountMaster();
            if (!returnFirebaseInvestment) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterInvestment);
        return true;
    }

    /* Create Income */
    private boolean create_Income() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseIncome = insert_AccountTransaction();
            if (!returnFirebaseIncome) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterIncome);
        return true;
    }

    /* Create Asset */
    private boolean create_Asset() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseAsset = insert_AccountTransaction();
            if (!returnFirebaseAsset) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterAsset);
        return true;
    }

    /* Create Transaction Outcome Financial */
    private boolean create_OutcomeFinancial() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseOutcomeFinancial = insert_AccountTransaction();
            if (!returnFirebaseOutcomeFinancial) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterOutcomeFinancial);
        return true;
    }

    /* Create Transaction Outcome Health */
    private boolean create_OutcomeHealth() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseOutcomeHealth = insert_AccountTransaction();
            if (!returnFirebaseOutcomeHealth) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterOutcomeHealth);
        return true;
    }

    /* Create Transaction Outcome Pet */
    private boolean create_OutcomePet() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseOutcomePet = insert_AccountTransaction();
            if (!returnFirebaseOutcomePet) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterOutcomePet);
        return true;
    }

    /* Create Transaction Outcome Transportation */
    private boolean create_OutcomeTransportation() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseOutcomeTransportation = insert_AccountTransaction();
            if (!returnFirebaseOutcomeTransportation) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterOutcomeTransportation);
        return true;
    }

    /* Create Transaction Outcome Feeding */
    private boolean create_OutcomeFeeding() {
        counterLoopDo = 0;
        do {
        boolean returnFirebaseOutcomeFeeding = insert_AccountTransaction();
        if (!returnFirebaseOutcomeFeeding) {
            return false;
        }
        counterLoopDo++;
        } while (counterLoopDo <= counterOutcomeFeeding);
        return true;
    }

    /* Create Transaction Outcome Mobility */
    private boolean create_OutcomeMobility() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseOutcomeMobility = insert_AccountTransaction();
            if (!returnFirebaseOutcomeMobility) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterOutcomeMobility);
        return true;
    }

    /* Create Transaction Outcome Housing */
    private boolean create_OutcomeHousing() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseOutcomeHousing = insert_AccountTransaction();
            if (!returnFirebaseOutcomeHousing) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterOutcomeHousing);
        return true;
    }

    /* Create Transaction Outcome Education */
    private boolean create_OutcomeEducation() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseOutcomeEducation = insert_AccountTransaction();
            if (!returnFirebaseOutcomeEducation) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterOutcomeEducation);
        return true;
    }

    /* Create Transaction Outcome Entertainment */
    private boolean create_OutcomeEntertainment() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseOutcomeEntertainment = insert_AccountTransaction();
            if (!returnFirebaseOutcomeEntertainment) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterOutcomeEntertainment);
        return true;
    }

    /* Create Transaction Outcome Personal Care */
    private boolean create_OutcomePersonalCare() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseOutcomePersonalCare = insert_AccountTransaction();
            if (!returnFirebaseOutcomePersonalCare) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterOutcomePersonalCare);
        return true;
    }

    /* Create Transaction Outcome Subscription */
    private boolean create_OutcomeSubscriptions() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseOutcomeSubscriptions = insert_AccountTransaction();
            if (!returnFirebaseOutcomeSubscriptions) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterOutcomeSubscriptions);
        return true;
    }

    /* Create Transaction Outcome Charity / Gifts */
    private boolean create_OutcomeCharityGifts() {
        counterLoopDo = 0;
        do {
            boolean returnFirebaseOutcomeCharityGifts = insert_AccountTransaction();
            if (!returnFirebaseOutcomeCharityGifts) {
                return false;
            }
            counterLoopDo++;
        } while (counterLoopDo <= counterOutcomeCharityGifts);
        return true;
    }

    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */

    /* Create Account Chart */
    private boolean create_AccountChartStandard() {

        firebaseAccountChart = FirebaseDatabase.getInstance().getReference("AccountChart");

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
                boolean returnFirebaseAccountChart = appAccountMasterChartFirebaseMaintain.appAccountChartFirebaseCreate(
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

                if (!returnFirebaseAccountChart) return false;
                counterRegister++;
            }

        } catch (IOException error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

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
        return true;
    }

    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */

    /* Create Goal */
    private boolean insert_Goal() {

        new appSupportCurrentDateTime();

        strName = "Goal" + String.valueOf(counterLoopDo);
        strType = "Retirement";
        strDatePlanned = myStartDate;
        strDateRealized = myFinishDate;
        strValuePlanned = "10000";
        strValueRealized = "5000";
        strPriority = "High";
        strSavingRate = "10";

        boolean returnFirebaseGoal = appAccountMasterGoalFirebaseMaintain.appGoalFirebaseCreate(
                strName,
                strType,
                strDatePlanned,
                strDateRealized,
                strValuePlanned,
                strValueRealized,
                strPriority,
                strSavingRate);

        if (!returnFirebaseGoal) return false;
        counterRegister++;
        return true;
    }

    /* Create User */
    private boolean insert_User() {

        new appSupportCurrentDateTime();

        strCode = "user" + String.valueOf(counterLoopDo);
        strName = "userName" + String.valueOf(counterLoopDo);
        strPhone = "084150464";
        strBirthday = myStartDate;
        strLanguage = "EN";
        strCurrency = "US$";
        strSessionTime = "15 minutes";
        strAdvertising = "Yes";
        strTimeFormat = "24";
        strDateFormat = "YYYY-MM-DD";
        strDecimalFormat = "1.000,00";

        boolean returnFirebaseUser = appUserFirebaseMaintain.appUserFirebaseCreate(
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

        if (!returnFirebaseUser) return false;
        counterRegister++;
        return true;
    }

    /* Create Account Master */
    private boolean insert_AccountMaster() {

        new appSupportCurrentDateTime();

        strName = "Investment" + String.valueOf(counterLoopDo);
        strStartDate = myStartDate;
        strFinishDate = myFinishDate;
        strExpireDate = myExpireDate;
        strInitialValue = "200000";
        strLimitValue = "1000";
        strTaxes = "2";
        strInterest = "3";

        boolean returnFirebaseAccountMaster = appAccountMasterFirebaseMaintain.appAccountMasterFirebaseCreate(
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

        if (!returnFirebaseAccountMaster) return false;
        counterRegister++;
        return true;
    }

    /* Create Budget */
    private boolean insert_Budget() {

        new appSupportCurrentDateTime();

        counterPeriodDo = 0;
        counterYear = fromYear;
        counterMonth = fromMonth;

        do {

            strYear = String.valueOf(counterYear);
            strMonth = String.valueOf(counterMonth);
            strValuePlanned = "10000";
            strValueRealized = "15000";

            boolean returnFirebaseBudget = appAccountMasterBudgetFirebaseMaintain.appBudgetFirebaseCreate(
                    strYear,
                    strMonth,
                    strTransaction,
                    strCategory,
                    strSubCategory,
                    strValuePlanned,
                    strValueRealized);

            if (!returnFirebaseBudget) return false;
            counterRegister++;

            counterMonth++;

            if (counterMonth>12){
                counterYear++;
                counterMonth=1;
            }

            counterPeriodDo++;

        } while (counterPeriodDo <= counterBudget);

        return true;
    }

    /* Create Account Transaction */
    private boolean insert_AccountTransaction() {

        new appSupportCurrentDateTime();

        counterYear = fromYear;
        counterMonth = fromMonth;
        counterDay = fromDay;

        do {

            strYear = String.valueOf(counterYear);
            strMonth = String.valueOf(counterMonth);
            strDay = String.valueOf(counterDay);
            strAccount = "Cash";
            strDatePlanned = myPlannedDate;
            strDateRealized = myRealizedDate;
            strValuePlanned = "1000";
            strValueRealized = "1500";
            strPeriod = "Month";
            strOccurrence = "01";
            strReminder = "Yes";
            strAutomatic = "Yes";

            boolean returnFirebaseAccountTransaction = appAccountTransactionFirebaseMaintain.appAccountTransactionFirebaseCreate(
                    strYear,
                    strMonth,
                    strDay,
                    strTransaction,
                    strCategory,
                    strSubCategory,
                    strAccount,
                    strDatePlanned,
                    strDateRealized,
                    strValuePlanned,
                    strValueRealized,
                    strPeriod,
                    strOccurrence,
                    strReminder,
                    strAutomatic);

            if (!returnFirebaseAccountTransaction) return false;
            counterRegister++;

            counterDay++;

            if (counterDay>30){
                counterMonth++;
                counterDay=1;
                if (counterMonth>12){
                    counterYear++;
                    counterMonth=1;
                    counterDay=1;
                }
            }

            counterPeriodDo++;

        } while (counterPeriodDo <= counterBudget);

        return true;
    }

    /* ------------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------------ */

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
