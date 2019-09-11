package com.haiph.photowallpaper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haiph.photowallpaper.R;
import com.haiph.photowallpaper.model.Example;

import java.util.ArrayList;
import java.util.List;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.ViewHolder> {
    private ArrayList<Example> examplesList;
    private Context context;
    private AdapterListener adapterListener;

    public LatestAdapter(ArrayList<Example> examplesList, Context context, AdapterListener adapterListener) {
        this.examplesList = examplesList;
        this.context = context;
        this.adapterListener = adapterListener;
    }


    public interface AdapterListener {
        public void itemOnclick(int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_latest, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Example example = examplesList.get(position);


        if (position == 0 || position == examplesList.size() - 1) {
            holder.tvTitleLatest.setText(example.getTitle().getRendered() + "");
            holder.tvTitleLatest.setTextColor(Color.YELLOW);
        } else {
            holder.tvTitleLatest.setText(example.getTitle().getRendered() + "");
            holder.tvTitleLatest.setTextColor(Color.WHITE);
        }


        if (example.getId() == 952) {
            Glide.with(context).load("https://i.imgur.com/q4vrn8j.png").into(holder.imgLatest);
        } else {
            Glide.with(context).load(example.getEmbedded().getWpFeaturedmedia().get(0).getSourceUrl()).into(holder.imgLatest);
        }

        holder.cardViewLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.itemOnclick(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return examplesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewLatest;
        ImageView imgLatest;
        TextView tvTitleLatest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardViewLatest = itemView.findViewById(R.id.cardViewLatest);

            imgLatest = itemView.findViewById(R.id.imgLatest);

            tvTitleLatest = itemView.findViewById(R.id.tvTitleLatest);
        }
    }

    public void updateData(List<Example> examples) {
        this.examplesList.addAll(examples);
        notifyDataSetChanged();
    }
}
