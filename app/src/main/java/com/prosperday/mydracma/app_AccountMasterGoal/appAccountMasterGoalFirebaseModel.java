package com.prosperday.mydracma.app_AccountMasterGoal;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class appAccountMasterGoalFirebaseModel {

    private String GoalKey;
    private String GoalName;
    private String GoalType;
    private String GoalDatePlanned;
    private String GoalDateRealized;
    private String GoalValuePlanned;
    private String GoalValueRealized;
    private String GoalPriority;
    private String GoalSavingRate;
    private String GoalUpdatedBy;
    private String GoalUpdatedOn;

    public appAccountMasterGoalFirebaseModel() {
    }

    public String getGoalKey() {
        return GoalKey;
    }

    public void setGoalKey(String goalKey) {
        GoalKey = goalKey;
    }

    public String getGoalName() {
        return GoalName;
    }

    public void setGoalName(String goalName) {
        GoalName = goalName;
    }

    public String getGoalType() {
        return GoalType;
    }

    public void setGoalType(String goalType) {
        GoalType = goalType;
    }

    public String getGoalDatePlanned() {
        return GoalDatePlanned;
    }

    public void setGoalDatePlanned(String goalDatePlanned) {
        GoalDatePlanned = goalDatePlanned;
    }

    public String getGoalDateRealized() {
        return GoalDateRealized;
    }

    public void setGoalDateRealized(String goalDateRealized) {
        GoalDateRealized = goalDateRealized;
    }

    public String getGoalValuePlanned() {
        return GoalValuePlanned;
    }

    public void setGoalValuePlanned(String goalValuePlanned) {
        GoalValuePlanned = goalValuePlanned;
    }

    public String getGoalValueRealized() {
        return GoalValueRealized;
    }

    public void setGoalValueRealized(String goalValueRealized) {
        GoalValueRealized = goalValueRealized;
    }

    public String getGoalPriority() {
        return GoalPriority;
    }

    public void setGoalPriority(String goalPriority) {
        GoalPriority = goalPriority;
    }

    public String getGoalSavingRate() {
        return GoalSavingRate;
    }

    public void setGoalSavingRate(String goalSavingRate) {
        GoalSavingRate = goalSavingRate;
    }

    public String getGoalUpdatedBy() {
        return GoalUpdatedBy;
    }

    public void setGoalUpdatedBy(String goalUpdatedBy) {
        GoalUpdatedBy = goalUpdatedBy;
    }

    public String getGoalUpdatedOn() {
        return GoalUpdatedOn;
    }

    public void setGoalUpdatedOn(String goalUpdatedOn) {
        GoalUpdatedOn = goalUpdatedOn;
    }
}
