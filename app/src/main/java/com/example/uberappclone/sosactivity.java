package com.example.uberappclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class sosactivity extends AppCompatActivity {
    private static boolean splashActivityShown = false;

    // Notification channel ID (for Android 8.0 and above)
    private static final String CHANNEL_ID = "channel_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosactivity);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (splashActivityShown) {
            navigateToNextActivity();
            return;
        }

        setContentView(R.layout.activity_sosactivity);

        // Create notification channel (for Android 8.0 and above)
        createNotificationChannel();

        new Handler().postDelayed(() -> {
            // Set the flag to indicate that the activity has been shown
            splashActivityShown = true;

            // Start your main activity
            navigateToNextActivity();

            // Send SOS notification
            sendSOSNotification();
        }, 3000);
    }
    private void navigateToNextActivity() {
        Intent intent = new Intent(sosactivity.this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "SOS Channel";
            String description = "Channel for SOS notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendSOSNotification() {
        // Get the current location (you need to implement the location retrieval logic)
        Location currentLocation = getCurrentLocation();

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.sos)
                .setContentTitle("Emergency SOS")
                .setContentText("Help! I need assistance.")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // Add the current location to the notification content
        if (currentLocation != null) {
            builder.setContentText("Help me! I need assistance. My current location: " +
                    currentLocation.getLatitude() + ", " + currentLocation.getLongitude());
        }

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(1, builder.build());
    }

    // Implement your location retrieval logic here
    private Location getCurrentLocation() {
        Location dummyLocation = new Location("dummyProvider");
        dummyLocation.setLatitude(37.7749);
        dummyLocation.setLongitude(-122.4194);

        return dummyLocation;
    }
}