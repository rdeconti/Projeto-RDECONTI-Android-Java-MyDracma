package com.prosperday.mydracma.app_AccountTransfer;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class appAccountTransferFirebaseModel {

    private String AccountTransferKey;
    private String AccountTransferAccountFrom;
    private String AccountTransferAccountTo;
    private String AccountTransferDatePlanned;
    private String AccountTransferDateRealized;
    private String AccountTransferValuePlanned;
    private String AccountTransferValueRealized;
    private String AccountTransferPeriod;
    private String AccountTransferOccurrence;
    private String AccountTransferReminder;
    private String AccountTransferAutomatic;
    private String AccountTransferUpdatedBy;
    private String AccountTransferUpdatedOn;

    public appAccountTransferFirebaseModel() {
    }

    public String getAccountTransferKey() {
        return AccountTransferKey;
    }

    public void setAccountTransferKey(String accountTransferKey) {
        AccountTransferKey = accountTransferKey;
    }

    public String getAccountTransferAccountFrom() {
        return AccountTransferAccountFrom;
    }

    public void setAccountTransferAccountFrom(String accountTransferAccountFrom) {
        AccountTransferAccountFrom = accountTransferAccountFrom;
    }

    public String getAccountTransferAccountTo() {
        return AccountTransferAccountTo;
    }

    public void setAccountTransferAccountTo(String accountTransferAccountTo) {
        AccountTransferAccountTo = accountTransferAccountTo;
    }

    public String getAccountTransferDatePlanned() {
        return AccountTransferDatePlanned;
    }

    public void setAccountTransferDatePlanned(String accountTransferDatePlanned) {
        AccountTransferDatePlanned = accountTransferDatePlanned;
    }

    public String getAccountTransferDateRealized() {
        return AccountTransferDateRealized;
    }

    public void setAccountTransferDateRealized(String accountTransferDateRealized) {
        AccountTransferDateRealized = accountTransferDateRealized;
    }

    public String getAccountTransferValuePlanned() {
        return AccountTransferValuePlanned;
    }

    public void setAccountTransferValuePlanned(String accountTransferValuePlanned) {
        AccountTransferValuePlanned = accountTransferValuePlanned;
    }

    public String getAccountTransferValueRealized() {
        return AccountTransferValueRealized;
    }

    public void setAccountTransferValueRealized(String accountTransferValueRealized) {
        AccountTransferValueRealized = accountTransferValueRealized;
    }

    public String getAccountTransferPeriod() {
        return AccountTransferPeriod;
    }

    public void setAccountTransferPeriod(String accountTransferPeriod) {
        AccountTransferPeriod = accountTransferPeriod;
    }

    public String getAccountTransferOccurrence() {
        return AccountTransferOccurrence;
    }

    public void setAccountTransferOccurrence(String accountTransferOccurrence) {
        AccountTransferOccurrence = accountTransferOccurrence;
    }

    public String getAccountTransferReminder() {
        return AccountTransferReminder;
    }

    public void setAccountTransferReminder(String accountTransferReminder) {
        AccountTransferReminder = accountTransferReminder;
    }

    public String getAccountTransferAutomatic() {
        return AccountTransferAutomatic;
    }

    public void setAccountTransferAutomatic(String accountTransferAutomatic) {
        AccountTransferAutomatic = accountTransferAutomatic;
    }

    public String getAccountTransferUpdatedBy() {
        return AccountTransferUpdatedBy;
    }

    public void setAccountTransferUpdatedBy(String accountTransferUpdatedBy) {
        AccountTransferUpdatedBy = accountTransferUpdatedBy;
    }

    public String getAccountTransferUpdatedOn() {
        return AccountTransferUpdatedOn;
    }

    public void setAccountTransferUpdatedOn(String accountTransferUpdatedOn) {
        AccountTransferUpdatedOn = accountTransferUpdatedOn;
    }
}
