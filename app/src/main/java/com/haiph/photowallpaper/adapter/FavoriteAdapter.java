package com.haiph.photowallpaper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haiph.photowallpaper.R;
import com.haiph.photowallpaper.model.Favorite;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    ArrayList<Favorite> list;

    public FavoriteAdapter(ArrayList<Favorite> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

Glide.with(context).load(list.get(position).getUrl()).into(holder.imgFavorite);



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
