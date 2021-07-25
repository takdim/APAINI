package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PDFViewerActivity extends AppCompatActivity implements View.OnClickListener {

    // extras
    public static final String EXTRA_PDF_KEY = "extra_pdf_key";

    // database
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    // widgets
    private PDFView pdfView;
    private ImageView btnBack, btnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        btnBack = findViewById(R.id.btn_back_pdf_viewer);
        btnHome = findViewById(R.id.btn_home_pdf_viewer);
        pdfView = findViewById(R.id.pdfView_pdf_viewer);

        String pdfKey = getIntent().getStringExtra(EXTRA_PDF_KEY);
        getPdf(pdfKey);

        // if button clicked
        btnBack.setOnClickListener(this);
        btnHome.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back_pdf_viewer:
                finish();
                break;
            case R.id.btn_home_pdf_viewer:
                Intent goToHome = new Intent(PDFViewerActivity.this, MainActivity.class);
                goToHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goToHome);
                break;
        }
    }

    private void getPdf(String pdfKey) {
        DatabaseReference pdfRef = database.getReference("PDF").child(pdfKey);
        pdfRef.addValueEventListener(new ValueEventListener() {
            class RetrievePdfStream extends AsyncTask<String, Void, InputStream> {
                @Override
                protected InputStream doInBackground(String... strings) {
                    InputStream inputStream = null;
                    try {
                        URL url = new URL(strings[0]);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        if (urlConnection.getResponseCode() == 200) {
                            inputStream = new BufferedInputStream(urlConnection.getInputStream());
                        }
                    } catch (IOException e) {
                        return null;
                    }
                    return inputStream;
                }

                @Override
                protected void onPostExecute(InputStream inputStream) {
                    pdfView.fromStream(inputStream).load();
                }
            }

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String urlPdf = snapshot.child("url").getValue().toString();
                new RetrievePdfStream().execute(urlPdf);

                Toast.makeText(PDFViewerActivity.this, "Retrieving PDF...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(PDFViewerActivity.this, "Retrieving PDF failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}