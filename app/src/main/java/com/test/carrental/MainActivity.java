package com.test.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner carListSpinner;
    ArrayList<Car> carArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        carListSpinner = (Spinner) findViewById(R.id.carListSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, carArrayList);

        carListSpinner.setAdapter(adapter);

    }

    public void fillData () {
        carArrayList.add(new Car("Toyota", 23, true));
        carArrayList.add(new Car("BMW", 25, true));
        carArrayList.add(new Car("Mercedes", 30, true));
        carArrayList.add(new Car("Lamborgini", 50.25,false));
        carArrayList.add(new Car("Ferrari", 40.50, true));
    }
}