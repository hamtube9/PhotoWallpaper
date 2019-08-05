package com.haiph.assignmentandroidnw.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.haiph.assignmentandroidnw.R;

public class FavoriteFragment extends Fragment {
    ProgressBar progressBar;
    private RecyclerView rcView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View view = inflater.inflate(R.layout.fragment_favorite, container, false);
         rcView=view.findViewById(R.id.rcViewFavorite);
        progressBar = view.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new ChasingDots();
        progressBar.setIndeterminateDrawable(doubleBounce);

         return view;
    }
}
