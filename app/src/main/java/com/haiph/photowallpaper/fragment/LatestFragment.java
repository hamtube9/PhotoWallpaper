package com.haiph.photowallpaper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.haiph.photowallpaper.R;
import com.haiph.photowallpaper.Retrofit;
import com.haiph.photowallpaper.adapter.LatestAdapter;
import com.haiph.photowallpaper.model.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestFragment extends Fragment {
    private RecyclerView recyclerView;
    private LatestAdapter adapter;
    Context context;
    ProgressBar progressBar;

    int currentPage, lastItemVisible, totalItemCount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_latest, container, false);
        recyclerView = view.findViewById(R.id.recylerViewLatest);

         progressBar = view.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new FoldingCube();
        progressBar.setIndeterminateDrawable(doubleBounce);





        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        final ArrayList<Example> examplesList = new ArrayList<>();
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        adapter = new LatestAdapter(examplesList, getActivity(), new LatestAdapter.AdapterListener() {
            @Override
            public void itemOnclick(int position) {
                Example example= examplesList.get(position);
                Bundle bundle=new Bundle();;
                bundle.putString("id", example.getId().toString());

                PostInCateFragment fragment = new PostInCateFragment();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frameLayout, fragment);

                fragmentTransaction.commit();

            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getPost();


        return view;
    }

    private void getPost() {

        Retrofit.getInstance().getLastest("").enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if(response.isSuccessful()){

                    adapter.updateData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e("", t.getMessage());
            }
        });
    }

}