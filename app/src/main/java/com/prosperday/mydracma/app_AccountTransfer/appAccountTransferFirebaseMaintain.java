package com.prosperday.mydracma.app_AccountTransfer;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prosperday.mydracma.app_Support.appSupportCurrentDateTime;
import com.prosperday.mydracma.app_Support.appSupportHandlingExceptionError;

import static com.prosperday.mydracma.app_AccountTransfer.appAccountTransferReportAdapter.chooseFromUser;
import static com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization.myUserCode;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myDateTime;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myHour;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myMinute;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.mySecond;

public class appAccountTransferFirebaseMaintain {

    private static final String TAG = "TransferMaintain ";

    public static boolean appAccountTransferFirebaseReset() {

        try {

            DatabaseReference firebaseAccountTransfer = FirebaseDatabase.getInstance().getReference("AccountTransfer");
            firebaseAccountTransfer.removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountTransferFirebaseDelete() {

        try {

            DatabaseReference firebaseAccountTransfer = FirebaseDatabase.getInstance().getReference("AccountTransfer");
            firebaseAccountTransfer.child(chooseFromUser).removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountTransferFirebaseUpdate(
            String strYear,
            String strMonth,
            String strDay,
            String strAccountFrom,
            String strAccountTo,
            String strDatePlanned,
            String strDateRealized,
            String strValuePlanned,
            String strValueRealized,
            String strPeriod,
            String strOccurrence,
            String strReminder,
            String strAutomatic) {

        try {

            DatabaseReference firebaseAccountTransfer = FirebaseDatabase.getInstance().getReference("AccountTransfer");

            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferYear").setValue(strYear);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferMonth").setValue(strMonth);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferDay").setValue(strDay);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferHour").setValue(myHour);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferMinute").setValue(myMinute);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferSecond").setValue(mySecond);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferAccountFrom").setValue(strAccountFrom);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferAccountTo").setValue(strAccountTo);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferDatePlanned").setValue(strDatePlanned);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferDateRealized").setValue(strDateRealized);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferValuePlanned").setValue(strValuePlanned);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferValueRealized").setValue(strValueRealized);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferPeriod").setValue(strPeriod);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferOccurrence").setValue(strOccurrence);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferReminder").setValue(strReminder);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferAutomatic").setValue(strAutomatic);

            new appSupportCurrentDateTime();
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferUpdatedOn").setValue(myDateTime);
            firebaseAccountTransfer.child(appAccountTransferReportAdapter.chooseFromUser).child("AccountTransferUpdatedBy").setValue(myUserCode);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountTransferFirebaseCreate(
            String strYear,
            String strMonth,
            String strDay,
            String strAccountFrom,
            String strAccountTo,
            String strDatePlanned,
            String strDateRealized,
            String strValuePlanned,
            String strValueRealized,
            String strPeriod,
            String strOccurrence,
            String strReminder,
            String strAutomatic) {

        try {

            DatabaseReference firebaseAccountTransfer = FirebaseDatabase.getInstance().getReference("AccountTransfer");
            String firebaseKey = firebaseAccountTransfer.push().getKey();

            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferKey").setValue(firebaseKey);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferYear").setValue(strYear);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferMonth").setValue(strMonth);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferDay").setValue(strDay);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferHour").setValue(myHour);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferMinute").setValue(myMinute);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferSecond").setValue(mySecond);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferAccountFrom").setValue(strAccountFrom);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferAccountTo").setValue(strAccountTo);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferDatePlanned").setValue(strDatePlanned);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferDateRealized").setValue(strDateRealized);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferValuePlanned").setValue(strValuePlanned);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferValueRealized").setValue(strValueRealized);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferPeriod").setValue(strPeriod);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferOccurrence").setValue(strOccurrence);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferReminder").setValue(strReminder);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferAutomatic").setValue(strAutomatic);

            new appSupportCurrentDateTime();
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferUpdatedOn").setValue(myDateTime);
            firebaseAccountTransfer.child(firebaseKey).child("AccountTransferUpdatedBy").setValue(myUserCode);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }
}
