package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.ekn.gruzer.gaugelibrary.ArcGauge;
import com.ekn.gruzer.gaugelibrary.Range;

import java.util.stream.IntStream;

public class guageandseekbar extends AppCompatActivity {

    ArcGauge arcGauge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guageandseekbar);

        arcGauge = findViewById(R.id.arcGauge);



        Range range = new Range();
        range.setColor(Color.parseColor("#ce0000"));
        range.setFrom(0.0);
        range.setTo(150.0);


        arcGauge.setMinValue(0.0);
        arcGauge.setMaxValue(100.0);
        arcGauge.setValue(35);

        arcGauge.addRange(range);


    }
}