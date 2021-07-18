package com.prosperday.mydracma.app_Report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_AccountMasterChart.appAccountMasterChartReport;
import com.prosperday.mydracma.app_AccountMaster.appAccountMasterReport;
import com.prosperday.mydracma.app_AccountTransaction.appAccountTransactionReport;
import com.prosperday.mydracma.app_AccountTransfer.appAccountTransferReport;
import com.prosperday.mydracma.app_Authorization.appAuthorizationLogIn;
import com.prosperday.mydracma.app_Authorization.appAuthorizationLogOut;
import com.prosperday.mydracma.app_AccountMasterBudget.appAccountMasterBudgetReport;
import com.prosperday.mydracma.app_FinancialHealth.appFinancialHealthStep00Menu;
import com.prosperday.mydracma.app_AccountMasterGoal.appAccountMasterGoalReport;
import com.prosperday.mydracma.app_User.appUserReport;

public class appReportCashFlow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Verifying if user is logged on app */
        FirebaseAuth myAuthorization = FirebaseAuth.getInstance();
        FirebaseUser user = myAuthorization.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(getApplicationContext(), appAuthorizationLogIn.class));
            finish();
        }

        /* Set Layout */
        setContentView(R.layout.app_report_cash_flow);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnUpdateDelete = findViewById(R.id.btnDelete);

        /* Treat Update and Delete */
        btnUpdateDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: develop report
                Toast.makeText(getApplicationContext(), R.string.msg_toBeDeveloped, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /* Inflate menu toolbar */
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_navigation_general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /* Treat options from action bar and menu */
        switch (item.getItemId()) {

            /* Treat toolbar: Icons */
            case R.id.actionHome:
                startActivity(new Intent(getApplicationContext(), appFinancialHealthStep00Menu.class));
                break;
            case R.id.actionLogout:
                startActivity(new Intent(getApplicationContext(), appAuthorizationLogOut.class));
                break;
            case R.id.actionNotification:
                // TODO: develop alerts
                Toast.makeText(getApplicationContext(), R.string.msg_toBeDeveloped, Toast.LENGTH_LONG).show();
                break;

            /* Treat toolbar: Options */
            case R.id.actionAccountCategoryReport:
                startActivity(new Intent(getApplicationContext(), appAccountMasterChartReport.class));
                break;
            case R.id.actionAccountMasterReport:
                startActivity(new Intent(getApplicationContext(), appAccountMasterReport.class));
                break;
            case R.id.actionAccountTransactionReport:
                startActivity(new Intent(getApplicationContext(), appAccountTransactionReport.class));
                break;
            case R.id.actionAccountTransferReport:
                startActivity(new Intent(getApplicationContext(), appAccountTransferReport.class));
                break;
            case R.id.actionBudgetReport:
                startActivity(new Intent(getApplicationContext(), appAccountMasterBudgetReport.class));
                break;
            case R.id.actionUser:
                startActivity(new Intent(getApplicationContext(), appUserReport.class));
                break;
            case R.id.actionGoalReport:
                startActivity(new Intent(getApplicationContext(), appAccountMasterGoalReport.class));
                break;
            /* Treat toolbar: option not treated yet */
            default:
                Toast.makeText(getApplicationContext(), R.string.msg_toBeTreated, Toast.LENGTH_LONG).show();
                break;
        }

        /* End toolbar treatment */
        return super.onOptionsItemSelected(item);
    }
}
