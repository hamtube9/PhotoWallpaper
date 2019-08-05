package com.haiph.assignmentandroidnw.fragment;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.haiph.assignmentandroidnw.R;
import com.haiph.assignmentandroidnw.Retrofit;
import com.haiph.assignmentandroidnw.adapter.CategoryAdapter;
import com.haiph.assignmentandroidnw.model.Categories;
import com.haiph.assignmentandroidnw.model.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatergoryFragment extends Fragment {
    Context context;
    int currentPage, lastItemVisible, totalItemCount;
    private RecyclerView rcView;
    private CategoryAdapter categoryAdapter;
    ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        rcView = view.findViewById(R.id.rcCategory);
        progressBar = view.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new WanderingCubes();
        progressBar.setIndeterminateDrawable(doubleBounce);

        rcView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ArrayList<Categories> examplesList = new ArrayList<>();
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        categoryAdapter = new CategoryAdapter(examplesList, context, new CategoryAdapter.AdapterListener() {
            @Override
            public void itemOnclick(int position) {
                LatestFragment fragment = new LatestFragment();
                fragmentTransaction.replace(R.id.frameLayout, fragment);

                fragmentTransaction.commit();

            }
        });

        rcView.setAdapter(categoryAdapter);
        rcView.setItemAnimator(new DefaultItemAnimator());

        getData(currentPage);

//        final LinearLayoutManager linearLayout = (LinearLayoutManager) rcView.getLayoutManager();
//        rcView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (linearLayout != null) {
//                    lastItemVisible = linearLayout.findLastVisibleItemPosition();
//
//                }
//                totalItemCount = Integer.parseInt(String.valueOf(rcView.getAdapter().getItemCount()));
//                if (!rcView.canScrollVertically(1) && newState == recyclerView.SCROLL_STATE_IDLE) {
//                    currentPage++;
//                    getData(currentPage);
//                }
//
//            }
//        });

        return view;


    }

    public void getData(int currentPage) {

        Retrofit.getInstance().getCate("1","18").enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.code() == 200 && response.body() != null) {

                    categoryAdapter.UpdateData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e("fail", t.getMessage());
            }
        });


    }
}
