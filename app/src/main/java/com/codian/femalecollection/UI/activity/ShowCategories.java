package com.codian.femalecollection.UI.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Model.ModelAll;
import com.codian.femalecollection.UI.retrofit.ApiClint;
import com.codian.femalecollection.UI.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShowCategories extends AppCompatActivity {
    ApiInterface apiInterface;
    ArrayList<ModelAll> categorys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);

        Intent intent = getIntent();
        String category = intent.getStringExtra("cat");

        categorys = new ArrayList<>();

        Retrofit instance = ApiClint.instance();
        apiInterface = instance.create(ApiInterface.class);

        ModelAll modelAll = new ModelAll();
        modelAll.setCategories(category);


        //TODO not done

        apiInterface.getcategoryitems(modelAll).enqueue(new Callback<List<ModelAll>>() {
            @Override
            public void onResponse(Call<List<ModelAll>> call, Response<List<ModelAll>> response) {
                categorys.addAll(response.body());

                Toast.makeText(ShowCategories.this, ""+response.body().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ModelAll>> call, Throwable t) {

            }
        });
    }
}