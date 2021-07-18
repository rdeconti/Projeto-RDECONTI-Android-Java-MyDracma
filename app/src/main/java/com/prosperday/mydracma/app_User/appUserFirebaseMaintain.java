package com.prosperday.mydracma.app_User;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prosperday.mydracma.app_Support.appSupportCurrentDateTime;
import com.prosperday.mydracma.app_Support.appSupportHandlingExceptionError;

import static com.prosperday.mydracma.app_AccountTransfer.appAccountTransferReportAdapter.chooseFromUser;
import static com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization.myUserCode;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myDateTime;

public class appUserFirebaseMaintain {

    private static final String TAG = "UserMaintain ";

    public static boolean appUserFirebaseReset() {

        try {

            DatabaseReference firebaseUser = FirebaseDatabase.getInstance().getReference("User");
            firebaseUser.removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appUserFirebaseDelete() {

        try {

            DatabaseReference firebaseUser = FirebaseDatabase.getInstance().getReference("User");
            firebaseUser.child(chooseFromUser).removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appUserFirebaseUpdate(
            String strCode,
            String strName,
            String strPhone,
            String strBirthday,
            String strLanguage,
            String strCurrency,
            String strSessionTime,
            String strAdvertising,
            String strTimeFormat,
            String strDateFormat,
            String strDecimalFormat) {

        try {

            DatabaseReference firebaseUser = FirebaseDatabase.getInstance().getReference("User");

            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataCode").setValue(strCode);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataName").setValue(strName);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataPhone").setValue(strPhone);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataBirthday").setValue(strBirthday);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataLanguage").setValue(strLanguage);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataCurrency").setValue(strCurrency);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataSessionTime").setValue(strSessionTime);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataAdvertising").setValue(strAdvertising);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataTimeFormat").setValue(strDateFormat);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataDateFormat").setValue(strDateFormat);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataDecimalFormat").setValue(strDecimalFormat);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserDataDecimalFormat").setValue(strDecimalFormat);

            new appSupportCurrentDateTime();
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserUpdatedOn").setValue(myDateTime);
            firebaseUser.child(appUserReportAdapter.chooseFromUser).child("UserUpdateBy").setValue(myUserCode);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appUserFirebaseCreate(
            String strCode,
            String strName,
            String strPhone,
            String strBirthday,
            String strLanguage,
            String strCurrency,
            String strSessionTime,
            String strAdvertising,
            String strTimeFormat,
            String strDateFormat,
            String strDecimalFormat) {

        try {

            DatabaseReference firebaseUser = FirebaseDatabase.getInstance().getReference("User");
            String firebaseKey = firebaseUser.push().getKey();

            firebaseUser.child(firebaseKey).child("UserKey").setValue(firebaseKey);
            firebaseUser.child(firebaseKey).child("UserDataCode").setValue(strCode);
            firebaseUser.child(firebaseKey).child("UserDataName").setValue(strName);
            firebaseUser.child(firebaseKey).child("UserDataPhone").setValue(strPhone);
            firebaseUser.child(firebaseKey).child("UserDataBirthday").setValue(strBirthday);
            firebaseUser.child(firebaseKey).child("UserDataLanguage").setValue(strLanguage);
            firebaseUser.child(firebaseKey).child("UserDataCurrency").setValue(strCurrency);
            firebaseUser.child(firebaseKey).child("UserDataSessionTime").setValue(strSessionTime);
            firebaseUser.child(firebaseKey).child("UserDataAdvertising").setValue(strAdvertising);
            firebaseUser.child(firebaseKey).child("UserDataTimeFormat").setValue(strTimeFormat);
            firebaseUser.child(firebaseKey).child("UserDataDateFormat").setValue(strDateFormat);
            firebaseUser.child(firebaseKey).child("UserDataDecimalFormat").setValue(strDecimalFormat);

            new appSupportCurrentDateTime();
            firebaseUser.child(firebaseKey).child("UserUpdatedOn").setValue(myDateTime);
            firebaseUser.child(firebaseKey).child("UserUpdateBy").setValue(myUserCode);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }
}
