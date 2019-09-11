package com.haiph.photowallpaper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.haiph.photowallpaper.R;
import com.haiph.photowallpaper.model.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<Categories> examplesList;
    Context context;
    AdapterListener adapterListener;
    public CategoryAdapter(ArrayList<Categories> examplesList, Context context, AdapterListener adapterListener) {
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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_example, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Categories example=examplesList.get(position);
        holder.tvTitle.setText(example.getName());
        String name = example.getName();
        if (name.equals("3D")) {
            holder.img.setImageResource(R.drawable.d3);
        } else if (name.equals("Asian")) {
            holder.img.setImageResource(R.drawable.asian);
        } else if (name.equals("Cambodia")) {
            holder.img.setImageResource(R.drawable.cambodia);
        } else if (name.equals("Car")) {
            holder.img.setImageResource(R.drawable.car);
        } else if (name.equals("China")) {
            holder.img.setImageResource(R.drawable.china);
        } else if(name.equals("Galaxy")){
            holder.img.setImageResource(R.drawable.galaxy);
        }else if(name.equals("Girl")){
            holder.img.setImageResource(R.drawable.girl);
        }else if(name.equals("Japan")){
            holder.img.setImageResource(R.drawable.japan);
        }else if(name.equals("Landscape")){
            holder.img.setImageResource(R.drawable.landscape);
        }else if(name.equals("Laos")){
            holder.img.setImageResource(R.drawable.laos);
        }else if(name.equals("MotoBike")){
            holder.img.setImageResource(R.drawable.ducati);
        }else if(name.equals("Planet")){
            holder.img.setImageResource(R.drawable.planet);
        }else if(name.equals("Sea")){
            holder.img.setImageResource(R.drawable.sea);
        }else if(name.equals("Truck")){
            holder.img.setImageResource(R.drawable.truck);
        }else if(name.equals("VietNam")){
            holder.img.setImageResource(R.drawable.vietnam);
        }


        holder.cardViewCate.setOnClickListener(new View.OnClickListener() {
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
        ImageView img;
         TextView tvTitle;
        private CardView cardViewCate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imgCategory);

            tvTitle = itemView.findViewById(R.id.tvTitleCategory);
            cardViewCate = itemView.findViewById(R.id.cardViewCate);

        }
    }

    public void UpdateData(List<Categories> exampleList) {

        this.examplesList.addAll(exampleList);
        notifyDataSetChanged();
    }

    public void ClearList() {
        if (this.examplesList.isEmpty()) {
            this.examplesList.clear();
        }
    }


}
