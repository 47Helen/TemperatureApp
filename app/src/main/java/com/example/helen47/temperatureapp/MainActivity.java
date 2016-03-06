package com.example.helen47.temperatureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Random;

/**
 * The TemperatureApp program implements an application that simply displays 5 days
 * (Mon-Fri) and temperature for each day (bothn in Fahrenheit and Celsius),
 * as well as the ambient temperature of the phone.
 *
 * @author Siqi Chen
 * @version 1.0
 * @since 2016-03-03
 */
public class MainActivity extends AppCompatActivity {

    private TempSensorActivity sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMainUI();
        TempConvertActivity tempConvertHelper = new TempConvertActivity(this);
        sensor = new TempSensorActivity(this);

    }

    /**
     * This method is used to initialize main user interface.
     * There are five numbers generated in (15, 25), which can be treated as temperature values.
     * The temperature shown are limited to two decimals with temperature scale.
     */
    public void initMainUI() {

        int[] textViewIds = new int[] {R.id.tv_mon_temp, R.id.tv_tues_temp, R.id.tv_wed_temp,
                R.id.tv_thurs_temp, R.id.tv_fri_temp};

        Random rand = new Random();
        float minX = 15.0f;
        float maxX = 25.0f;

        float[] data = new float[textViewIds.length];
        for (int i = 0; i < textViewIds.length; i++) {
            TextView tv = (TextView) findViewById(textViewIds[i]);
            data[i] = rand.nextFloat() * (maxX - minX) + minX;
            String temp = String.format("%.2f %s", data[i], getResources().getString(R.string.celsius));
            tv.setText(temp);
        }

    }

    /**
     * This method implements a function that registers temperature sensor service.
     */
    @Override
    protected void onResume() {
        super.onResume();
        sensor.registerService();
    }

    /**
     * This method implements a function that unregisters temperature sensor service.
     */
    @Override
    protected void onPause() {
        super.onPause();
        sensor.unregisterService();
    }

}
