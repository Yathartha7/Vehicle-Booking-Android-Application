package com.example.uberappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Payment_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Button payNowButton = findViewById(R.id.payNowBtn);
        Intent intent = getIntent();
        int billAmount = intent.getIntExtra("BILL_AMOUNT", 0);
        int peopleCount = intent.getIntExtra("PEOPLE_COUNT", 0);
        int hours = intent.getIntExtra("HOURS", 0);
        //int minutes = intent.getIntExtra("MINUTES", 0);
        String minutes = intent.getStringExtra("MINUTES");
        String selectedMode = intent.getStringExtra("SELECTED_MODE");

        // Find views in the layout
        TextView vehicleTypeTextView = findViewById(R.id.vehicleType);
        ImageView vehicleImageView = findViewById(R.id.vehicleImage);
        TextView peopleTextView = findViewById(R.id.people);
        TextView hrsTextView = findViewById(R.id.hrs);
        TextView minsTextView = findViewById(R.id.mins);
        TextView billAmtTextView = findViewById(R.id.billAmt);

        // Update views with data from the intent
        vehicleTypeTextView.setText(selectedMode);
        // Set vehicle image based on the selected mode
        if ("Cab".equals(selectedMode)) {
            vehicleImageView.setImageResource(R.drawable.car);
        } else if ("Auto Rickshaw".equals(selectedMode)) {
            vehicleImageView.setImageResource(R.drawable.auto);
        } else if ("2 wheeler".equals(selectedMode)) {
            vehicleImageView.setImageResource(R.drawable.bike);
        }
        peopleTextView.setText(String.valueOf(peopleCount));
        hrsTextView.setText(String.valueOf(hours));
        minsTextView.setText(minutes);
        billAmtTextView.setText(String.format("%.2f", billAmount / 1.0));
        // Set OnClickListener for the Pay now button
        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event
                // For now, let's navigate to the SplashPaymentActivity

                Intent intent = new Intent(Payment_activity.this, payment_splash.class);
                startActivity(intent);
            }
        });
    }
}