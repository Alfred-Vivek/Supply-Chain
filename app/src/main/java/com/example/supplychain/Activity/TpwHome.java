package com.example.supplychain.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.supplychain.Adapter.DataAdapterTpw;
import com.example.supplychain.Pojo.TpwModel;
import com.example.supplychain.R;
import com.example.supplychain.Response.TpwpackageData;
import com.example.supplychain.Response.TpwPackageResponse;
import com.example.supplychain.Rest.ApiClient;
import com.example.supplychain.Rest.ApiInterface;
import com.example.supplychain.Utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TpwHome extends AppCompatActivity {
    private ArrayList<TpwModel> details;
    List <TpwpackageData> tpwpackageData;
    ProgressDialog progressDialog;
    ApiInterface apiServices;
    TpwPackageResponse tpwPackageResponse;
    SwipeRefreshLayout srl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpw_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
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
                loadTpwApi();
            }
        });
        loadTpwApi();
    }
    public void loadTpwApi() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        apiServices = ApiClient.getPackageClient().create(ApiInterface.class);
        Call<TpwPackageResponse> call = apiServices.getTpwPackage("tpw",false);
        call.enqueue(new Callback<TpwPackageResponse>() {
            @Override
            public void onResponse(Call<TpwPackageResponse> call, Response<TpwPackageResponse> response) {
                progressDialog.dismiss();
                srl.setRefreshing(false);
                tpwPackageResponse = response.body();
                tpwpackageData = tpwPackageResponse.getTpwpackageData();
                if(tpwPackageResponse !=null)
                    initViews("");
            }
            @Override
            public void onFailure(Call<TpwPackageResponse> call, Throwable t) {
                progressDialog.dismiss();
                new AlertDialog.Builder(TpwHome.this)
                        .setTitle("Failed to connect!")
                        .setMessage("Try connecting to server again?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                loadTpwApi();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tpw_home, menu);
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
            Intent intent  = new Intent(TpwHome.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void initViews(String search) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        details = new ArrayList<TpwModel>();
        List<TpwpackageData> pd = tpwPackageResponse.getTpwpackageData();
        List <TpwpackageData> newpd = new ArrayList<>();
        if(search.equalsIgnoreCase(""))
        {
            for(int i=0; i<pd.size(); i++ )
            {
                TpwModel m = new TpwModel(pd.get(i).getAwbNumber(),pd.get(i).getShiptoLocationAddress(),pd.get(i).getTotalQuantity(),pd.get(i).getTotalLength(),pd.get(i).getTotalwidth(),pd.get(i).getTotalHeight(),"Open");
                    details.add(m);
                    newpd.add(pd.get(i));
            }
        }
        else
        {
            for(int i=0; i<pd.size(); i++ )
            {
                if(pd.get(i).getAwbNumber().toLowerCase().contains(search.toLowerCase()))
                {
                    TpwModel m = new TpwModel(pd.get(i).getAwbNumber(),pd.get(i).getShiptoLocationAddress(),pd.get(i).getTotalQuantity(),pd.get(i).getTotalLength(),pd.get(i).getTotalwidth(),pd.get(i).getTotalHeight(),"Open");
                        details.add(m);
                        newpd.add(pd.get(i));
                }
            }
        }
        RecyclerView.Adapter adapter = new DataAdapterTpw(this,details,newpd,0);
        recyclerView.setAdapter(adapter);
    }
}