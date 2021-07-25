package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnPosko3, btnEPdf, btnHandsanitizer, btnInfoApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPosko3 = findViewById(R.id.btn_posko3);
        btnEPdf = findViewById(R.id.btn_e_pdf);
        btnHandsanitizer = findViewById(R.id.btn_handsanitizer);
        btnInfoApp = findViewById(R.id.btn_info_app);

        btnPosko3.setOnClickListener(this);
        btnEPdf.setOnClickListener(this);
        btnHandsanitizer.setOnClickListener(this);
        btnInfoApp.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_posko3:
                Intent o = new Intent(MainActivity.this, Posko3Activity.class);
                startActivity(o);
                break;
            case R.id.btn_e_pdf:
                Intent q = new Intent(MainActivity.this, EReferencesPDFActivity.class);
                startActivity(q);
                break;
            case R.id.btn_handsanitizer:
                Intent p = new Intent(MainActivity.this, HandsanitizerActivity.class);
                startActivity(p);
                break;
            case R.id.btn_info_app:
                Intent i = new Intent(MainActivity.this, InfoAppActivity.class);
                startActivity(i);
                break;
        }
    }

//    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    private DatabaseReference myRef = database.getReference();
//        LoadImage("ImageOne", "url", btnPosko3);
//        LoadImage("ImageTwo", "url", btnEPdf);
//        LoadImage("ImageThree", "url", btnHandsanitizer);
//        LoadImage("ImageFour", "url", btnInfoApp);
//    private void LoadImage(String s, String s1, ImageView img) {
//        DatabaseReference getImage = myRef.child("MainImage").child(s).child(s1);
//        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
//                String url = snapshot.getValue(String.class);
//                Glide.with(MainActivity.this).load(url).into(img);
//            }
//
//            @Override
//            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
//
//            }
//        });
//    }
}