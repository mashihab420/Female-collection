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

 @GET("get_category.php")
 Call<List<ModelAll>> getcategory();


 @POST("signup.php")
 Call<ModelAll> create_account(@Body ModelAll modelAll);



 @POST("user_order_insert.php")
 Call<ModelAll> insert_order(@Body ModelAll modelAll);


 @POST("get_categoryitem.php")
 Call<List<ModelAll>> getcategoryitems(@Body ModelAll modelAll);


 @GET("user_login.php")
 Call<ModelAll> login( @Query("phone") String owner_phone,@Query("password") String password);

}
