package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EReferencesPDFActivity extends AppCompatActivity implements PDFAdapter.OnItemClick {

    // recyclerview attr
    private RecyclerView rvPdf;
    private PDFAdapter pdfAdapter;
    private List<PDFModel> pdfList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_references_pdf);

        // initialize adapter with an empty list
        pdfList = new ArrayList<>();
        pdfAdapter = new PDFAdapter(pdfList, this);

        // setup recyclerview
        rvPdf = findViewById(R.id.rv_e_references_pdf);
        rvPdf.setLayoutManager(new GridLayoutManager(this, 2));
        rvPdf.setAdapter(pdfAdapter);

        // update list with data from firebase
        updatePdfList();
    }

    private void updatePdfList() {
        // get database reference
        DatabaseReference pdfRef = FirebaseDatabase.getInstance().getReference()
                .child("PDFImage");
        pdfRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    System.out.println(data.getKey()); // debug

                    // update the list
                    PDFModel pdf = new PDFModel();
                    pdf.setUrlImage(data.child("url").getValue().toString());
                    pdf.setKey(data.getKey());

                    // append to list
                    pdfList.add(pdf);
                }
                // notify the adapter
                pdfAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(EReferencesPDFActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void itemClicked(PDFModel pdf) {
        Intent goToPdfViewer = new Intent(EReferencesPDFActivity.this, PDFViewerActivity.class);
        goToPdfViewer.putExtra(PDFViewerActivity.EXTRA_PDF_KEY, pdf.getKey());
        startActivity(goToPdfViewer);
    }
}