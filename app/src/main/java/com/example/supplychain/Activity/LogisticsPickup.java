package com.example.supplychain.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.supplychain.Adapter.DataAdapterLogistics;
import com.example.supplychain.Pojo.LogisticsModel;
import com.example.supplychain.R;
import com.example.supplychain.Response.LogisticPackageData;
import com.example.supplychain.Response.LogisticPackageResponse;
import com.example.supplychain.Rest.ApiClient;
import com.example.supplychain.Rest.ApiInterface;
import com.example.supplychain.Utils.PrefUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogisticsPickup extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ArrayList<LogisticsModel> details;
    List <LogisticPackageData> logisticPackageData;
    ProgressDialog progressDialog;
    ApiInterface apiServices;
    LogisticPackageResponse logisticPackageResponse;
    TextView nameTV,emailTV,dpTV, msgTV;
    SwipeRefreshLayout srl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics_pickup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        srl = findViewById(R.id.pullToRefresh);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPackageApi();
            }
        });
        msgTV = findViewById(R.id.msgTV);
        loadPackageApi();
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
        emailTV = header.findViewById(R.id.emailTV);
        emailTV.setText(PrefUtils.getFromPrefs(this,PrefUtils.user_email,""));
        nameTV = header.findViewById(R.id.nameTV);
        nameTV.setText("Welcome "+PrefUtils.getFromPrefs(this,PrefUtils.user_name,""));
        dpTV = header.findViewById(R.id.dpTV);
        String x = PrefUtils.getFromPrefs(this,PrefUtils.user_name,"");
        String[] myName = x.split(" ");
        String dp="";
        for (int i = 0; i < myName.length; i++) {
            String s = myName[i].charAt(0)+"";
            dp = dp +s;
        }
        dpTV.setText(dp);
    }
    public void loadPackageApi() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String dcorg = PrefUtils.getFromPrefs(getApplicationContext(), PrefUtils.user_dcorg,"");
        apiServices = ApiClient.getPackageClient().create(ApiInterface.class);
        Call<LogisticPackageResponse> call = apiServices.getPackage(dcorg);
        call.enqueue(new Callback<LogisticPackageResponse>() {
            @Override
            public void onResponse(Call<LogisticPackageResponse> call, Response<LogisticPackageResponse> response) {
                progressDialog.dismiss();
                srl.setRefreshing(false);
                if(response.body().getStatus().equalsIgnoreCase("fail"))
                {
                    msgTV.setVisibility(View.VISIBLE);
                    msgTV.setText(response.body().getMessage());
                }
                else {
                    logisticPackageResponse = response.body();
                    logisticPackageData = logisticPackageResponse.getLogisticPackageData();
                    if(logisticPackageResponse !=null)
                        initViews("");
                }
            }
            @Override
            public void onFailure(Call<LogisticPackageResponse> call, Throwable t) {
                progressDialog.dismiss();
                new AlertDialog.Builder(LogisticsPickup.this)
                        .setTitle("Failed to connect!")
                        .setMessage("Try connecting to server again?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                loadPackageApi();
                            }
                        })
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                finishAffinity();
                            }
                        }).show();
            }
        });
    }
    public void initViews(String search) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        details = new ArrayList<LogisticsModel>();
        List <LogisticPackageData> newpd = new ArrayList<>();
        if(search.equalsIgnoreCase(""))
        {
            for(int i = 0; i< logisticPackageData.size(); i++ )
            {
                Boolean asnacceptance = logisticPackageData.get(i).getAsnAcceptance() != null;
                if(asnacceptance)
                {
                    Boolean acceptance = logisticPackageData.get(i).getAsnAcceptance().getAcceptance().equalsIgnoreCase("true");
                    Boolean pickupdetails = logisticPackageData.get(i).getPickupDetails() == null;
                    if((acceptance && pickupdetails))
                    {
                        LogisticsModel m = new LogisticsModel(logisticPackageData.get(i).getAwbNumber(), logisticPackageData.get(i).getShiptoLocationAddress(), logisticPackageData.get(i).getTotalQuantity(), logisticPackageData.get(i).getTotalLength(), logisticPackageData.get(i).getTotalwidth(), logisticPackageData.get(i).getTotalHeight(),"Open");
                        details.add(m);
                        newpd.add(logisticPackageData.get(i));
                    }
                }
            }
        }
        else
        {
            for(int i = 0; i< logisticPackageData.size(); i++ )
            {
                if(logisticPackageData.get(i).getAwbNumber().toLowerCase().contains(search.toLowerCase()))
                {
                    Boolean asnacceptance = logisticPackageData.get(i).getAsnAcceptance() != null;
                    if(asnacceptance)
                    {
                        Boolean acceptance = logisticPackageData.get(i).getAsnAcceptance().getAcceptance().equalsIgnoreCase("true");
                        Boolean pickupdetails = logisticPackageData.get(i).getPickupDetails() == null;
                        if((asnacceptance && acceptance && pickupdetails))
                        {
                            LogisticsModel m = new LogisticsModel(logisticPackageData.get(i).getAwbNumber(), logisticPackageData.get(i).getShiptoLocationAddress(), logisticPackageData.get(i).getTotalQuantity(), logisticPackageData.get(i).getTotalLength(), logisticPackageData.get(i).getTotalwidth(), logisticPackageData.get(i).getTotalHeight(),"Open");
                            details.add(m);
                            newpd.add(logisticPackageData.get(i));
                        }
                    }
                }
            }
        }
        if (details.size() == 0)
        {
            msgTV.setVisibility(View.VISIBLE);
            msgTV.setText("No Pick Up's for the day");
        }
        RecyclerView.Adapter adapter = new DataAdapterLogistics(LogisticsPickup.this,details,newpd,1);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logistics_pickup, menu);
        MenuItem menuItem           =       menu.findItem(R.id.mi_search);
        SearchView searchView       =       (SearchView)menuItem.getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("Search AWB");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false;}
            @Override
            public boolean onQueryTextChange(String newText)
            {
                initViews(newText);
                return true;
            }});
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            PrefUtils.removePrefs(getApplicationContext());
            Intent intent  = new Intent(LogisticsPickup.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent i = new Intent(getApplicationContext(), LogisticsHome.class);
            startActivity(i);
        } else if (id == R.id.nav_pickup) {
            Intent i = new Intent(getApplicationContext(), LogisticsPickup.class);
            i.putExtra("logisticPackageData", (Serializable) logisticPackageData);
            startActivity(i);
        } else if (id == R.id.nav_deliver) {
            Intent i = new Intent(getApplicationContext(), LogisticsDeliver.class);
            i.putExtra("logisticPackageData", (Serializable) logisticPackageData);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}