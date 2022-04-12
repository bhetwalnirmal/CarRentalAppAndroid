package com.test.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    Spinner carListSpinner;
    ImageView carImage;
    ArrayList<Car> carArrayList = new ArrayList<>();
    ArrayList<Car> availableCarList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        carImage = (ImageView) findViewById(R.id.carImage);
        carListSpinner = (Spinner) findViewById(R.id.carListSpinner);
        availableCarList = getAvailableCarList();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, availableCarList);
        carListSpinner.setAdapter(adapter);

        carListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Car car = carArrayList.get(i);
                carImage.setImageResource(car.getImage());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void fillData () {
        carArrayList.add(new Car("Toyota", 23, true, R.drawable.toyota));
        carArrayList.add(new Car("Honda", 25, true, R.drawable.honda));
        carArrayList.add(new Car("Ford", 30, true, R.drawable.ford));
        carArrayList.add(new Car("Lamborgini", 50.25,true, R.drawable.lamborgini));
        carArrayList.add(new Car("Nissan", 40.50, true, R.drawable.nissan));
    }

    public ArrayList<Car> getAvailableCarList () {
        return (ArrayList<Car>) carArrayList.stream().filter(car -> {
            return car.getStatus();
        }).collect(Collectors.toList());
    }
}