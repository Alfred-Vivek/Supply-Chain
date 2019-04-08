package com.example.supplychain.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.supplychain.R;
import com.example.supplychain.Response.GenericPackageList;

import java.util.ArrayList;
import java.util.List;

public class PackagingDetails extends AppCompatActivity {
    String shipName,shipDate,arrival,departure,bol,asn;
    String tracking,release,storage,volume,addr;
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    TextView tit1,tit2,tit3,tit4,tit5,tit6;
    String from;
    List<GenericPackageList> genericPackageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packaging_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent i = getIntent();
        genericPackageList = (List<GenericPackageList>) i.getSerializableExtra("packageList");
        from = i.getStringExtra("from");
        if(from.equalsIgnoreCase("logistic"))
        {
            shipName = i.getStringExtra("shipper");
            shipDate = i.getStringExtra("shipdate");
            arrival = i.getStringExtra("arrival");
            departure = i.getStringExtra("departure");
            bol = i.getStringExtra("bol");
            asn = i.getStringExtra("asn");
        }
        else if(from.equalsIgnoreCase("tpw"))
        {
            tracking = i.getStringExtra("tracking");
            release = i.getStringExtra("release");
            storage = i.getStringExtra("storage");
            volume = i.getStringExtra("volume");
            addr = i.getStringExtra("addr");
        }
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tit1 = findViewById(R.id.tit1);
        tit2 = findViewById(R.id.tit2);
        tit3 = findViewById(R.id.tit3);
        tit4 = findViewById(R.id.tit4);
        tit5 = findViewById(R.id.tit5);
        tit6 = findViewById(R.id.tit6);
        if(from.equalsIgnoreCase("logistic"))
        {
            tv1.setText(" : "+shipName);
            tv2.setText(" : "+shipDate);
            tv3.setText(" : "+arrival);
            tv4.setText(" : "+departure);
            tv5.setText(" : "+bol);
            tv6.setText(" : "+asn);
            tit1.setText("\tShipper Name");
            tit2.setText("\tShipment Date");
            tit3.setText("\tPort of Arrival");
            tit4.setText("\tPort of Departure");
            tit5.setText("\tBOL Number");
            tit6.setText("\tASN Number");
            showInfo();
        }
        else if(from.equalsIgnoreCase("tpw"))
        {
            tv1.setText(" : "+tracking);
            tv2.setText(" : "+release);
            tv3.setText(" : "+storage);
            tv4.setText(" : "+volume);
            tv5.setText(" : "+addr);
            tit1.setText("\tTracking Number");
            tit2.setText("\tRelease Date");
            tit3.setText("\tStorage Days");
            tit4.setText("\tStorage Volume");
            tit5.setText("\tShip to Location");
            tit6.setVisibility(View.GONE);
            tv6.setVisibility(View.GONE);
            showInfo();
        }
    }
    @Override
    public void onBackPressed() {
        PackagingDetails.this.finish();
    }
    public void showInfo(){
        TextView itemTV;
        TextView quantityTV;
        TextView lengthTV;
        TextView widthTV;
        TextView heightTV;
        TextView paadTV;
        LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout track=(LinearLayout)findViewById(R.id.itemLL);
        List views=new ArrayList();
        for(int i = 0; i< genericPackageList.size(); i++)
        {
            View view=layoutInflater.inflate(R.layout.item_data, null);
            itemTV = (TextView)view.findViewById(R.id.itemTV);
            quantityTV = (TextView) view.findViewById(R.id.quantityTV);
            lengthTV = (TextView) view.findViewById(R.id.lengthTV);
            widthTV = (TextView) view.findViewById(R.id.widthTV);
            heightTV = (TextView) view.findViewById(R.id.heightTV);
            paadTV = (TextView) view.findViewById(R.id.paddTV);
            itemTV.setText("\t\tItem : "+ genericPackageList.get(i).getItemName());
            quantityTV.setText("\t\tQuantity : "+ genericPackageList.get(i).getQuantity());
            lengthTV.setText("\t\tLength : "+ genericPackageList.get(i).getLength());
            widthTV.setText("Width : "+ genericPackageList.get(i).getWidth());
            heightTV.setText("Height : "+ genericPackageList.get(i).getHeight());
            paadTV.setText("\t\tPackage Added On: "+ genericPackageList.get(i).getPackageAddedDate()+"\n\n");
            views.add(view);        }
        for(int i=0; i<views.size(); i++)
        {
            track.addView((View) views.get(i));
        }
    }
}