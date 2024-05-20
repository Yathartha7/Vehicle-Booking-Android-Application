package com.example.uberappclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Button btn;
    private Marker sourceMarker, destinationMarker;
    private LatLng sourceLocation, destinationLocation;
    private String sourceName, destinationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        // Store the distance in your code
        //Toast.makeText(this, "Distance: " + distanceInKm + " km", Toast.LENGTH_SHORT).show();
        btn = findViewById(R.id.doneButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this,booking.class);
                startActivity(intent);
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
