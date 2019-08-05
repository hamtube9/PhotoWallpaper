package com.haiph.assignmentandroidnw;

import com.haiph.assignmentandroidnw.model.Categories;
import com.haiph.assignmentandroidnw.model.Example;
import com.haiph.assignmentandroidnw.model.GetPost;
import com.haiph.assignmentandroidnw.model.PostMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicerRetrofit {


    @GET("wp-json/wp/v2/media")
    Call<List<GetPost>> getMedia( @Query("parent") String parent);

    @GET("wp-json/wp/v2/categories")
    Call<List<Categories>> getCate(@Query("page") String page, @Query("per_page") String per_page);


    @GET("wp-json/wp/v2/posts")
    Call<List<Example>> getLastest(@Query("_embed") String embed);

}
