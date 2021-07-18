package com.prosperday.mydracma.app_AccountMasterMortage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prosperday.mydracma.R;
import com.prosperday.mydracma.app_Authorization.appAuthorizationLogIn;
import com.prosperday.mydracma.app_Support.appSupportHandlingDatabaseError;
import com.prosperday.mydracma.app_Support.appSupportMenuGeneral;

import java.util.ArrayList;
import java.util.List;

public class appAccountMasterMortageReport extends AppCompatActivity {

    public static Context currentContext;
    List<appAccountMasterMortageFirebaseModel> allListItems;
    private appAccountMasterMortageReportAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

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
        setContentView(R.layout.app_account_master_report);

        /* Set Toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Set button */
        Button btnCreate = findViewById(R.id.btnCreate);

        /* Set progress bar */
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        /* Set recycler view */
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager myLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(myLayout);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        /* Set array list */
        allListItems = new ArrayList<>();

        /* Set Firebase */
        DatabaseReference myDatabaseReference = FirebaseDatabase.getInstance().getReference("AccountMaster");

        /* Obtain data to recycler view */
        myDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allListItems.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        appAccountMasterMortageFirebaseModel snapshotDetails = dataSnapshot.getValue(appAccountMasterMortageFirebaseModel.class);
                        allListItems.add(snapshotDetails);
                    }
                    recyclerViewAdapter = new appAccountMasterMortageReportAdapter(appAccountMasterMortageReport.this, allListItems);
                    recyclerView.setAdapter(recyclerViewAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.msg_CreateAppData, Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                new appSupportHandlingDatabaseError(this.getClass().getSimpleName(), databaseError);
                Toast.makeText(getApplicationContext(),R.string.msg_error_firebase,Toast.LENGTH_LONG).show();
                finish();
            }
        });

        /* Treat create */
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), appAccountMasterMortageScreenCreate.class));
            }
        });
    }

    /* Inflate menu toolbar */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_navigation_general, menu);
        return true;
    }

    /* Treat options from action bar and menu */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new appSupportMenuGeneral(item);
        return super.onOptionsItemSelected(item);
    }
}
