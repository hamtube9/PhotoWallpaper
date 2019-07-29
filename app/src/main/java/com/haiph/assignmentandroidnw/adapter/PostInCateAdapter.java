package com.haiph.assignmentandroidnw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haiph.assignmentandroidnw.R;
import com.haiph.assignmentandroidnw.model.Example;
import com.haiph.assignmentandroidnw.model.GetPost;
import com.haiph.assignmentandroidnw.model.PostMain;

import java.util.ArrayList;
import java.util.List;

public class PostInCateAdapter extends RecyclerView.Adapter<PostInCateAdapter.ViewHolder> {

    ArrayList<GetPost> postMains;

Context context;
        adapterListener adapterListener;

    public PostInCateAdapter(ArrayList<GetPost> postMains, Context context, PostInCateAdapter.adapterListener adapterListener) {
        this.postMains = postMains;
        this.context = context;
        this.adapterListener = adapterListener;
    }

    public interface adapterListener{
    public void onClick(int position);
}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final GetPost postMain= postMains.get(position);
        String url = postMain.getSourceUrl();
        Glide.with(context).load(url).into(holder.imgPost);
        holder.cardViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postMains.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewPost;
        ImageView imgPost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPost=itemView.findViewById(R.id.imgPostInCategory);
            cardViewPost=itemView.findViewById(R.id.cardViewPostInCate);

        }
    }

    public void updateData(List<GetPost> posts){
        this.postMains.addAll(posts);
        notifyDataSetChanged();
    }
}
