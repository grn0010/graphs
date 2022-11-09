package com.example.app_graphs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lecho.lib.hellocharts.model.*;
import lecho.lib.hellocharts.view.LineChartView;

public class History extends AppCompatActivity {

    LineChartView chart_line_history;
    String[] xAxis = {Date(21), Date(14), Date(7), Date(0)};
    int[] yAxis = {3923, 4755, 5123, 5234}; //static for now

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        chart_line_history = findViewById(R.id.linechart);

        /*variables for graph*/
        List xAxisValues = new ArrayList();
        List yAxisValues = new ArrayList();
        List line = new ArrayList();
        Line graph_line = new Line(yAxisValues).setColor(Color.RED);
        Axis axisLabel = new Axis();
        Axis yAxisLabel = new Axis();
        Axis yAxisB = new Axis();
        LineChartData linechart_hist = new LineChartData();

        //adds x/y-axis values into lists
        for(int a=0; a<xAxis.length;a++){
            xAxisValues.add(a, new AxisValue(a).setLabel(xAxis[a]));
        }
        for(int a=0; a<yAxis.length;a++){
            yAxisValues.add(new PointValue(a, yAxis[a]));
        }

        /*graph visuals*/
        axisLabel.setTextSize(16);
        axisLabel.setTextColor(Color.BLACK);
        yAxisB.setTextSize(9);
        yAxisB.setTextColor(Color.BLACK);
        yAxisB.setMaxLabelChars(5);
        axisLabel.setValues(xAxisValues);
        linechart_hist.setAxisXBottom(axisLabel);
        linechart_hist.setAxisYLeft(yAxisB);

        line.add(graph_line);
        linechart_hist.setLines(line);

        Viewport viewport = new Viewport(chart_line_history.getMaximumViewport());
        viewport.top = 10000;
        viewport.bottom = 0;
        chart_line_history.setMaximumViewport(viewport);
        chart_line_history.setCurrentViewport(viewport);

        chart_line_history.setLineChartData(linechart_hist);
    }

    public String Date(int a) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        calendar.add(Calendar.DATE, -a);
        return dateFormat.format(calendar.getTime());
    }

}

