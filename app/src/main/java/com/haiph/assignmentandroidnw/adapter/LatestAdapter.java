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
import com.haiph.assignmentandroidnw.model.Categories;
import com.haiph.assignmentandroidnw.model.Example;
import com.haiph.assignmentandroidnw.model.PostMain;

import java.util.ArrayList;
import java.util.List;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.ViewHolder> {
    ArrayList<Example> examplesList;
    Context context;
   AdapterListener adapterListener;

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
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_latest, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Example example=examplesList.get(position);

        holder.tvTitleLatest.setText(example.getTitle().getRendered());
        String url = example.getEmbedded().getWpFeaturedmedia().get(position).getSourceUrl();
        Glide
                .with(context)
                .load(url)
                .into(holder.imgLatest);
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

            cardViewLatest=itemView.findViewById(R.id.cardViewLatest);

          imgLatest=itemView.findViewById(R.id.imgLatest);

            tvTitleLatest=itemView.findViewById(R.id.tvTitleLatest);
        }
    }

    public void updateData(List<Example> examples){
        this.examplesList.addAll(examples);
        notifyDataSetChanged();
    }
}
