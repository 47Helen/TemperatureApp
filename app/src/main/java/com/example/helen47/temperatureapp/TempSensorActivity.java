package com.example.helen47.temperatureapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

/**
 * The TempSensorActivity program implements a function that detects ambient temperature
 * via build-in temperature sensor and display it on the main user interface.
 *
 * @author Siqi Chen
 * @version 1.0
 * @since 2016-03-03
 */
public class TempSensorActivity extends MainActivity implements SensorEventListener {
    private final SensorManager mSensorManager;
    private final Sensor mTemperature;

    private Activity mContext;

    /**
     * This method is used to initialize Sensormanager and choose temperature sensor type.
     * It also displays an info "loading" before getting the ambient temperature data.
     *
     * @param context This is a reference to main activity context
     */
    public TempSensorActivity(Activity context) {
        mContext = context;
        mSensorManager = (SensorManager)context.getSystemService(SENSOR_SERVICE);
        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        TextView tvTemperature = (TextView) mContext.findViewById(R.id.tv_ambinet_temp);
        tvTemperature.setText(R.string.info_loading);
    }

    /**
     * This method is used to register a SensorEventListener for the given temperature sensor
     * at the given sampling frequency.
     */
    public void registerService() {
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * This method is used to unregister the listener for the temperature sensor.
     */
    public void unregisterService() {
        mSensorManager.unregisterListener(this);
    }

    /**
     * This method is used to update ambient temperature value on the main user interface
     * when the temperature sensor provides new temperature data.
     * The temperature shown are limited to two decimals with temperature scale (Celsius).
     *
     * @param event This is the sensor event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        float temperatureValue = event.values[0];
        String strTemperature = String.format("%.2f %s", temperatureValue,
                mContext.getResources().getString(R.string.celsius));
        TextView tvTemperature = (TextView) mContext.findViewById(R.id.tv_ambinet_temp);
        tvTemperature.setText(strTemperature);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
