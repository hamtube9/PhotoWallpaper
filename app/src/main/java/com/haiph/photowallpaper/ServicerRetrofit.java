package com.haiph.photowallpaper;

import com.haiph.photowallpaper.model.Categories;
import com.haiph.photowallpaper.model.Example;
import com.haiph.photowallpaper.model.GetPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicerRetrofit {


    @GET("wp-json/wp/v2/media")
    Call<List<GetPost>> getMedia( @Query("parent") String parent,@Query("page") int page, @Query("per_page") int perpage);

    @GET("wp-json/wp/v2/categories")
    Call<List<Categories>> getCate(@Query("page") String page, @Query("per_page") String per_page);


    @GET("wp-json/wp/v2/posts")
    Call<List<Example>> getLastest(@Query("_embed") String embed);



}
