package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PDFAdapter extends RecyclerView.Adapter<PDFAdapter.ViewHolder> {
    private final List<PDFModel> pdfList;
    private final OnItemClick onItemClick;

    public PDFAdapter(List<PDFModel> pdfList, OnItemClick onItemClick) {
        this.pdfList = pdfList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_e_references_pdf, parent, false);

        return new ViewHolder(view, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PDFAdapter.ViewHolder holder, int position) {
        holder.onBind(pdfList.get(position));
    }

    @Override
    public int getItemCount() {
        return pdfList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivCover;
        public ViewHolder(@NonNull @NotNull View itemView, OnItemClick onItemClick) {
            super(itemView);
            itemView.setOnClickListener(v -> onItemClick.itemClicked(pdfList.get(getAdapterPosition())));

            ivCover = itemView.findViewById(R.id.iv_cover_item_e_references_pdf);
        }

        public void onBind(PDFModel pdf) {
            Glide.with(itemView.getContext())
                    .load(pdf.getUrlImage())
                    .into(ivCover);
        }
    }

    public interface OnItemClick {
        void itemClicked(PDFModel pdf);
    }
}
