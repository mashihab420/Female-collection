package com.codian.femalecollection.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codian.femalecollection.R;
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
List<ModelAll> products;
AllProductAdapter allProductAdapter;
RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        recyclerView = view.findViewById(R.id.recyclerViewid);
        //TODO

        products = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        allProductAdapter = new AllProductAdapter(getActivity(),products);
        recyclerView.setAdapter(allProductAdapter);


        Retrofit instance = ApiClint.instance();
        ApiInterface apiInterface = instance.create(ApiInterface.class);


        apiInterface.getProducts().enqueue(new Callback<List<ModelAll>>() {
            @Override
            public void onResponse(Call<List<ModelAll>> call, Response<List<ModelAll>> response) {

               // products.clear();
                products.addAll(response.body());
                allProductAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<ModelAll>> call, Throwable t) {

            }
        });


        return view;
    }
}