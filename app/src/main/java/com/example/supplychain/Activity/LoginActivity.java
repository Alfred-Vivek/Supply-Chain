package com.example.supplychain.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.supplychain.R;
import com.example.supplychain.Request.LoginRequest;
import com.example.supplychain.Response.LoginResponse;
import com.example.supplychain.Rest.ApiClient;
import com.example.supplychain.Rest.ApiInterface;
import com.example.supplychain.Utils.PrefUtils;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivity extends AppCompatActivity {

EditText usernameET;
EditText passwordET;
Button loginBT;
Spinner deptSP;
private ApiInterface apiServices;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        usernameET = (EditText)findViewById(R.id.emailET);
        passwordET = (EditText)findViewById(R.id.passwordET);
        loginBT = (Button)findViewById(R.id.loginBT);
        deptSP = (Spinner)findViewById(R.id.deptSP);
        Spinner spinner = (Spinner) findViewById(R.id.deptSP);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        String[] items = new String[] {"Select Department","Logistic1", "Logistic2", "TPW"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernameET.getText().toString().trim().equals(""))
                {
                    usernameET.setError("Enter valid user name");
                    return;
                }
                if(passwordET.getText().toString().trim().equals("")){
                    passwordET.setError("Enter valid password");
                    return;
                }
                if(deptSP.getSelectedItem().toString().equalsIgnoreCase("Select Department"))
                {
                    Toast.makeText(v.getContext(),"Select Department",Toast.LENGTH_SHORT).show();
                    return;
                }
                loginUser();
            }
        });
    }
    private void loginUser()
    {
        progressDialog.show();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(usernameET.getText().toString().trim());
        loginRequest.setPasscode(passwordET.getText().toString().trim());
        loginRequest.setDcOrg(deptSP.getSelectedItem().toString().toLowerCase());
        Gson gson = new Gson();
        final String json = gson.toJson(loginRequest);
        Map<String,String> map = new HashMap<String,String>();
        map = gson.fromJson(json, map.getClass());
        apiServices = ApiClient.getLoginClient().create(ApiInterface.class);
        Call<LoginResponse> call = apiServices.sendLoginDetails(map);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                Log.i("Login_Response",response.toString());
                if(response.code() == 200){
                    if(response.body().getStatus().equalsIgnoreCase("success")){ ;
                        LoginResponse loginResponse = response.body();
                        processDetails(loginResponse);
                    }else{
                        invalidDialog();
                    }
                }else if(response.code() == 400){
                        invalidDialog();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Failed to connect!")
                        .setMessage("Try connecting to server again?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                loginUser();
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
    public void processDetails(LoginResponse lr)
    {
        PrefUtils.saveToPrefs(getApplicationContext(),PrefUtils.user_name,lr.getName());
        PrefUtils.saveToPrefs(getApplicationContext(),PrefUtils.user_email,lr.getEmail());
        PrefUtils.saveToPrefs(getApplicationContext(),PrefUtils.user_dcorg,lr.getDcOrg());
        PrefUtils.saveToPrefs(getApplicationContext(),PrefUtils.orgTypeId,lr.getOrgTypeID());
        PrefUtils.saveToPrefs(getApplicationContext(),PrefUtils.orgTypeName,lr.getOrgTypeName());
        PrefUtils.saveToPrefs(getApplicationContext(),PrefUtils.siteCode,lr.getSiteCode());
        PrefUtils.saveToPrefs(getApplicationContext(),PrefUtils.user_auth,lr.getAuth());
        PrefUtils.saveToPrefs(getApplicationContext(),PrefUtils.token_string,lr.getToken());
        if(lr.getDcOrg().equalsIgnoreCase("tpw"))
        {
            Intent in = new Intent(this,TpwHome.class);
            in.putExtra("email",lr.getEmail());
            startActivity(in);
        }
        else
        {
            Intent in = new Intent(this,LogisticsHome.class);
            startActivity(in);
        }
    }
    public void invalidDialog()
    {
        new AlertDialog.Builder(this)
                .setTitle("Invalid Login Details")
                .setMessage("\n Please Check Your Login Credentials")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNeutralButton(android.R.string.ok, null).show();
    }
}