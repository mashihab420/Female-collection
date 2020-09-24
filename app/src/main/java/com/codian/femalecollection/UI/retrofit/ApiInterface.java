package com.codian.femalecollection.UI.retrofit;

import com.codian.femalecollection.UI.Model.ModelAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


 @GET("get_products.php")
 Call<List<ModelAll>> getProducts();


}
