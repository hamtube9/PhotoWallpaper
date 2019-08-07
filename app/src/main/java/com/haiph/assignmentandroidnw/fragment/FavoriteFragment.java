package com.haiph.assignmentandroidnw.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.haiph.assignmentandroidnw.R;
import com.haiph.assignmentandroidnw.adapter.FavoriteAdapter;
import com.haiph.assignmentandroidnw.database.FavoriteDAO;
import com.haiph.assignmentandroidnw.model.Favorite;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    ProgressBar progressBar;

    RecyclerView rcView;
    FavoriteAdapter adapter;

    private ArrayList<Favorite> listFavorites= new ArrayList<>();
    FavoriteDAO favoriteDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View view = inflater.inflate(R.layout.fragment_favorite, container, false);
         rcView=view.findViewById(R.id.rcViewFavorite);
        progressBar = view.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new ChasingDots();
        progressBar.setIndeterminateDrawable(doubleBounce);

        favoriteDAO = new FavoriteDAO(getActivity());
        listFavorites= (ArrayList<Favorite>) favoriteDAO.getAllFavorite();
        rcView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        adapter = new FavoriteAdapter(listFavorites, getContext());

        rcView.setLayoutManager(gridLayoutManager);
        rcView.setAdapter(adapter);
        progressBar.setVisibility(View.INVISIBLE);

         return view;
    }
}
