package com.codian.femalecollection.UI.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Adapter.OrderAdapter;
import com.codian.femalecollection.UI.Model.ModelAll;
import com.codian.femalecollection.UI.MysharedPreferance;
import com.codian.femalecollection.UI.retrofit.ApiClint;
import com.codian.femalecollection.UI.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrdersActivity extends AppCompatActivity {
    List<ModelAll> arrayList;
    Context context;
    RecyclerView recyclerView;
    OrderAdapter adapter;
    ApiInterface apiInterface;
    MysharedPreferance sharedPreferance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        arrayList = new ArrayList<>();
        Retrofit instance = ApiClint.instance();
        apiInterface = instance.create(ApiInterface.class);
        recyclerView = findViewById(R.id.recyclerView);
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter = new OrderAdapter(arrayList,getApplicationContext());
       // recyclerView.setAdapter(adapter);

        ModelAll modelAll = new ModelAll();
        modelAll.setUser_phone(sharedPreferance.getPhone());
        apiInterface.getorders(modelAll).enqueue(new Callback<List<ModelAll>>() {
            @Override
            public void onResponse(Call<List<ModelAll>> call, Response<List<ModelAll>> response) {
                arrayList.clear();
                arrayList.addAll(response.body());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelAll>> call, Throwable t) {

            }
        });
    }

    public void backbtn(View view) {
        onBackPressed();
    }
}