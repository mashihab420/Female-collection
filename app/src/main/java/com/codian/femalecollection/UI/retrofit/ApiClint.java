package com.codian.femalecollection.UI.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {


    private static Retrofit retrofit = null;


    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    private ApiClint() {}



    public static synchronized Retrofit instance(){
        if (retrofit==null){

            retrofit = new Retrofit.Builder()

                   .baseUrl("http://femalefashion88.000webhostapp.com/femalefashion/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
