package com.codian.femalecollection.UI.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {
    private static Retrofit retrofit = null;
    public static synchronized Retrofit instance(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()

                   .baseUrl("http://femalefashion88.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
