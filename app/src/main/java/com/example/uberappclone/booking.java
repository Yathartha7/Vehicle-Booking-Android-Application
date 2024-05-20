package com.example.uberappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;

public class booking extends AppCompatActivity {
    private ImageButton carImg, autoImg, bikeImg;
    private NumberPicker peopleCount, hours, minutes;
    private int maxPeopleCount = 4;
    private int distance = 6; // Assuming the default distance is 1
    private int mode = 1; // Assuming the default mode is 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Button proceedButton = findViewById(R.id.proceedButton);
        Button mapsbtn = findViewById(R.id.mapsBtn);
        carImg = findViewById(R.id.carImg);
        autoImg = findViewById(R.id.autoImg);
        bikeImg = findViewById(R.id.bikeImg);
        peopleCount = findViewById(R.id.peopleCount);
        hours = findViewById(R.id.hours);
        minutes = findViewById(R.id.minutes);

        // Set default values for the NumberPickers
        peopleCount.setMinValue(1);
        peopleCount.setMaxValue(maxPeopleCount);

        hours.setMinValue(0);
        hours.setMaxValue(23);

        String[] minuteValues = {"00", "15", "30", "45"};
        minutes.setMinValue(0);
        minutes.setMaxValue(minuteValues.length - 1);
        minutes.setDisplayedValues(minuteValues);

        // Set onClickListeners for ImageButtons
        carImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePeopleCountMax(4);
                mode = 10;
                carImg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                autoImg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                bikeImg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
            }
        });

        autoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePeopleCountMax(3);
                mode = 7; // Set mode to 7 for auto
                autoImg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                carImg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                bikeImg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
            }
        });

        bikeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePeopleCountMax(1);
                mode = 5;
                // Set mode to 5 for bike
                bikeImg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                autoImg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                carImg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
            }
        });

        // Set OnClickListener for the Proceed button
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected values
                int selectedPeopleCount = peopleCount.getValue();
                int selectedHours = hours.getValue();
                int selectedMinutes = minutes.getValue();

                // Calculate the bill based on the user's choices
                int bill = selectedPeopleCount * distance * mode;

                // Determine the selected mode as a string
                String selectedMode = "";
                if (mode == 10) {
                    selectedMode = "Cab";
                } else if (mode == 7) {
                    selectedMode = "Auto Rickshaw";
                } else if (mode == 5) {
                    selectedMode = "2 wheeler";
                }

                // For now, let's navigate to the PaymentActivity
                Intent intent = new Intent(booking.this, Payment_activity.class);
                intent.putExtra("BILL_AMOUNT", bill);
                intent.putExtra("PEOPLE_COUNT", selectedPeopleCount);
                intent.putExtra("HOURS", selectedHours);
                intent.putExtra("MINUTES", minuteValues[minutes.getValue()]);
                intent.putExtra("SELECTED_MODE", selectedMode);
                startActivity(intent);
            }
        });

        mapsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(booking.this,MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updatePeopleCountMax(int maxValue) {
        maxPeopleCount = maxValue;
        peopleCount.setMaxValue(maxPeopleCount);
    }
}
