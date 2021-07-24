package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HandsanitizerActivity extends AppCompatActivity {

    private Button btnPdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handsanitizer);

        btnPdf = findViewById(R.id.btnPdf);

        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HandsanitizerActivity.this, PDFHandsanitizerActivity.class);
                startActivity(a);
            }
        });
    }
}