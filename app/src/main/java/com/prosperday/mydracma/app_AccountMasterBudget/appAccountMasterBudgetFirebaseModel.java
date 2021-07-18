package com.prosperday.mydracma.app_AccountMasterBudget;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class appAccountMasterBudgetFirebaseModel {

    private String BudgetKey;
    private String BudgetYear;
    private String BudgetMonth;
    private String BudgetTransaction;
    private String BudgetCategory;
    private String BudgetSubCategory;
    private String BudgetValuePlanned;
    private String BudgetValueRealized;
    private String BudgetUpdatedBy;
    private String BudgetUpdatedOn;

    public appAccountMasterBudgetFirebaseModel() {
    }

    public String getBudgetKey() {
        return BudgetKey;
    }

    public void setBudgetKey(String budgetKey) {
        BudgetKey = budgetKey;
    }

    public String getBudgetYear() {
        return BudgetYear;
    }

    public void setBudgetYear(String budgetYear) {
        BudgetYear = budgetYear;
    }

    public String getBudgetMonth() {
        return BudgetMonth;
    }

    public void setBudgetMonth(String budgetMonth) {
        BudgetMonth = budgetMonth;
    }

    public String getBudgetTransaction() {
        return BudgetTransaction;
    }

    public void setBudgetTransaction(String budgetTransaction) {
        BudgetTransaction = budgetTransaction;
    }

    public String getBudgetCategory() {
        return BudgetCategory;
    }

    public void setBudgetCategory(String budgetCategory) {
        BudgetCategory = budgetCategory;
    }

    public String getBudgetSubCategory() {
        return BudgetSubCategory;
    }

    public void setBudgetSubCategory(String budgetSubCategory) {
        BudgetSubCategory = budgetSubCategory;
    }

    public String getBudgetValuePlanned() {
        return BudgetValuePlanned;
    }

    public void setBudgetValuePlanned(String budgetValuePlanned) {
        BudgetValuePlanned = budgetValuePlanned;
    }

    public String getBudgetValueRealized() {
        return BudgetValueRealized;
    }

    public void setBudgetValueRealized(String budgetValueRealized) {
        BudgetValueRealized = budgetValueRealized;
    }

    public String getBudgetUpdatedBy() {
        return BudgetUpdatedBy;
    }

    public void setBudgetUpdatedBy(String budgetUpdatedBy) {
        BudgetUpdatedBy = budgetUpdatedBy;
    }

    public String getBudgetUpdatedOn() {
        return BudgetUpdatedOn;
    }

    public void setBudgetUpdatedOn(String budgetUpdatedOn) {
        BudgetUpdatedOn = budgetUpdatedOn;
    }
}
