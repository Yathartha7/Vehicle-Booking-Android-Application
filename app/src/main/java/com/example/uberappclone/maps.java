package com.example.uberappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class maps extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    Button btn;
    private Marker sourceMarker, destinationMarker;
    private LatLng sourceLocation, destinationLocation;
    private String sourceName, destinationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btn = findViewById(R.id.doneButton);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.map, mapFragment)
                    .commit();
        }
        mapFragment.getMapAsync(this);

        // Set default source and destination locations
        sourceLocation = new LatLng(30.2690555, 77.9916389); // Replace with your default source location
        destinationLocation = new LatLng(30.3108297, 78.0344215); // Replace with your default destination location

        // Set default names for source and destination
        sourceName = "Default Source";
        destinationName = "Default Destination";

        // Calculate and display the distance
        float[] results = new float[1];
        android.location.Location.distanceBetween(
                sourceLocation.latitude, sourceLocation.longitude,
                destinationLocation.latitude, destinationLocation.longitude,
                results);
        float distanceInMeters = results[0];
        int distanceInKm = (int)(distanceInMeters / 1000);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(maps.this, booking.class);
                startActivity(in);
                finish();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add markers for source and destination
        sourceMarker = mMap.addMarker(new MarkerOptions().position(sourceLocation).title(sourceName));
        destinationMarker = mMap.addMarker(new MarkerOptions().position(destinationLocation).title(destinationName));

        // Move camera to the center of the markers
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sourceLocation, 10));
    }
}