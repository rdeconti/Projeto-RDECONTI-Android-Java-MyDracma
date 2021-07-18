package com.prosperday.mydracma.app_AccountTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prosperday.mydracma.app_Support.appSupportCurrentDateTime;
import com.prosperday.mydracma.app_Support.appSupportHandlingExceptionError;

import static com.prosperday.mydracma.app_AccountTransaction.appAccountTransactionReportAdapter.chooseFromUser;
import static com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization.myUserCode;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myDateTime;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myHour;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myMinute;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.mySecond;

public class appAccountTransactionFirebaseMaintain {

    private static final String TAG = "TransactionMaintain ";

    public static boolean appAccountTransactionFirebaseReset() {

        try {

            DatabaseReference firebaseAccountTransaction = FirebaseDatabase.getInstance().getReference("AccountTransaction");
            firebaseAccountTransaction.removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountTransactionFirebaseDelete() {

        try {

            DatabaseReference firebaseAccountTransaction = FirebaseDatabase.getInstance().getReference("AccountTransaction");
            firebaseAccountTransaction.child(chooseFromUser).removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountTransactionFirebaseUpdate(
            String strYear,
            String strMonth,
            String strDay,
            String strTransaction,
            String strCategory,
            String strSubCategory,
            String strAccount,
            String strDatePlanned,
            String strDateRealized,
            String strValuePlanned,
            String strValueRealized,
            String strPeriod,
            String strOccurrence,
            String strReminder,
            String strAutomatic) {

        try {

            DatabaseReference firebaseAccountTransaction = FirebaseDatabase.getInstance().getReference("AccountTransaction");

            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionYear").setValue(strYear);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionMonth").setValue(strMonth);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionDay").setValue(strDay);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionHour").setValue(myHour);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionMinute").setValue(myMinute);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionSecond").setValue(mySecond);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionTransaction").setValue(strTransaction);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionCategory").setValue(strCategory);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionSubCategory").setValue(strSubCategory);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionAccount").setValue(strAccount);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionDatePlanned").setValue(strDatePlanned);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionDateRealized").setValue(strDateRealized);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionValuePlanned").setValue(strValuePlanned);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionValueRealized").setValue(strValueRealized);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionPeriod").setValue(strPeriod);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionOccurrence").setValue(strOccurrence);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionReminder").setValue(strReminder);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionAutomatic").setValue(strAutomatic);

            new appSupportCurrentDateTime();
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("accountTransactionUpdatedOn").setValue(myDateTime);
            firebaseAccountTransaction.child(appAccountTransactionReportAdapter.chooseFromUser).child("AccountTransactionUpdatedBy").setValue(myUserCode);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountTransactionFirebaseCreate(
            String strYear,
            String strMonth,
            String strDay,
            String strTransaction,
            String strCategory,
            String strSubCategory,
            String strAccount,
            String strDatePlanned,
            String strDateRealized,
            String strValuePlanned,
            String strValueRealized,
            String strPeriod,
            String strOccurrence,
            String strReminder,
            String strAutomatic) {

        try {

            DatabaseReference firebaseAccountTransaction = FirebaseDatabase.getInstance().getReference("AccountTransaction");
            String firebaseKey = firebaseAccountTransaction.push().getKey();

            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionKey").setValue(firebaseKey);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionYear").setValue(strYear);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionMonth").setValue(strMonth);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionDay").setValue(strDay);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionHour").setValue(myHour);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionMinute").setValue(myMinute);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionSecond").setValue(mySecond);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionTransaction").setValue(strTransaction);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionCategory").setValue(strCategory);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionSubCategory").setValue(strSubCategory);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionAccount").setValue(strAccount);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionDatePlanned").setValue(strDatePlanned);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionDateRealized").setValue(strDateRealized);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionValuePlanned").setValue(strValuePlanned);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionValueRealized").setValue(strValueRealized);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionPeriod").setValue(strPeriod);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionOccurrence").setValue(strOccurrence);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionReminder").setValue(strReminder);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionAutomatic").setValue(strAutomatic);

            new appSupportCurrentDateTime();
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionUpdatedOn").setValue(myDateTime);
            firebaseAccountTransaction.child(firebaseKey).child("AccountTransactionUpdatedBy").setValue(myUserCode);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }
}
