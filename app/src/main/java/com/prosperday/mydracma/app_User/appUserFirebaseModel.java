package com.prosperday.mydracma.app_User;
// ---------------------------------------------------------------
// Created by Rosemeire Deconti
// Created on 05/01/2018
// Created at 15:02
// Package is com.prosperday.mydracma
// Project is MyDracma
// ----------------------------------------------------------------

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class appUserFirebaseModel {

    private String UserDataKey;
    private String UserDataCode;
    private String UserDataName;
    private String UserDataPhone;
    private String UserDataBirthday;
    private String UserDataLanguage;
    private String UserDataCurrency;
    private String UserDataSession;
    private String UserDataAdvertising;
    private String UserDataSignIn;
    private String UserDataSignOut;
    private String UserDataTimeFormat;
    private String UserDataDateFormat;
    private String UserDataDecimalFormat;
    private String UserDataUpdatedBy;
    private String UserDataUpdatedOn;
    private String UserDataLoginFailed;
    private String UserDataLoginBlocked;
    private String UserPasswordLastChange;
    private String UserPasswordNextChange;
    private String UserLimitLowerEmergency;
    private String UserLimitUpperEmergency;
    private String UserLimitLowerRetirement;
    private String UserLimitUpperRetirement;
    private String UserLimitLowerIncome;
    private String UserLimitUpperIncome;
    private String UserLimitLowerOutcome;
    private String UserLimitUpperOutcome;
    private String UserLimitLowerGoal;
    private String UserLimitUpperGoal;
    private String UserLimitLowerBudget;
    private String UserLimitUpperBudget;
    private String UserNotificationEmergency;
    private String UserNotificationRetirement;
    private String UserNotificationIncome;
    private String UserNotificationOutcome;
    private String UserNotificationGoal;
    private String UserNotificationBudget;
    private String UserNotificationBillToPay;
    private String UserNotificationBillNotPaid;

    public appUserFirebaseModel() {
    }

    public String getUserDataKey() {
        return UserDataKey;
    }

    public void setUserDataKey(String UserDataKey) {
        this.UserDataKey = UserDataKey;
    }

    public String getUserDataCode() {
        return UserDataCode;
    }

    public void setUserDataCode(String UserDataCode) {
        this.UserDataCode = UserDataCode;
    }

    public String getUserDataName() {
        return UserDataName;
    }

    public void setUserDataName(String UserDataName) {
        this.UserDataName = UserDataName;
    }

    public String getUserDataPhone() {
        return UserDataPhone;
    }

    public void setUserDataPhone(String UserDataPhone) {
        this.UserDataPhone = UserDataPhone;
    }

    public String getUserDataBirthday() {
        return UserDataBirthday;
    }

    public void setUserDataBirthday(String UserDataBirthday) {
        this.UserDataBirthday = UserDataBirthday;
    }

    public String getUserDataLanguage() {
        return UserDataLanguage;
    }

    public void setUserDataLanguage(String UserDataLanguage) {
        this.UserDataLanguage = UserDataLanguage;
    }

    public String getUserDataCurrency() {
        return UserDataCurrency;
    }

    public void setUserDataCurrency(String UserDataCurrency) {
        this.UserDataCurrency = UserDataCurrency;
    }

    public String getUserDataSession() {
        return UserDataSession;
    }

    public void setUserDataSession(String UserDataSession) {
        this.UserDataSession = UserDataSession;
    }

    public String getUserDataAdvertising() {
        return UserDataAdvertising;
    }

    public void setUserDataAdvertising(String UserDataAdvertising) {
        this.UserDataAdvertising = UserDataAdvertising;
    }

    public String getUserDataSignIn() {
        return UserDataSignIn;
    }

    public void setUserDataSignIn(String UserDataSignIn) {
        this.UserDataSignIn = UserDataSignIn;
    }

    public String getUserDataSignOut() {
        return UserDataSignOut;
    }

    public void setUserDataSignOut(String UserDataSignOut) {
        this.UserDataSignOut = UserDataSignOut;
    }

    public String getUserDataTimeFormat() {
        return UserDataTimeFormat;
    }

    public void setUserDataTimeFormat(String UserDataTimeFormat) {
        this.UserDataTimeFormat = UserDataTimeFormat;
    }

    public String getUserDataDateFormat() {
        return UserDataDateFormat;
    }

    public void setUserDataDateFormat(String UserDataDateFormat) {
        this.UserDataDateFormat = UserDataDateFormat;
    }

    public String getUserDataDecimalFormat() {
        return UserDataDecimalFormat;
    }

    public void setUserDataDecimalFormat(String UserDataDecimalFormat) {
        this.UserDataDecimalFormat = UserDataDecimalFormat;
    }

    public String getUserDataUpdatedBy() {
        return UserDataUpdatedBy;
    }

    public void setUserDataUpdatedBy(String UserDataUpdatedBy) {
        this.UserDataUpdatedBy = UserDataUpdatedBy;
    }

    public String getUserDataUpdatedOn() {
        return UserDataUpdatedOn;
    }

    public void setUserDataUpdatedOn(String UserDataUpdatedOn) {
        this.UserDataUpdatedOn = UserDataUpdatedOn;
    }

    public String getUserDataLoginFailed() {
        return UserDataLoginFailed;
    }

    public void setUserDataLoginFailed(String UserDataLoginFailed) {
        this.UserDataLoginFailed = UserDataLoginFailed;
    }

    public String getUserDataLoginBlocked() {
        return UserDataLoginBlocked;
    }

    public void setUserDataLoginBlocked(String UserDataLoginBlocked) {
        this.UserDataLoginBlocked = UserDataLoginBlocked;
    }

    public String getUserPasswordLastChange() {
        return UserPasswordLastChange;
    }

    public void setUserPasswordLastChange(String UserPasswordLastChange) {
        this.UserPasswordLastChange = UserPasswordLastChange;
    }

    public String getUserPasswordNextChange() {
        return UserPasswordNextChange;
    }

    public void setUserPasswordNextChange(String UserPasswordNextChange) {
        this.UserPasswordNextChange = UserPasswordNextChange;
    }

    public String getUserLimitLowerEmergency() {
        return UserLimitLowerEmergency;
    }

    public void setUserLimitLowerEmergency(String UserLimitLowerEmergency) {
        this.UserLimitLowerEmergency = UserLimitLowerEmergency;
    }

    public String getUserLimitUpperEmergency() {
        return UserLimitUpperEmergency;
    }

    public void setUserLimitUpperEmergency(String UserLimitUpperEmergency) {
        this.UserLimitUpperEmergency = UserLimitUpperEmergency;
    }

    public String getUserLimitLowerRetirement() {
        return UserLimitLowerRetirement;
    }

    public void setUserLimitLowerRetirement(String UserLimitLowerRetirement) {
        this.UserLimitLowerRetirement = UserLimitLowerRetirement;
    }

    public String getUserLimitUpperRetirement() {
        return UserLimitUpperRetirement;
    }

    public void setUserLimitUpperRetirement(String UserLimitUpperRetirement) {
        this.UserLimitUpperRetirement = UserLimitUpperRetirement;
    }

    public String getUserLimitLowerIncome() {
        return UserLimitLowerIncome;
    }

    public void setUserLimitLowerIncome(String UserLimitLowerIncome) {
        this.UserLimitLowerIncome = UserLimitLowerIncome;
    }

    public String getUserLimitUpperIncome() {
        return UserLimitUpperIncome;
    }

    public void setUserLimitUpperIncome(String UserLimitUpperIncome) {
        this.UserLimitUpperIncome = UserLimitUpperIncome;
    }

    public String getUserLimitLowerOutcome() {
        return UserLimitLowerOutcome;
    }

    public void setUserLimitLowerOutcome(String UserLimitLowerOutcome) {
        this.UserLimitLowerOutcome = UserLimitLowerOutcome;
    }

    public String getUserLimitUpperOutcome() {
        return UserLimitUpperOutcome;
    }

    public void setUserLimitUpperOutcome(String UserLimitUpperOutcome) {
        this.UserLimitUpperOutcome = UserLimitUpperOutcome;
    }

    public String getUserLimitLowerGoal() {
        return UserLimitLowerGoal;
    }

    public void setUserLimitLowerGoal(String UserLimitLowerGoal) {
        this.UserLimitLowerGoal = UserLimitLowerGoal;
    }

    public String getUserLimitUpperGoal() {
        return UserLimitUpperGoal;
    }

    public void setUserLimitUpperGoal(String UserLimitUpperGoal) {
        this.UserLimitUpperGoal = UserLimitUpperGoal;
    }

    public String getUserLimitLowerBudget() {
        return UserLimitLowerBudget;
    }

    public void setUserLimitLowerBudget(String UserLimitLowerBudget) {
        this.UserLimitLowerBudget = UserLimitLowerBudget;
    }

    public String getUserLimitUpperBudget() {
        return UserLimitUpperBudget;
    }

    public void setUserLimitUpperBudget(String UserLimitUpperBudget) {
        this.UserLimitUpperBudget = UserLimitUpperBudget;
    }

    public String getUserNotificationEmergency() {
        return UserNotificationEmergency;
    }

    public void setUserNotificationEmergency(String UserNotificationEmergency) {
        this.UserNotificationEmergency = UserNotificationEmergency;
    }

    public String getUserNotificationRetirement() {
        return UserNotificationRetirement;
    }

    public void setUserNotificationRetirement(String UserNotificationRetirement) {
        this.UserNotificationRetirement = UserNotificationRetirement;
    }

    public String getUserNotificationIncome() {
        return UserNotificationIncome;
    }

    public void setUserNotificationIncome(String UserNotificationIncome) {
        this.UserNotificationIncome = UserNotificationIncome;
    }

    public String getUserNotificationOutcome() {
        return UserNotificationOutcome;
    }

    public void setUserNotificationOutcome(String UserNotificationOutcome) {
        this.UserNotificationOutcome = UserNotificationOutcome;
    }

    public String getUserNotificationGoal() {
        return UserNotificationGoal;
    }

    public void setUserNotificationGoal(String UserNotificationGoal) {
        this.UserNotificationGoal = UserNotificationGoal;
    }

    public String getUserNotificationBudget() {
        return UserNotificationBudget;
    }

    public void setUserNotificationBudget(String UserNotificationBudget) {
        this.UserNotificationBudget = UserNotificationBudget;
    }

    public String getUserNotificationBillToPay() {
        return UserNotificationBillToPay;
    }

    public void setUserNotificationBillToPay(String UserNotificationBillToPay) {
        this.UserNotificationBillToPay = UserNotificationBillToPay;
    }

    public String getUserNotificationBillNotPaid() {
        return UserNotificationBillNotPaid;
    }

    public void setUserNotificationBillNotPaid(String UserNotificationBillNotPaid) {
        this.UserNotificationBillNotPaid = UserNotificationBillNotPaid;
    }
}
