package com.example.uberappclone;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class rentverify extends AppCompatActivity {

    private static final int FILE_PICKER_REQUEST_CODE_1 = 1;
    private static final int FILE_PICKER_REQUEST_CODE_2 = 2;
    ImageView dl,pp;
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentverify);
        btn = findViewById(R.id.rentpro);
        Button uploadButton1 = findViewById(R.id.upload1);
        Button uploadButton2 = findViewById(R.id.upload2);
        dl = findViewById(R.id.warningImage1);
        pp = findViewById(R.id.warningImage2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rentverify.this, rentvchoose.class);
                startActivity(intent);
            }
        });
        uploadButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker(FILE_PICKER_REQUEST_CODE_1);
            }
        });

        uploadButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker(FILE_PICKER_REQUEST_CODE_2);
            }
        });
    }

    private void openFilePicker(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            // Dummy logic: Simulate the file selection
            // You can update your UI accordingly
            int warningImageResource = R.drawable.detailok;

            if (requestCode == FILE_PICKER_REQUEST_CODE_1) {
                // Update warning image for the first file
                dl.setImageResource(R.drawable.detailok);
                //findViewById(R.id.warningImage1).setBackgroundResource(warningImageResource);
            } else if (requestCode == FILE_PICKER_REQUEST_CODE_2) {
                // Update warning image for the second file
                //findViewById(R.id.warningImage2).setBackgroundResource(warningImageResource);
                pp.setImageResource(R.drawable.detailok);
            }
        }
    }
}
