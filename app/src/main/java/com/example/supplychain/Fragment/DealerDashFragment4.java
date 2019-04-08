package com.example.supplychain.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supplychain.Activity.AwbTrack;
import com.example.supplychain.Activity.ShipmentDetails;
import com.example.supplychain.Activity.ShipmentTrack;
import com.example.supplychain.Pojo.DealerModel;
import com.example.supplychain.R;

import java.util.ArrayList;

public class DealerDashFragment4 extends Fragment{
    public DealerDashFragment4() {
    }
    private Spinner spinner1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_dealer_dash3, container, false);
        spinner1 = (Spinner) v.findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new ItemSelectedListener());
        TableLayout tl = (TableLayout) v.findViewById(R.id.dealerTL);
        init(tl);
        return v;
    }
    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {
        //get strings of first item
        String firstItem = String.valueOf(spinner1.getSelectedItem());
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinner1.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                Toast.makeText(parent.getContext(),
                        "You have selected : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                // Todo when item is selected by the user
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> arg) {
        }
    }
    @SuppressLint("ResourceAsColor")
    public void init(TableLayout tl){
        ArrayList<DealerModel> details = new ArrayList<DealerModel>();
        DealerModel dm1 = new DealerModel("1200121","12232212","1223321",(int)(Math.random()*10000000)+"","Alarm",1000,"02-12-2019","Hyderabad","Order Generated");
        DealerModel dm2 = new DealerModel("1200121","12232212","1223321",(int)(Math.random()*10000000)+"","Smart Phone",2000,"02-12-2019","Mumbai","Order Generated");
        DealerModel dm3 = new DealerModel("1200121","12232212","1223321",(int)(Math.random()*10000000)+"","Laptop",100,"02-12-2019","Hyderabad","Order Accepted");
        DealerModel dm4 = new DealerModel("1200121","12232212","1223321",(int)(Math.random()*10000000)+"","Smart Watch",500,"02-12-2019","Chennai","Order is in progress");
        DealerModel dm5 = new DealerModel("1200121","12232212","1223321",(int)(Math.random()*10000000)+"","Keyboard",100,"02-12-2019","Hyderabad","Order Is in Transit");
        DealerModel dm6 = new DealerModel("1200121","12232212","1223321",(int)(Math.random()*10000000)+"","Mouse",1000,"02-12-2019","Hyderabad","Ready for shipment");
        DealerModel dm7 = new DealerModel("1200121","12232212","1223321",(int)(Math.random()*10000000)+"","Rasberry Pie",300,"02-12-2019","Hyderabad","Order Generated");
        details.add(dm1);
        details.add(dm2);
        details.add(dm3);
        details.add(dm4);
        details.add(dm5);
        details.add(dm6);
        details.add(dm7);
        for (int i = 0; i <details.size(); i++) {
            TableRow row= new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView shipno = new TextView(getContext());
            TextView item = new TextView(getContext());
            TextView status = new TextView(getContext());
            final TextView awb = new TextView(getContext());
            TextView more = new TextView(getContext());
            View v = new View(getContext());

            shipno.setText(details.get(i).getShipno());
            shipno.setWidth(210);
            shipno.setTextColor(Color.BLACK);
            shipno.setPadding(5,5,5,5);
            shipno.setTextSize(14);
            shipno.setEllipsize(TextUtils.TruncateAt.END);

            item.setText(details.get(i).getItem());
            item.setWidth(280);
            item.setTextColor(Color.BLACK);
            item.setPadding(5,5,5,5);
            item.setTextSize(14);
            item.setEllipsize(TextUtils.TruncateAt.END);

            status.setText(details.get(i).getStatus());
            status.setWidth(200);
            status.setHeight(120);
            status.setTextColor(R.color.navigationBarColor);
            status.setPadding(5,5,5,5);
            status.setTextSize(14);
            status.setEllipsize(TextUtils.TruncateAt.END);
            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), ShipmentTrack.class);
                    startActivity(i);
                }
            });

            awb.setText(details.get(i).getAwb());
            awb.setWidth(250);
            awb.setTextColor(R.color.navigationBarColor);
            awb.setPadding(5,5,5,5);
            awb.setTextSize(14);
            awb.setEllipsize(TextUtils.TruncateAt.END);
            awb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), AwbTrack.class);
                    startActivity(i);
                }
            });

            more.setText("+");
            more.setWidth(100);
            more.setTextColor(Color.BLACK);
            more.setPadding(5,5,5,5);
            more.setTextSize(24);
            more.setClickable(true);

            v.setLayoutParams(new ViewGroup.LayoutParams(500, 1));
            v.setBackgroundColor(Color.rgb(185,185,185));

            row.addView(shipno);
            row.addView(item);
            row.addView(status);
            row.addView(awb);
            row.addView(more);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),awb.getText(),Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getContext(), ShipmentDetails.class);
                    startActivity(i);
                }
            });
            tl.addView(row);
            tl.addView(v);
        }
    }
}
