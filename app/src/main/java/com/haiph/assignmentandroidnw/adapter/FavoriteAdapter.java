package com.haiph.assignmentandroidnw.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haiph.assignmentandroidnw.R;
import com.haiph.assignmentandroidnw.fragment.FavoriteFragment;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    List list;
    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        list = new ArrayList();
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle b = fragment.getArguments();
        String url = b.getString("img");
        Glide.with(context).load(url).into(holder.imgFavorite);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFavorite;

        CardView cardViewFavorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFavorite=itemView.findViewById(R.id.imgFavorite);
            cardViewFavorite=itemView.findViewById(R.id.cardViewFavorite);
        }
    }
}