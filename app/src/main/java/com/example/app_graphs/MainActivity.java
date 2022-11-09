package com.example.app_graphs;

import android.app.slice.Slice;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
import android.database.sqlite.*;
import lecho.lib.hellocharts.view.PieChartView;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;

import static android.graphics.Color.*;

public class MainActivity extends AppCompatActivity {

    PieChartView chart_weekly;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        chart_weekly = findViewById(R.id.chart);
        List<SliceValue> chartslice_weekly = new ArrayList<>(); //Array for spending values
        final float cash_spreads, cash_toiletries, cash_snacks, cash_personalcare, cash_noodles, cash_dairy, cash_condiments, cash_coffee, cash_cleaningagents, cash_cereal, cash_cannedgoods, cash_total, per_spreads, per_toiletries, per_snacks, per_personalcare, per_noodles, per_dairy, per_condiments, per_coffee, per_cleaningagents, per_cereal, per_cannedgoods; //variables for categories
        DecimalFormat twodecs = new DecimalFormat("#.00"); //reduces values to two decimal places

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        //amounts spent on categories
        cash_spreads = (float) databaseAccess.getSpreads();
        cash_toiletries = (float) databaseAccess.getToiletries();
        cash_snacks = (float) databaseAccess.getSnacks();
        cash_personalcare = (float) databaseAccess.getPersonalCare();
        cash_noodles = (float) databaseAccess.getNoodles();
        cash_dairy = (float) databaseAccess.getDairy();
        cash_condiments = (float) databaseAccess.getCondiments();
        cash_coffee = (float) databaseAccess.getCoffee();
        cash_cleaningagents = (float) databaseAccess.getCleaningAgents();
        cash_cereal = (float) databaseAccess.getCereal();
        cash_cannedgoods = (float) databaseAccess.getCannedGoods();

        cash_total = cash_spreads + cash_personalcare + cash_toiletries + cash_snacks + cash_noodles + cash_dairy + cash_condiments +
                cash_coffee + cash_cleaningagents + cash_cereal + cash_cannedgoods;

        //percentage of spending per category
        per_spreads = (cash_spreads/cash_total)*100;
        per_toiletries = (cash_toiletries/cash_total)*100;
        per_snacks = (cash_snacks/cash_total)*100;
        per_personalcare = (cash_personalcare/cash_total)*100;
        per_noodles = (cash_noodles/cash_total)*100;
        per_dairy = (cash_dairy/cash_total)*100;
        per_condiments = (cash_condiments/cash_total)*100;
        per_coffee = (cash_coffee/cash_total)*100;
        per_cleaningagents = (cash_cleaningagents/cash_total)*100;
        per_cereal = (cash_cereal/cash_total)*100;
        per_cannedgoods = (cash_cannedgoods/cash_total)*100;

        //slices of weekly chart

        chartslice_weekly.add(new SliceValue(per_spreads, GREEN).setLabel("Spreads - P" + twodecs.format(cash_spreads) + " (" + twodecs.format(per_spreads) + "%)"));
        chartslice_weekly.add(new SliceValue(per_toiletries, BLUE).setLabel("Toiletries - P" + twodecs.format(cash_toiletries) + " (" + twodecs.format(per_toiletries) + "%)"));
        chartslice_weekly.add(new SliceValue(per_snacks, BLACK).setLabel("Snacks - P" + twodecs.format(cash_snacks) + " (" + twodecs.format(per_snacks) + "%)"));
        chartslice_weekly.add(new SliceValue(per_personalcare, RED).setLabel("Personal Care - P" + twodecs.format(cash_personalcare) + " (" + twodecs.format(per_personalcare) + "%)"));
        chartslice_weekly.add(new SliceValue(per_noodles, GRAY).setLabel("Noodles - P" + twodecs.format(cash_noodles) + " (" + twodecs.format(per_noodles) + "%)"));
        chartslice_weekly.add(new SliceValue(per_dairy, GREEN).setLabel("Dairy - P" + twodecs.format(cash_dairy) + " (" + twodecs.format(per_dairy) + "%)"));
        chartslice_weekly.add(new SliceValue(per_condiments, YELLOW).setLabel("Condiments - P" + twodecs.format(cash_condiments) + " (" + twodecs.format(per_condiments) + "%)"));
        chartslice_weekly.add(new SliceValue(per_coffee, MAGENTA).setLabel("Coffee - P" + twodecs.format(cash_coffee) + " (" + twodecs.format(per_coffee) + "%)"));
        chartslice_weekly.add(new SliceValue(per_cleaningagents, BLACK).setLabel("Cleaning Agents - P" + twodecs.format(cash_cleaningagents) + " (" + twodecs.format(per_cleaningagents) + "%)"));
        chartslice_weekly.add(new SliceValue(per_cereal, CYAN).setLabel("Cereal - P" + twodecs.format(cash_cereal) + " (" + twodecs.format(per_cereal) + "%)"));
        chartslice_weekly.add(new SliceValue(per_cannedgoods, MAGENTA).setLabel("Canned Goods - P" + twodecs.format(cash_cannedgoods) + " (" + twodecs.format(per_cannedgoods) + "%)"));

        //sets data inside chart_weekly
        PieChartData chartdata_weekly = new PieChartData(chartslice_weekly);
        chartdata_weekly.setHasLabels(true).setValueLabelTextSize(10);
        chartdata_weekly.setHasCenterCircle(true).setCenterText1("Total Spending: P" + twodecs.format(cash_total)).setCenterText1FontSize(8);
        chart_weekly.setPieChartData(chartdata_weekly);

        //buttons
        Button historyButton = (Button)findViewById(R.id.button_hist);

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_hist = new Intent(MainActivity.this, History.class);
                /*intent_hist.putExtra("cash_total", cash_total); */
                startActivity(intent_hist);
            }
        });



    }


}
