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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supplychain.Activity.PackagingDetails;
import com.example.supplychain.Activity.TpwHome;
import com.example.supplychain.Pojo.TpwModel;
import com.example.supplychain.R;
import com.example.supplychain.Request.TpwAcceptRequest;
import com.example.supplychain.Response.TpwpackageData;
import com.example.supplychain.Response.TpwPackageAcceptResponse;
import com.example.supplychain.Rest.ApiClient;
import com.example.supplychain.Rest.ApiInterface;
import com.example.supplychain.Utils.PrefUtils;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataAdapterTpw extends RecyclerView.Adapter<DataAdapterTpw.ViewHolder> {
    private ArrayList<TpwModel> details;
    public List<TpwpackageData> tpwpackageData;
    private ApiInterface apiServices;
    private Context context;
    ProgressDialog progressDialog;
    int activity;
    public DataAdapterTpw(Context context, ArrayList<TpwModel> details, List<TpwpackageData> tpwpackageData, int activity) {
        this.context = context;
        this.details = details;
        this.tpwpackageData = tpwpackageData;
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.generic_cardrow, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
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
        if (details.get(i).getStatus().equalsIgnoreCase("Status OPEN")) {
            viewHolder.statusTV.setTextColor(Color.rgb(0, 200, 0));
        } else {
            viewHolder.statusTV.setTextColor(Color.rgb(200, 0, 0));
        }
        viewHolder.package_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), PackagingDetails.class);
                in.putExtra("from","tpw");
                in.putExtra("packageList", (Serializable) tpwpackageData.get(i).getGenericPackageList());
                in.putExtra("tracking", tpwpackageData.get(i).getAwbNumber());
                in.putExtra("release", tpwpackageData.get(i).getReqReleaseDate());
                in.putExtra("storage", tpwpackageData.get(i).getReqStorageDays());
                in.putExtra("volume", tpwpackageData.get(i).getReqStorageVolume());
                in.putExtra("addr", tpwpackageData.get(i).getShiptoLocationAddress());
                v.getContext().startActivity(in);
            }
        });
        viewHolder.bol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ApiClient.Base_URL2+"download?filePath="+tpwpackageData.get(i).getDocumentRefURL();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                v.getContext().startActivity(i);
            }
        });
        viewHolder.titleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.showLL.getVisibility() == View.VISIBLE){
                    viewHolder.showLL.setVisibility(View.GONE);
                }
                else{
                    viewHolder.showLL.setVisibility(View.VISIBLE);
                }
            }
        });
        if (activity == 1) {
            viewHolder.reject.setVisibility(View.GONE);
            viewHolder.accept.setText("\tPick Up Shipment\t\t");
        }
        if (activity == 2) {
            viewHolder.reject.setVisibility(View.GONE);
            viewHolder.accept.setText("\tDeliver Shipment\t");
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
        private LinearLayout package_details;
        private LinearLayout bol;
        private Button accept;
        private Button reject;
        private LinearLayout titleTV;
        private LinearLayout showLL;

        public ViewHolder(View view) {
            super(view);
            awbTV = view.findViewById(R.id.awbTV);
            addressTV = view.findViewById(R.id.addressTV);
            quantityTV = view.findViewById(R.id.quantityTV);
            lengthTV = view.findViewById(R.id.lengthTV);
            widthTV = view.findViewById(R.id.widthTV);
            heightTV = view.findViewById(R.id.heightTV);
            statusTV = view.findViewById(R.id.statusTV);
            package_details = view.findViewById(R.id.pdLL);
            bol = view.findViewById(R.id.bolLL);
            accept = view.findViewById(R.id.acceptBT);
            reject = view.findViewById(R.id.rejectBT);
            titleTV = view.findViewById(R.id.titleTV);
            showLL = view.findViewById(R.id.showLL);
        }
    }
    public void sendtoserver(int i) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        TpwAcceptRequest tpwAcceptRequest = new TpwAcceptRequest();
        tpwAcceptRequest.setTpwKey(tpwpackageData.get(i).getAwbNumber()+"_"+ tpwpackageData.get(i).getCreatedOrgID());
        tpwAcceptRequest.setFlag("true");
        tpwAcceptRequest.setAcceptedBy(PrefUtils.getFromPrefs(context,PrefUtils.user_email,""));
        tpwAcceptRequest.setOrgName(PrefUtils.getFromPrefs(context,PrefUtils.user_dcorg,""));
        Gson gson = new Gson();
        final String json = gson.toJson(tpwAcceptRequest);
        Map<String, String> map = new HashMap<>();
        map = gson.fromJson(json, map.getClass());
        apiServices = ApiClient.getPackageClient().create(ApiInterface.class);
        Call<TpwPackageAcceptResponse> call = apiServices.sendTpwAcceptance(map);
        call.enqueue(new Callback<TpwPackageAcceptResponse>() {
            @Override
            public void onResponse(Call<TpwPackageAcceptResponse> call, Response<TpwPackageAcceptResponse> response) {
                progressDialog.dismiss();
                new AlertDialog.Builder(context)
                        .setTitle(response.body().getStatus())
                        .setMessage("Transaction ID : " + response.body().getTpwPackageAcceptData().getTransactionID())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if(context instanceof TpwHome){
                                    ((TpwHome)context).loadTpwApi();
                                }
                            }
                        }).show();
            }
            @Override
            public void onFailure(Call<TpwPackageAcceptResponse> call, Throwable t) {
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