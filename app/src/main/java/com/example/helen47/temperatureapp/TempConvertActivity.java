package com.example.helen47.temperatureapp;

import android.app.Activity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

/**
 * The TempConvertActivity class implements a function that realizes conversion of temperature from
 * Celsius to Fahrenheit or from Fahrenheit to Celsius and display them on the main user interface.
 *
 * @author Siqi Chen
 * @version 1.0
 * @since 2016-03-03
 */
public class TempConvertActivity {

    private Activity mContext;

    private int[] textViewIds = new int[] {R.id.tv_mon_temp, R.id.tv_tues_temp, R.id.tv_wed_temp,
            R.id.tv_thurs_temp, R.id.tv_fri_temp};

    /**
     * This method is used to register OnCheckedChangeListener for switch button.
     *
     * @param context This is a reference to main activity context
     */
    public TempConvertActivity(Activity context) {

        mContext = context;

        Switch btnChangeScale = (Switch) mContext.findViewById(R.id.btn_change_scale);
        btnChangeScale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            /**
             * This method is called when the checked state of the switch button has changed.
             * Step 1: gather temperature data on the main user interface into an float array.
             * Step 2: pass the list of temperature to JNI component.
             * Step 3: show the list of new temperature data returned from JNI component on the
             * main user interface limited to two decimals with temperature scale.
             */
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                float[] temperatureValue = new float[textViewIds.length];
                for(int i = 0; i < textViewIds.length; i++) {
                    TextView tvTemperature = (TextView) mContext.findViewById(textViewIds[i]);
                    String[] strTempWithScale = tvTemperature.getText().toString().split(" ");
                    String strTemperature = strTempWithScale[0];
                    temperatureValue[i] = Float.parseFloat(strTemperature);
                }


                float[] newTemperatureValue;
                if(isChecked) {
                    // Fahrenheit -> Celsius
                    newTemperatureValue = convertTemperature(temperatureValue, false);
                } else {
                    // Celsius -> Fahrenheit
                    newTemperatureValue = convertTemperature(temperatureValue, true);
                }


                String strNewScale = isChecked ? mContext.getResources().getString(R.string.celsius)
                        : mContext.getResources().getString(R.string.fahrenheit);
                for(int i = 0; i < textViewIds.length; i++) {
                    TextView tvTemperature = (TextView) mContext.findViewById(textViewIds[i]);
                    String strNewTemperature = String.format("%.2f %s", newTemperatureValue[i],
                            strNewScale);
                    tvTemperature.setText(strNewTemperature);
                }

            }
        });
    }

    /**
     * This is a static initializer to load a shared library.
     * It declares a native method called convertTemperature.
     */
    static {
        System.loadLibrary("temperature-app");
    }

    /**
     * This native method is used to convert temperature between two temperature scales.
     *
     * @param floatArray This is the list of temperature data needed to be converted
     * @param bool This indicates whether the current scale is Celsius
     * @return floatArray This is a list of new temperature data after conversion
     */
    public native float[] convertTemperature(float[] floatArray, boolean bool);
}
