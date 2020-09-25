package com.codian.femalecollection.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Adapter.AllCategoryAdapter;
import com.codian.femalecollection.UI.Adapter.AllProductAdapter;
import com.codian.femalecollection.UI.Model.ModelAll;
import com.codian.femalecollection.UI.retrofit.ApiClint;
import com.codian.femalecollection.UI.retrofit.ApiInterface;
import com.codian.femalecollection.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;
ArrayList<ModelAll> products;
ArrayList<ModelAll> allcategory;
AllProductAdapter allProductAdapter;
AllCategoryAdapter allCategoryAdapter;
RecyclerView recyclerView,recyclerViewcat;
ApiInterface apiInterface;





    public HomeFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container!=null)
        {

            container.removeAllViews();

        }
        // Inflate the layout for this fragment
       binding = FragmentHomeBinding.inflate(inflater,container,false);
    View view = binding.getRoot();
        //View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewid);


        products = new ArrayList<>();
        allProductAdapter = new AllProductAdapter(getContext(),products);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));


        allcategory = new ArrayList<>();
        allCategoryAdapter = new AllCategoryAdapter(getContext(),allcategory);
        binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(),1));


       Retrofit instance = ApiClint.instance();
        apiInterface = instance.create(ApiInterface.class);


      apiInterface.getProducts().enqueue(new Callback<List<ModelAll>>() {
            @Override
            public void onResponse(Call<List<ModelAll>> call, Response<List<ModelAll>> response) {

               // products.clear();
                products.addAll(response.body());
                allProductAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(allProductAdapter);

                allcategory.addAll(response.body());
                allCategoryAdapter.notifyDataSetChanged();
                binding.recyclerViewCategory.setAdapter(allCategoryAdapter);

            }

            @Override
            public void onFailure(Call<List<ModelAll>> call, Throwable t) {

                Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });





        return view;
    }
}