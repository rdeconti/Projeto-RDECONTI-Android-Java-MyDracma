package com.prosperday.mydracma.app_Support;

import android.content.Intent;
import android.view.MenuItem;

import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_AccountMasterChart.appAccountMasterChartReport;
import com.prosperday.mydracma.app_AccountMaster.appAccountMasterReport;
import com.prosperday.mydracma.app_AccountTransaction.appAccountTransactionReport;
import com.prosperday.mydracma.app_AccountTransfer.appAccountTransferReport;
import com.prosperday.mydracma.app_Authorization.appAuthorizationLogOut;
import com.prosperday.mydracma.app_AccountMasterBudget.appAccountMasterBudgetReport;
import com.prosperday.mydracma.app_FinancialHealth.appFinancialHealthStep00Menu;
import com.prosperday.mydracma.app_AccountMasterGoal.appAccountMasterGoalReport;
import com.prosperday.mydracma.app_Report.appReportNotification;
import com.prosperday.mydracma.app_Tool.appToolStep00Menu;
import com.prosperday.mydracma.app_User.appUserReport;
import com.prosperday.mydracma.app_VirtualAssistant.appVirtualAssistantStep00Menu;

import static com.prosperday.mydracma.app_FinancialHealth.appFinancialHealthStep00Menu.currentContext;

public class appSupportMenuGeneral {

    public appSupportMenuGeneral(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionHome:
                Intent intent01 = new Intent(currentContext, appFinancialHealthStep00Menu.class);
                currentContext.startActivity(intent01);
                break;
            case R.id.actionLogout:
                Intent intent02 = new Intent(currentContext, appAuthorizationLogOut.class);
                currentContext.startActivity(intent02);
                break;
            case R.id.actionNotification:
                Intent intent03 = new Intent(currentContext, appReportNotification.class);
                currentContext.startActivity(intent03);
                break;
            case R.id.actionAccountCategoryReport:
                Intent intent05 = new Intent(currentContext, appAccountMasterChartReport.class);
                currentContext.startActivity(intent05);
                break;
            case R.id.actionAccountMasterReport:
                Intent intent06 = new Intent(currentContext, appAccountMasterReport.class);
                currentContext.startActivity(intent06);
                break;
            case R.id.actionAccountTransactionReport:
                Intent intent07 = new Intent(currentContext, appAccountTransactionReport.class);
                currentContext.startActivity(intent07);
                break;
            case R.id.actionAccountTransferReport:
                Intent intent08 = new Intent(currentContext, appAccountTransferReport.class);
                currentContext.startActivity(intent08);
                break;
            case R.id.actionBudgetReport:
                Intent intent09 = new Intent(currentContext, appAccountMasterBudgetReport.class);
                currentContext.startActivity(intent09);
                break;
            case R.id.actionGoalReport:
                Intent intent10 = new Intent(currentContext, appAccountMasterGoalReport.class);
                currentContext.startActivity(intent10);
                break;
            case R.id.actionUser:
                Intent intent11 = new Intent(currentContext, appUserReport.class);
                currentContext.startActivity(intent11);
                break;
            case R.id.virtualAssistant:
                Intent intent12 = new Intent(currentContext, appVirtualAssistantStep00Menu.class);
                currentContext.startActivity(intent12);
                break;
            case R.id.tool:
                Intent intent13 = new Intent(currentContext, appToolStep00Menu.class);
                currentContext.startActivity(intent13);
                break;
        }
    }
}
