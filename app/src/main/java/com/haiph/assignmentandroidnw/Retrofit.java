package com.haiph.assignmentandroidnw;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    public static ServicerRetrofit service;

    public static ServicerRetrofit getInstance(){

        if (service == null){
            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("http://asian.dotplays.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service  =  retrofit.create(ServicerRetrofit.class);
        }

        return service;
    }
  }
