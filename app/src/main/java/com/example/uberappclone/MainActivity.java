package com.example.uberappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ImageButton bookRideButton;
    private ImageButton rentRideButton;
    private ImageButton sosbtn;
    Button btn;
    TextView txt;
    FirebaseUser user;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = findViewById(R.id.logout);
        txt = findViewById(R.id.displayName);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if(user == null){
            Intent inte = new Intent(getApplicationContext(),activity_login.class);
            startActivity(inte);
            finish();
        }
        else{
            txt.setText(user.getEmail());
        }

        bookRideButton = findViewById(R.id.bookRide);
        rentRideButton = findViewById(R.id.rentRide);
        sosbtn = findViewById(R.id.sosbtn);

        sosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,sossplash.class);
                startActivity(intent);
            }
        });

        bookRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open BookingActivity when bookRideButton is clicked
                Intent intent = new Intent(MainActivity.this, booking.class);
                startActivity(intent);
            }
        });
        rentRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,rentverify.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),activity_login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
