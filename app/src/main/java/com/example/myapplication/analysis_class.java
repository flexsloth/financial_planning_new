package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class analysis_class extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ProgressBar circularProgressBar;
    private TextView percentageTextView;
    BarChart barChart;
    private ImageView back,calender_open;
    private PieChart pieChart;
    private ArrayList<PieEntry> pieEntryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.analysis_xml,container,false);
        //getChildFragmentManager().beginTransaction().replace(R.id.frameLayout,new records_activity()).commit();
        barChart=(BarChart) view.findViewById(R.id.barChart);
        recyclerView = view.findViewById(R.id.scanned_info_table);
//        circularProgressBar = view.findViewById(R.id.circularProgressBar);
//        percentageTextView = view.findViewById(R.id.percentageTextView);
        pieEntryList = new ArrayList<>();
        pieChart=view.findViewById(R.id.chart);
        setValues();
        setUpChart();
        back=view.findViewById(R.id.back);
        calender_open=view.findViewById(R.id.calenderopen);
        int end=(125*100/200);
        back.setOnClickListener(this);
        calender_open.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        List<expence_details_item> data = new ArrayList<>();
        data.add(new expence_details_item("Bills","60000",R.drawable.ic__calculator));
        data.add(new expence_details_item("butter","50000",R.drawable.ic__calculator));
        data.add(new expence_details_item("apple","70000",R.drawable.ic__calculator));
        data.add(new expence_details_item("car","80000",R.drawable.ic__calculator));
        TableAdapter adapter = new TableAdapter(data);
        recyclerView.setAdapter(adapter);
        bargraph_display();
        return view;
    }
    private void bargraph_display() {
        String[] xAxisLabels = new String[]{"Total","Scanned", "Unscanned"};
        int[] yAxisValues = new int[]{200, 125, 75};
        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < xAxisLabels.length; i++) {
            entries.add(new BarEntry(i, yAxisValues[i]));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Number of Houses");
        BarData barData = new BarData(dataSet);

        barChart.setData(barData);

        // Setting X-axis labels
        barChart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisLabels));
        barChart.getXAxis().setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);

        barChart.getDescription().setEnabled(false);  // Hide description
        barChart.getLegend().setEnabled(false);  // Hide legend if not needed
        barChart.invalidate();
    }
    private void setUpChart(){
        PieDataSet pieDataSet = new PieDataSet(pieEntryList,"Pie Chart");
        PieData pieData=new PieData(pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(getResources().getColor(R.color.white));
        pieData.setValueTextSize(12f);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
    private void setValues(){
        pieEntryList.add(new PieEntry(200,"Food"));
        pieEntryList.add(new PieEntry(800,"Bills"));
        pieEntryList.add(new PieEntry(100,"Travel"));
    }
    @Override
    public void onClick(View view) {
        if(view==back){
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.swipe_in_left, R.anim.swipe_out_right);
            fragmentTransaction.replace(R.id.frameLayout,new home_profile_class());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if(view==calender_open){
            Toast.makeText(view.getContext(), "calender open", Toast.LENGTH_SHORT).show();
        }
    }
}
