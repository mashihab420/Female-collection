package com.codian.femalecollection.UI.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Adapter.AllProductAdapter;
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
    TextView title;
    ArrayList<ModelAll> categorys;
    AllProductAdapter allProductAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);

        title = findViewById(R.id.textView4);
        recyclerView = findViewById(R.id.recyclerView);



        Intent intent = getIntent();
        String category = intent.getStringExtra("cat");
        title.setText(category);

        categorys = new ArrayList<>();

        allProductAdapter = new AllProductAdapter(ShowCategories.this,categorys);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

        Retrofit instance = ApiClint.instance();
        apiInterface = instance.create(ApiInterface.class);

        ModelAll modelAll = new ModelAll();
        modelAll.setCategories(category);


        //TODO not done

        apiInterface.getcategoryitems(modelAll).enqueue(new Callback<List<ModelAll>>() {
            @Override
            public void onResponse(Call<List<ModelAll>> call, Response<List<ModelAll>> response) {
                categorys.addAll(response.body());
                allProductAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(allProductAdapter);
               // Toast.makeText(ShowCategories.this, ""+response.body().size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ModelAll>> call, Throwable t) {

                Toast.makeText(ShowCategories.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}