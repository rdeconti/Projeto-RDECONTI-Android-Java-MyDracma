package com.prosperday.mydracma.app_AccountMasterOutcome;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prosperday.mydracma.app_Support.appSupportCurrentDateTime;
import com.prosperday.mydracma.app_Support.appSupportHandlingExceptionError;

import static com.prosperday.mydracma.app_AccountMaster.appAccountMasterReportAdapter.chooseFromUser;
import static com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization.myUserCode;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myDateTime;

public class appAccountMasterOutcomeFirebaseMaintain {

    public static boolean appAccountMasterFirebaseReset() {

        try {

            DatabaseReference firebaseAccountMaster = FirebaseDatabase.getInstance().getReference("AccountMaster");
            firebaseAccountMaster.removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountMasterFirebaseDelete() {

        try {

            DatabaseReference firebaseAccountMaster = FirebaseDatabase.getInstance().getReference("AccountMaster");
            firebaseAccountMaster.child(chooseFromUser).removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountMasterFirebaseUpdate(
            String strTransaction,
            String strCategory,
            String strSubCategory,
            String strName,
            String strStartDate,
            String strFinishDate,
            String strExpireDate,
            String strInitialValue,
            String strLimitValue,
            String strTaxes,
            String strInterest) {

        try {

            DatabaseReference firebaseAccountMaster = FirebaseDatabase.getInstance().getReference("AccountMaster");

            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterTransaction").setValue(strTransaction);
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterCategory").setValue(strCategory);
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterSubCategory").setValue(strSubCategory);
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterName").setValue(strName);
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterStartDate").setValue(strStartDate);
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterFinishDate").setValue(strFinishDate);
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterExpireDate").setValue(strExpireDate);
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterInitialValue").setValue(strInitialValue);
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterLimitValue").setValue(strLimitValue);
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterTaxes").setValue(strTaxes);
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterInterest").setValue(strInterest);

            new appSupportCurrentDateTime();
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterUpdatedBy").setValue(myUserCode);
            firebaseAccountMaster.child(appAccountMasterOutcomeReportAdapter.chooseFromUser).child("AccountMasterUpdatedOn").setValue(myDateTime);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appAccountMasterFirebaseCreate(
            String strTransaction,
            String strCategory,
            String strSubCategory,
            String strName,
            String strStartDate,
            String strFinishDate,
            String strExpireDate,
            String strInitialValue,
            String strLimitValue,
            String strTaxes,
            String strInterest) {

        try {

            DatabaseReference firebaseAccountMaster = FirebaseDatabase.getInstance().getReference("AccountMaster");
            String firebaseKey = firebaseAccountMaster.push().getKey();

            firebaseAccountMaster.child(firebaseKey).child("AccountMasterKey").setValue(firebaseKey);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterTransaction").setValue(strTransaction);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterCategory").setValue(strCategory);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterSubCategory").setValue(strSubCategory);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterName").setValue(strName);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterStartDate").setValue(strStartDate);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterFinishDate").setValue(strFinishDate);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterExpireDate").setValue(strExpireDate);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterInitialValue").setValue(strInitialValue);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterLimitValue").setValue(strLimitValue);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterTaxes").setValue(strTaxes);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterInterest").setValue(strInterest);

            new appSupportCurrentDateTime();
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterUpdatedBy").setValue(myUserCode);
            firebaseAccountMaster.child(firebaseKey).child("AccountMasterUpdatedOn").setValue(myDateTime);

            Log.w("PROCESS CHECK ", "appAccountMasterFirebaseCreate: @@@@@@@@@@ "+ strName);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }
}
