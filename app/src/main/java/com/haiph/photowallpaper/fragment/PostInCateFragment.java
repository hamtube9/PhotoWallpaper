package com.haiph.photowallpaper.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haiph.photowallpaper.ImageDetailsActivity;
import com.haiph.photowallpaper.R;
import com.haiph.photowallpaper.Retrofit;
import com.haiph.photowallpaper.adapter.PostInCateAdapter;
import com.haiph.photowallpaper.model.GetPost;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostInCateFragment extends Fragment {
    private int position;
    private RecyclerView rcViewPost;
    Context context;
    int currentPage, lastItemVisible, totalItemCount;

    private PostInCateAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_post_in_category, container, false);
        rcViewPost=view.findViewById(R.id.rcViewPost);


        rcViewPost.setLayoutManager(new GridLayoutManager(getActivity(),2));
        final ArrayList<GetPost> examplesList = new ArrayList<>();


        adapter = new PostInCateAdapter(examplesList, getActivity(), new PostInCateAdapter.adapterListener() {
            @Override
            public void onClick(int position) {
                final GetPost getPost=examplesList.get(position);


                Intent intent=new Intent(getActivity(),ImageDetailsActivity.class);
                intent.putExtra("img",getPost.getSourceUrl());
                startActivity(intent);

            }


        });

        rcViewPost.setAdapter(adapter);
        rcViewPost.setItemAnimator(new DefaultItemAnimator());




        getPost();


        return view;
    }

    private void getPost() {

        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        Retrofit.getInstance().getMedia(id,1,100).enqueue(new Callback<List<GetPost>>() {
            @Override
            public void onResponse(Call<List<GetPost>> call, Response<List<GetPost>> response) {

                if (response.code()==200 && response.body()!= null) {
                    adapter.updateData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<GetPost>> call, Throwable t) {

                Log.e("err", t.getMessage());
            }
        });
    }


}
