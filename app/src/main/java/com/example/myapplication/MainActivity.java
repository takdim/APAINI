package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    private ImageView img1, img2, img3, img4;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);

        LoadImage("ImageOne", "url", img1);
        LoadImage("ImageTwo", "url", img2);
        LoadImage("ImageThree", "url", img3);
        LoadImage("ImageFour", "url", img4);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);

    }

    private void LoadImage(String s, String s1, ImageView img) {
        DatabaseReference getImage = myRef.child("MainImage").child(s).child(s1);
        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                String url = snapshot.getValue(String.class);
                Glide.with(MainActivity.this).load(url).into(img);
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img1:
                Intent o = new Intent(MainActivity.this, Posko3Activity.class);
                startActivity(o);
                break;
            case R.id.img2:
                Intent q = new Intent(MainActivity.this, EReferencesPDFActivity.class);
                startActivity(q);
                break;
            case R.id.img3:
                Intent p = new Intent(MainActivity.this, HandsanitizerActivity.class);
                startActivity(p);
                break;
            case R.id.img4:
                Intent i = new Intent(MainActivity.this, InfoAppActivity.class);
                startActivity(i);
                break;
        }
    }
}