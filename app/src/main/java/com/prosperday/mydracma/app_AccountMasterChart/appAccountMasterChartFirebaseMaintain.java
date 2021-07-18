package com.prosperday.mydracma.app_AccountMasterChart;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prosperday.mydracma.app_Support.appSupportCurrentDateTime;
import com.prosperday.mydracma.app_Support.appSupportHandlingExceptionError;

import static com.prosperday.mydracma.app_AccountMasterChart.appAccountMasterChartReportAdapter.chooseFromUser;
import static com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization.myUserCode;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myDateTime;

public class appAccountMasterChartFirebaseMaintain {

    public static boolean appAccountChartFirebaseReset() {

        try {

            DatabaseReference firebaseAccountChart = FirebaseDatabase.getInstance().getReference("AccountChart");
            firebaseAccountChart.removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountChartFirebaseDelete() {

        try {

            DatabaseReference firebaseAccountChart = FirebaseDatabase.getInstance().getReference("AccountChart");
            firebaseAccountChart.child(chooseFromUser).removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountChartFirebaseUpdate(
            String strTransactionEN,
            String strTypeEN,
            String strClassEN,
            String strCategoryEN,
            String strSubCategoryEN,
            String strTransactionSP,
            String strTypeSP,
            String strClassSP,
            String strCategorySP,
            String strSubCategorySP,
            String strTransactionPT,
            String strTypePT,
            String strClassPT,
            String strCategoryPT,
            String strSubCategoryPT) {

        try {

            DatabaseReference firebaseAccountChart = FirebaseDatabase.getInstance().getReference("AccountChart");
            firebaseAccountChart.child(chooseFromUser).child("AccountChartTransactionEN").setValue(strTransactionEN);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartTypeEN").setValue(strTypeEN);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartClassEN").setValue(strClassEN);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartCategoryEN").setValue(strCategoryEN);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartSubCategoryEN").setValue(strSubCategoryEN);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartTransactionSP").setValue(strTransactionSP);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartTypeSP").setValue(strTypeSP);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartClassSP").setValue(strClassSP);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartCategorySP").setValue(strCategorySP);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartSubCategorySP").setValue(strSubCategorySP);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartTransactionPT").setValue(strTransactionPT);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartTypePT").setValue(strTypePT);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartClassPT").setValue(strClassPT);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartCategoryPT").setValue(strCategoryPT);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartSubCategoryPT").setValue(strSubCategoryPT);

            new appSupportCurrentDateTime();
            firebaseAccountChart.child(chooseFromUser).child("AccountChartUpdatedBy").setValue(myUserCode);
            firebaseAccountChart.child(chooseFromUser).child("AccountChartUpdatedOn").setValue(myDateTime);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountChartFirebaseCreate(
            String strTransactionEN,
            String strTypeEN,
            String strClassEN,
            String strCategoryEN,
            String strSubCategoryEN,
            String strTransactionSP,
            String strTypeSP,
            String strClassSP,
            String strCategorySP,
            String strSubCategorySP,
            String strTransactionPT,
            String strTypePT,
            String strClassPT,
            String strCategoryPT,
            String strSubCategoryPT) {

        try {

            DatabaseReference firebaseAccountChart = FirebaseDatabase.getInstance().getReference("AccountChart");
            String firebaseKey = firebaseAccountChart.push().getKey();

            firebaseAccountChart.child(firebaseKey).child("AccountChartKey").setValue(firebaseKey);
            firebaseAccountChart.child(firebaseKey).child("AccountChartTransactionEN").setValue(strTransactionEN);
            firebaseAccountChart.child(firebaseKey).child("AccountChartTypeEN").setValue(strTypeEN);
            firebaseAccountChart.child(firebaseKey).child("AccountChartClassEN").setValue(strClassEN);
            firebaseAccountChart.child(firebaseKey).child("AccountChartCategoryEN").setValue(strCategoryEN);
            firebaseAccountChart.child(firebaseKey).child("AccountChartSubCategoryEN").setValue(strSubCategoryEN);
            firebaseAccountChart.child(firebaseKey).child("AccountChartTransactionSP").setValue(strTransactionSP);
            firebaseAccountChart.child(firebaseKey).child("AccountChartTypeSP").setValue(strTypeSP);
            firebaseAccountChart.child(firebaseKey).child("AccountChartClassSP").setValue(strClassSP);
            firebaseAccountChart.child(firebaseKey).child("AccountChartCategorySP").setValue(strCategorySP);
            firebaseAccountChart.child(firebaseKey).child("AccountChartSubCategorySP").setValue(strSubCategorySP);
            firebaseAccountChart.child(firebaseKey).child("AccountChartTransactionPT").setValue(strTransactionPT);
            firebaseAccountChart.child(firebaseKey).child("AccountChartTypePT").setValue(strTypePT);
            firebaseAccountChart.child(firebaseKey).child("AccountChartClassPT").setValue(strClassPT);
            firebaseAccountChart.child(firebaseKey).child("AccountChartCategoryPT").setValue(strCategoryPT);
            firebaseAccountChart.child(firebaseKey).child("AccountChartSubCategoryPT").setValue(strSubCategoryPT);
            firebaseAccountChart.child(firebaseKey).child("AccountChartCreatedByUser").setValue("Yes");
            firebaseAccountChart.child(firebaseKey).child("AccountChartUsageCounter").setValue("0");

            new appSupportCurrentDateTime();
            firebaseAccountChart.child(firebaseKey).child("AccountChartUpdatedBy").setValue(myUserCode);
            firebaseAccountChart.child(firebaseKey).child("AccountChartUpdatedOn").setValue(myDateTime);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }
}

