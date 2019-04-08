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
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.supplychain.Adapter.DataAdapterLogistics;
import com.example.supplychain.Pojo.LogisticsModel;
import com.example.supplychain.R;
import com.example.supplychain.Response.LogisticPackageData;
import com.example.supplychain.Response.LogisticPackageResponse;
import com.example.supplychain.Rest.ApiClient;
import com.example.supplychain.Rest.ApiInterface;
import com.example.supplychain.Utils.PrefUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogisticsHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ArrayList<LogisticsModel> details;
    TextView nameTV, emailTV;
    private ApiInterface apiServices;
    LogisticPackageResponse logisticPackageResponse;
    ProgressDialog progressDialog;
    SwipeRefreshLayout srl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        srl = findViewById(R.id.pullToRefresh);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPackageApi();
            }
        });
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
                String d = DateFormat.format("EEE, MMM d, yyyy", date).toString();
                Toast.makeText(getApplicationContext(),d,Toast.LENGTH_LONG).show();
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
            logisticPackageResponse = response.body();
            List <LogisticPackageData>pd = logisticPackageResponse.getLogisticPackageData();
            if(!logisticPackageResponse.getStatus().equalsIgnoreCase("fail"))
                initViews("");
        }
        @Override
        public void onFailure(Call<LogisticPackageResponse> call, Throwable t) {
            progressDialog.dismiss();
            new AlertDialog.Builder(LogisticsHome.this)
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
        List <LogisticPackageData>pd = logisticPackageResponse.getLogisticPackageData();
        List <LogisticPackageData> newpd = new ArrayList<>();
        if(search.equalsIgnoreCase(""))
        {
            for(int i=0; i<pd.size(); i++ )
            {
                if((pd.get(i).getAsnAcceptance() == null))
                {
                    LogisticsModel m = new LogisticsModel(pd.get(i).getAwbNumber(),pd.get(i).getShiptoLocationAddress(),pd.get(i).getTotalQuantity(),pd.get(i).getTotalLength(),pd.get(i).getTotalwidth(),pd.get(i).getTotalHeight(),"Open");
                    details.add(m);
                    newpd.add(pd.get(i));
                }
            }
        }
        else
        {
            for(int i=0; i<pd.size(); i++ )
            {
                if(pd.get(i).getAwbNumber().toLowerCase().contains(search.toLowerCase()))
                {
                    if((pd.get(i).getAsnAcceptance() == null))
                    {
                        LogisticsModel m = new LogisticsModel(pd.get(i).getAwbNumber(),pd.get(i).getShiptoLocationAddress(),pd.get(i).getTotalQuantity(),pd.get(i).getTotalLength(),pd.get(i).getTotalwidth(),pd.get(i).getTotalHeight(),"Open");
                        details.add(m);
                        newpd.add(pd.get(i));
                    }
                }
            }
        }
        RecyclerView.Adapter adapter = new DataAdapterLogistics(LogisticsHome.this,details,newpd,0);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logistics_home, menu);
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
            Intent intent  = new Intent(LogisticsHome.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        List <LogisticPackageData> logisticPackageData = logisticPackageResponse.getLogisticPackageData();
        if (id == R.id.nav_home) {
            Intent in = new Intent(getApplicationContext(), LogisticsHome.class);
            startActivity(in);
        } else if (id == R.id.nav_pickup) {
            Intent in = new Intent(getApplicationContext(), LogisticsPickup.class);
            startActivity(in);
        } else if (id == R.id.nav_deliver) {

            Intent in = new Intent(getApplicationContext(), LogisticsDeliver.class);
            startActivity(in);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
