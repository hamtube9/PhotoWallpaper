package com.haiph.assignmentandroidnw.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.haiph.assignmentandroidnw.R;
import com.haiph.assignmentandroidnw.Retrofit;
import com.haiph.assignmentandroidnw.adapter.CategoryAdapter;
import com.haiph.assignmentandroidnw.adapter.LatestAdapter;
import com.haiph.assignmentandroidnw.model.Categories;
import com.haiph.assignmentandroidnw.model.Example;
import com.haiph.assignmentandroidnw.model.PostMain;

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

        getPost(currentPage);

        final LinearLayoutManager linearLayout = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (linearLayout != null) {
                    lastItemVisible = linearLayout.findLastVisibleItemPosition();

                }
                totalItemCount = Integer.parseInt(String.valueOf(recyclerView.getAdapter().getItemCount()));
                if (!recyclerView.canScrollVertically(1) && newState == recyclerView.SCROLL_STATE_IDLE) {
                    currentPage++;
                    getPost(currentPage);
                }

            }
        });

        return view;
    }

    private void getPost(int currentPage) {

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