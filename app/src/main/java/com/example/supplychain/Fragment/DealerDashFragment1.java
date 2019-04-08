package com.example.supplychain.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.supplychain.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
public class DealerDashFragment1 extends Fragment{
    private BarChart mchart;
    public DealerDashFragment1() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dealer_dash1, container, false);
        mchart = (BarChart) v.findViewById(R.id.barchart);
        mchart.setMaxVisibleValueCount(40);
        setData(10);
        return v;
    }
    public void setData(int count)
    {
        ArrayList<BarEntry> yValues= new ArrayList<>();
        for(int i=0; i<count; i++)
        {
            float val1 = (float)(Math.random()* count);
            float val2 = (float)(Math.random()* count);
            float val3 = (float)(Math.random()* count);
            yValues.add(new BarEntry(i,new float[]{val1,val2,val3}));
        }
        BarDataSet set1;
        set1 = new BarDataSet(yValues,"Statistics");
        set1.setDrawIcons(false);
        set1.setStackLabels(new String[]{"Val1", "Val2", "Val3"});
        set1.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData data = new BarData(set1);
        mchart.setData(data);
        mchart.setFitBars(true);
        mchart.invalidate();
    }
}