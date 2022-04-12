package com.test.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner carListSpinner;
    ImageView carImage;
    ArrayList<Car> carArrayList = new ArrayList<>();
    ArrayList<Car> availableCarList = new ArrayList<>();
    RadioGroup radioGroup;
    RadioButton rbLessThan21, rbMiddle, rb65;
    TextView total;
    Car selectedCar = null;
    EditText etNumberOfDays;
    CheckBox cbNavigator, cbChildSeat, cbUnlimited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        rbLessThan21 = (RadioButton) findViewById(R.id.rbLessThan21);
        rbMiddle = (RadioButton) findViewById(R.id.rbMiddle);
        rb65 = (RadioButton) findViewById(R.id.rbSenior);

        cbNavigator = (CheckBox) findViewById(R.id.navigator);
        cbChildSeat = (CheckBox) findViewById(R.id.childSeat);
        cbUnlimited = (CheckBox) findViewById(R.id.unlimited);

        total = (TextView) findViewById(R.id.total);
        etNumberOfDays = (EditText) findViewById(R.id.numberOfDays);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        carImage = (ImageView) findViewById(R.id.carImage);
        carListSpinner = (Spinner) findViewById(R.id.carListSpinner);
        availableCarList = getAvailableCarList();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, availableCarList);
        carListSpinner.setAdapter(adapter);

        carListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Car car = carArrayList.get(i);
                selectedCar = car;
                carImage.setImageResource(car.getImage());
                updateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbLessThan21:
                        updateTotal();
                        break;
                    case R.id.rbMiddle:
                        updateTotal();
                        break;
                    case R.id.rbSenior:
                        updateTotal();
                        break;
                    default:
                        break;
                }
            }
        });

        etNumberOfDays.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateTotal();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cbUnlimited.setOnClickListener(this);
        cbChildSeat.setOnClickListener(this);
        cbNavigator.setOnClickListener(this);
    }

    public void fillData () {
        carArrayList.add(new Car("Toyota", 23, true, R.drawable.toyota));
        carArrayList.add(new Car("Honda", 25, true, R.drawable.honda));
        carArrayList.add(new Car("Ford", 30, true, R.drawable.ford));
        carArrayList.add(new Car("Lamborgini", 50.25,true, R.drawable.lamborgini));
        carArrayList.add(new Car("Nissan", 40.50, false, R.drawable.nissan));
    }

    public ArrayList<Car> getAvailableCarList () {
        return (ArrayList<Car>) carArrayList.stream().filter(car -> {
            return car.getStatus();
        }).collect(Collectors.toList());
    }

    public void updateTotal () {
        double grandTotal = 0;
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedCar != null) {
            int numberOfDays = 0;
            try {
                numberOfDays = Integer.parseInt(etNumberOfDays.getText().toString());

                if (cbNavigator.isChecked()) {
                    grandTotal += numberOfDays * 7;
                }

                if (cbChildSeat.isChecked()) {
                    grandTotal += numberOfDays * 5;
                }

                if (cbUnlimited.isChecked()) {
                    grandTotal += numberOfDays * 15;
                }
            } catch (Exception exception) {
                numberOfDays = 0;
            }
            grandTotal += selectedCar.getDailyRate() * numberOfDays;
        }
        
        try {
            int numberOfDays = Integer.parseInt(etNumberOfDays.getText().toString());

            switch(selectedId) {
                case R.id.rbLessThan21:
                    grandTotal += numberOfDays * 15;
                    break;
                case R.id.rbMiddle:
                    grandTotal += numberOfDays * 7;
                    break;
                case R.id.rbSenior:
                    grandTotal += numberOfDays * 10;
                    break;

                default:
                    break;
            }
        } catch (Exception e) {

        }

        total.setText(String.format("$ %.2f", grandTotal));
    }

    @Override
    public void onClick(View view) {
        updateTotal();
    }
}