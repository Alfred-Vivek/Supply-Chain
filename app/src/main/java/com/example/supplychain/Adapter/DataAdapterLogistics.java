package com.example.supplychain.Adapter;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supplychain.Activity.LogisticsDeliver;
import com.example.supplychain.Activity.LogisticsHome;
import com.example.supplychain.Activity.LogisticsPickup;
import com.example.supplychain.Activity.PackagingDetails;
import com.example.supplychain.Pojo.LogisticsModel;
import com.example.supplychain.R;
import com.example.supplychain.Request.LogisticAcceptRequest;
import com.example.supplychain.Response.LogisticPackageData;
import com.example.supplychain.Response.LogisticPackageAcceptResponse;
import com.example.supplychain.Rest.ApiClient;
import com.example.supplychain.Rest.ApiInterface;
import com.example.supplychain.Utils.PrefUtils;
import com.google.gson.Gson;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataAdapterLogistics extends RecyclerView.Adapter<DataAdapterLogistics.ViewHolder> {
    private ArrayList<LogisticsModel> details;
    public List<LogisticPackageData> logisticPackageData;
    private ApiInterface apiServices;
    private Context context;
    ProgressDialog progressDialog;
    int activity;
    public DataAdapterLogistics(Context context, ArrayList<LogisticsModel> details, List<LogisticPackageData> logisticPackageData, int activity) {
        this.context = context;
        this.details = details;
        this.logisticPackageData = logisticPackageData;
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.generic_cardrow, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.awbTV.setText(details.get(i).getAwb());
        String add = "<b>Address : </b>"+details.get(i).getAddress();
        viewHolder.addressTV.setText(Html.fromHtml(add));
        viewHolder.quantityTV.setText(details.get(i).getQuantity());
        String ln = "<b>Length : </b>"+details.get(i).getLength();
        viewHolder.lengthTV.setText(Html.fromHtml(ln));
        String wd = "<b>Width : </b>"+details.get(i).getWidth();
        viewHolder.widthTV.setText(Html.fromHtml(wd));
        String ht = "<b>Height : </b>"+details.get(i).getHeight();
        viewHolder.heightTV.setText(Html.fromHtml(ht));
        viewHolder.statusTV.setText(details.get(i).getStatus());
        if (details.get(i).getStatus().equalsIgnoreCase("Status : OPEN")) {
            viewHolder.statusTV.setTextColor(Color.rgb(0, 200, 0));
        } else {
            viewHolder.statusTV.setTextColor(Color.rgb(200, 0, 0));
        }
        viewHolder.package_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), PackagingDetails.class);
                in.putExtra("from","logistic");
                in.putExtra("packageList", (Serializable) logisticPackageData.get(i).getGenericPackageLists());
                in.putExtra("shipper", logisticPackageData.get(i).getAsnCreatedOrgName());
                in.putExtra("shipdate", logisticPackageData.get(i).getShipmentDate());
                in.putExtra("arrival", logisticPackageData.get(i).getPortofArrival());
                in.putExtra("departure", logisticPackageData.get(i).getPortofDeparture());
                in.putExtra("bol", logisticPackageData.get(i).getBillofLadingNumber());
                in.putExtra("asn", logisticPackageData.get(i).getAsnNumber());
                v.getContext().startActivity(in);
            }
        });
        viewHolder.bol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ApiClient.Base_URL2+"download?filePath="+logisticPackageData.get(i).getDocumentRefURL();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                v.getContext().startActivity(i);
            }
        });
        if (activity == 1) {
            viewHolder.reject.setVisibility(View.GONE);
            viewHolder.accept.setText("\t\tPick Up Shipment\t\t");
        }
        if (activity == 2) {
            viewHolder.reject.setVisibility(View.GONE);
            viewHolder.accept.setText("\t\tDeliver Shipment\t\t");
        }
        viewHolder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Confirm accept")
                        .setMessage("Do you confirm ?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                sendtoserver(i);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
        viewHolder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Confirm Reject")
                        .setMessage("Do you confirm ?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(v.getContext(), "Package Rejected", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return details.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView awbTV;
        private TextView addressTV;
        private TextView quantityTV;
        private TextView lengthTV;
        private TextView widthTV;
        private TextView heightTV;
        private TextView statusTV;
        private TextView package_details;
        private TextView bol;
        private Button accept;
        private Button reject;

        public ViewHolder(View view) {
            super(view);
            awbTV = view.findViewById(R.id.awbTV);
            addressTV = view.findViewById(R.id.addressTV);
            quantityTV = view.findViewById(R.id.quantityTV);
            lengthTV = view.findViewById(R.id.lengthTV);
            widthTV = view.findViewById(R.id.widthTV);
            heightTV = view.findViewById(R.id.heightTV);
            statusTV = view.findViewById(R.id.statusTV);
            package_details = view.findViewById(R.id.pdTV);
            bol = view.findViewById(R.id.bolTV);
            accept = view.findViewById(R.id.acceptBT);
            reject = view.findViewById(R.id.rejectBT);
        }
    }
    public void sendtoserver(int i) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        String dt = sdf.format(date);
        LogisticAcceptRequest packageAccept = new LogisticAcceptRequest();
        packageAccept.setAsnRefNumber(logisticPackageData.get(i).getAsnRefNumber());
        packageAccept.setAcceptance("true");
        packageAccept.setUpdatedby(PrefUtils.getFromPrefs(context, PrefUtils.user_email,""));
        packageAccept.setUpdatedDate(dt);
        packageAccept.setOrgName(PrefUtils.getFromPrefs(context, PrefUtils.user_dcorg,""));
        packageAccept.setAsnCreatedOrgID(logisticPackageData.get(i).getAsnCreatedOrgID());
        if (activity == 0) {
            packageAccept.setFlag("acceptance");
        } else if (activity == 1) {
            packageAccept.setFlag("pickup");
        } else if (activity == 2) {
            packageAccept.setFlag("delivery");
        }
        Gson gson = new Gson();
        String json = gson.toJson(packageAccept);
        Map<String, String> map = new HashMap<>();
        map = gson.fromJson(json, map.getClass());
        apiServices = ApiClient.getPackageClient().create(ApiInterface.class);
        Call<LogisticPackageAcceptResponse> call = apiServices.sendLogisticAcceptance(map);
        call.enqueue(new Callback<LogisticPackageAcceptResponse>() {
            @Override
            public void onResponse(Call<LogisticPackageAcceptResponse> call, Response<LogisticPackageAcceptResponse> response) {
                progressDialog.dismiss();
                new AlertDialog.Builder(context)
                        .setTitle(response.body().getStatus())
                        .setMessage("Transaction ID : " + response.body().getLogisticPackageAcceptData().getTransactionID())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if(context instanceof LogisticsHome){
                                    ((LogisticsHome)context).loadPackageApi();
                                }
                                else if(context instanceof LogisticsPickup){
                                    ((LogisticsPickup)context).loadPackageApi();
                                }
                                else if(context instanceof LogisticsDeliver){
                                    ((LogisticsDeliver)context).loadPackageApi();
                                }
                            }
                        }).show();
            }
            @Override
            public void onFailure(Call<LogisticPackageAcceptResponse> call, Throwable t) {
                progressDialog.dismiss();
                new AlertDialog.Builder(context)
                        .setTitle("Failed to connect!")
                        .setMessage("Try connecting to server again?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        }).show();
            }
        });
    }
}