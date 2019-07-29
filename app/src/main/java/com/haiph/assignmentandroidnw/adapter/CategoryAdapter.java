package com.haiph.assignmentandroidnw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.haiph.assignmentandroidnw.R;
import com.haiph.assignmentandroidnw.model.Categories;

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
         TextView tvTitle;
        private CardView cardViewCate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

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
