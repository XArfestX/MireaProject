package ru.mirea.burmakin.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sensors#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sensors extends Fragment implements SensorEventListener {
    private TextView azimuthTextView;
    private TextView pitchTextView;
    private TextView rollTextView;
    private TextView lightTextView;
    private TextView pressureTextView;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor lightSensor;
    private Sensor pressureSensor;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Sensors() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Sensor.
     */
    // TODO: Rename and change types and number of parameters
    public static Sensors newInstance(String param1, String param2) {
        Sensors fragment = new Sensors();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sensor, null);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);


        azimuthTextView = v.findViewById(R.id.textViewAzimuth);
        pitchTextView = v.findViewById(R.id.textViewPitch);
        rollTextView = v.findViewById(R.id.textViewRoll);
        lightTextView = v.findViewById(R.id.textViewLight);
        pressureTextView = v.findViewById(R.id.textViewPressure);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float valueAzimuth = sensorEvent.values[0];
            float valuePitch = sensorEvent.values[1];
            float valueRoll = sensorEvent.values[2];
            azimuthTextView.setText("Azimuth: " + valueAzimuth);
            pitchTextView.setText("Pitch: " + valuePitch);
            rollTextView.setText("Roll: " + valueRoll);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            float valueLight = sensorEvent.values[0];
            lightTextView.setText("Light: " + valueLight);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float valuePressure = sensorEvent.values[0];
            pressureTextView.setText("Pressure: " + valuePressure);
        }
    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pressureSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int i) {
    }
}











