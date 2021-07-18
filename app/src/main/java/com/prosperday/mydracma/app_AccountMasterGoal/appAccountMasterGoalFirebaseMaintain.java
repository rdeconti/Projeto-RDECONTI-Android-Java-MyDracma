package com.prosperday.mydracma.app_AccountMasterGoal;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.prosperday.mydracma.app_AccountMasterChart.appAccountMasterChartReportAdapter;
import com.prosperday.mydracma.app_Support.appSupportCurrentDateTime;
import com.prosperday.mydracma.app_Support.appSupportHandlingExceptionError;

import static com.prosperday.mydracma.app_AccountTransfer.appAccountTransferReportAdapter.chooseFromUser;
import static com.prosperday.mydracma.app_Firebase.appFirebaseCheckAuthorization.myUserCode;
import static com.prosperday.mydracma.app_Support.appSupportCurrentDateTime.myDateTime;

public class appAccountMasterGoalFirebaseMaintain {

    private static final String TAG = "GoalMaintain ";

    public static boolean appGoalFirebaseReset() {

        try {

            DatabaseReference firebaseGoal = FirebaseDatabase.getInstance().getReference("Goal");
            firebaseGoal.removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appGoalFirebaseDelete() {

        try {

            DatabaseReference firebaseGoal = FirebaseDatabase.getInstance().getReference("Goal");
            firebaseGoal.child(chooseFromUser).removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appGoalFirebaseUpdate(
            String strName,
            String strType,
            String strDatePlanned,
            String strDateRealized,
            String strValuePlanned,
            String strValueRealized,
            String strPriority,
            String strSavingRate) {

        try {

            DatabaseReference firebaseGoal = FirebaseDatabase.getInstance().getReference("Goal");

            firebaseGoal.child(appAccountMasterChartReportAdapter.chooseFromUser).child("GoalName").setValue(strName);
            firebaseGoal.child(appAccountMasterChartReportAdapter.chooseFromUser).child("GoalType").setValue(strType);
            firebaseGoal.child(appAccountMasterChartReportAdapter.chooseFromUser).child("GoalDatePlanned").setValue(strDatePlanned);
            firebaseGoal.child(appAccountMasterChartReportAdapter.chooseFromUser).child("GoalDateRealized").setValue(strDateRealized);
            firebaseGoal.child(appAccountMasterChartReportAdapter.chooseFromUser).child("GoalValuePlanned").setValue(strValuePlanned);
            firebaseGoal.child(appAccountMasterChartReportAdapter.chooseFromUser).child("GoalValueRealized").setValue(strValueRealized);
            firebaseGoal.child(appAccountMasterChartReportAdapter.chooseFromUser).child("GoalPriority").setValue(strPriority);
            firebaseGoal.child(appAccountMasterChartReportAdapter.chooseFromUser).child("GoalSavingRate").setValue(strSavingRate);

            new appSupportCurrentDateTime();
            firebaseGoal.child(appAccountMasterChartReportAdapter.chooseFromUser).child("GoalUpdatedOn").setValue(myDateTime);
            firebaseGoal.child(appAccountMasterChartReportAdapter.chooseFromUser).child("GoalUpdateBy").setValue(myUserCode);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }

    public static boolean appGoalFirebaseCreate(
            String strName,
            String strType,
            String strDatePlanned,
            String strDateRealized,
            String strValuePlanned,
            String strValueRealized,
            String strPriority,
            String strSavingRate) {

        try {

            DatabaseReference firebaseGoal = FirebaseDatabase.getInstance().getReference("Goal");
            String firebaseKey = firebaseGoal.push().getKey();

            firebaseGoal.child(firebaseKey).child("GoalKey").setValue(firebaseKey);
            firebaseGoal.child(firebaseKey).child("GoalName").setValue(strName);
            firebaseGoal.child(firebaseKey).child("GoalType").setValue(strType);
            firebaseGoal.child(firebaseKey).child("GoalDatePlanned").setValue(strDatePlanned);
            firebaseGoal.child(firebaseKey).child("GoalDateRealized").setValue(strDateRealized);
            firebaseGoal.child(firebaseKey).child("GoalValuePlanned").setValue(strValuePlanned);
            firebaseGoal.child(firebaseKey).child("GoalValueRealized").setValue(strValueRealized);
            firebaseGoal.child(firebaseKey).child("GoalPriority").setValue(strPriority);
            firebaseGoal.child(firebaseKey).child("GoalSavingRate").setValue(strSavingRate);

            new appSupportCurrentDateTime();
            firebaseGoal.child(firebaseKey).child("GoalUpdatedOn").setValue(myDateTime);
            firebaseGoal.child(firebaseKey).child("GoalUpdateBy").setValue(myUserCode);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new appSupportHandlingExceptionError(ClassName, error);
            return false;

        }
    }
}
