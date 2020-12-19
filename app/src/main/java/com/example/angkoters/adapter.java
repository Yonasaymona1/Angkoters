package com.example.angkoters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angkoters.model.RuteAngkotItem;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    private ArrayList<RuteAngkotItem> ruteAngkotItems;
    private Context context;

    public adapter(ArrayList<RuteAngkotItem> ruteAngkotItems, Context context) {
        this.ruteAngkotItems = ruteAngkotItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.daftar, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.trayek.setText(ruteAngkotItems.get(position).getTrayek());
        holder.no.setText(ruteAngkotItems.get(position).getNomorAngkot());
        holder.more.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("gambar", ruteAngkotItems.get(position).getGambarUrl());
            intent.putExtra("rute", ruteAngkotItems.get(position).getLintasan());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ruteAngkotItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView trayek, no;
        private LinearLayout more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            trayek = itemView.findViewById(R.id.trayek);
            no = itemView.findViewById(R.id.no);
            more = itemView.findViewById(R.id.more);
        }
    }
}