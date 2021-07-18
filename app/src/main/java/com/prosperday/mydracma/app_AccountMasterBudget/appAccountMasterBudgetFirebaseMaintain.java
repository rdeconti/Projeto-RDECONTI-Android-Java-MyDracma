package com.prosperday.mydracma.app_AccountMasterBudget;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prosperday.mydracma.app_Support.appSupportCurrentDateTime;
import com.prosperday.mydracma.app_Support.appSupportHandlingExceptionError;

import static com.prosperday.mydracma.app_AccountTransfer.appAccountTransferReportAdapter.chooseFromUser;
import static com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization.myUserCode;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myDateTime;

public class appAccountMasterBudgetFirebaseMaintain {

    public static boolean appBudgetFirebaseReset() {

        try {

            DatabaseReference firebaseBudget = FirebaseDatabase.getInstance().getReference("Budget");
            firebaseBudget.removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appBudgetFirebaseDelete() {

        try {

            DatabaseReference firebaseBudget = FirebaseDatabase.getInstance().getReference("Budget");
            firebaseBudget.child(chooseFromUser).removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appBudgetFirebaseUpdate(
            String strYear,
            String strMonth,
            String strTransaction,
            String strCategory,
            String strSubCategory,
            String strPlanned,
            String strRealized) {

        try {

            DatabaseReference firebaseBudget = FirebaseDatabase.getInstance().getReference("Budget");

            firebaseBudget.child(appAccountMasterBudgetReportAdapter.chooseFromUser).child("BudgetYear").setValue(strYear);
            firebaseBudget.child(appAccountMasterBudgetReportAdapter.chooseFromUser).child("BudgetMonth").setValue(strMonth);
            firebaseBudget.child(appAccountMasterBudgetReportAdapter.chooseFromUser).child("BudgetValuePlanned").setValue(strPlanned);
            firebaseBudget.child(appAccountMasterBudgetReportAdapter.chooseFromUser).child("BudgetValueRealized").setValue(strRealized);
            firebaseBudget.child(appAccountMasterBudgetReportAdapter.chooseFromUser).child("BudgetTransaction").setValue(strTransaction);
            firebaseBudget.child(appAccountMasterBudgetReportAdapter.chooseFromUser).child("BudgetCategory").setValue(strCategory);
            firebaseBudget.child(appAccountMasterBudgetReportAdapter.chooseFromUser).child("BudgetSubCategory").setValue(strSubCategory);
            firebaseBudget.child(appAccountMasterBudgetReportAdapter.chooseFromUser).child("BudgetUpdateBy").setValue(myUserCode);

            new appSupportCurrentDateTime();
            firebaseBudget.child(appAccountMasterBudgetReportAdapter.chooseFromUser).child("BudgetUpdatedOn").setValue(myDateTime);
            firebaseBudget.child(appAccountMasterBudgetReportAdapter.chooseFromUser).child("BudgetUpdateBy").setValue(myUserCode);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appBudgetFirebaseCreate(
            String strYear,
            String strMonth,
            String strTransaction,
            String strCategory,
            String strSubCategory,
            String strPlanned,
            String strRealized) {

        try {

            DatabaseReference firebaseBudget = FirebaseDatabase.getInstance().getReference("Budget");
            String firebaseKey = firebaseBudget.push().getKey();

            firebaseBudget.child(firebaseKey).child("BudgetKey").setValue(firebaseKey);
            firebaseBudget.child(firebaseKey).child("BudgetYear").setValue(strYear);
            firebaseBudget.child(firebaseKey).child("BudgetMonth").setValue(strMonth);
            firebaseBudget.child(firebaseKey).child("BudgetValuePlanned").setValue(strPlanned);
            firebaseBudget.child(firebaseKey).child("BudgetValueRealized").setValue(strRealized);
            firebaseBudget.child(firebaseKey).child("BudgetTransaction").setValue(strTransaction);
            firebaseBudget.child(firebaseKey).child("BudgetCategory").setValue(strCategory);
            firebaseBudget.child(firebaseKey).child("BudgetSubCategory").setValue(strSubCategory);

            new appSupportCurrentDateTime();
            firebaseBudget.child(firebaseKey).child("BudgetUpdatedOn").setValue(myDateTime);
            firebaseBudget.child(firebaseKey).child("BudgetUpdateBy").setValue(myUserCode);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }
}