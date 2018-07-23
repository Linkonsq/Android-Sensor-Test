package sensortest.linkon.siddique.sensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mLight, mAccelorometer, mProximity;
    private TextView light, xAcc, yAcc, zAcc, proximity;
    private float lightVal, axVal, ayVal, azVal, distance;
    //private static DecimalFormat df3 = new DecimalFormat(".###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelorometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        xAcc = (TextView) findViewById(R.id.ax);
        yAcc = (TextView) findViewById(R.id.ay);
        zAcc = (TextView) findViewById(R.id.az);
        light = (TextView) findViewById(R.id.lightval);
        proximity = (TextView) findViewById(R.id.proxVal);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_LIGHT) {
            lightVal = event.values[0];
            light.setText(String.valueOf(Math.round(lightVal)) + " lx");
        }

        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            axVal = event.values[0];
            ayVal = event.values[1];
            azVal = event.values[2];
            xAcc.setText(String.valueOf(axVal) + " m/s2");
            yAcc.setText(String.valueOf(ayVal) + " m/s2");
            zAcc.setText(String.valueOf(azVal) + " m/s2");
        }

        if (event.sensor.getType()==Sensor.TYPE_PROXIMITY) {
            distance = event.values[0];
            proximity.setText(String.valueOf(distance));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mAccelorometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}