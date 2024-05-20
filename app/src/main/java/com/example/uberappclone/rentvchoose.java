package com.example.uberappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class rentvchoose extends AppCompatActivity {

    private ImageButton b1,b2,b3,b4,b5,b6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentvchoose);
        b1 = findViewById(R.id.gearbtn);
        b2 = findViewById(R.id.nongearbtn);
        b3 = findViewById(R.id.sedanbtn);
        b4 = findViewById(R.id.suvbtn);
        b5 = findViewById(R.id.offroadbtn);
        b6 = findViewById(R.id.evbtn);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rentvchoose.this, gear.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rentvchoose.this, nongear.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rentvchoose.this, sedan.class);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rentvchoose.this, suv.class);
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rentvchoose.this, offroad.class);
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rentvchoose.this, ev.class);
                startActivity(intent);
            }
        });
    }
}