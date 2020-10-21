package com.codian.femalecollection.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
RecyclerView recyclerView,recyclerViewsearch;
ApiInterface apiInterface;



EditText editTextsearch;

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
        recyclerViewsearch = view.findViewById(R.id.recyclerViewsearch);

        editTextsearch = view.findViewById(R.id.editText);

        products = new ArrayList<>();
        allProductAdapter = new AllProductAdapter(getContext(),products);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerViewsearch.setLayoutManager(new GridLayoutManager(getContext(),1));


        allcategory = new ArrayList<>();
        allCategoryAdapter = new AllCategoryAdapter(getContext(),allcategory);
       // binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(),1));
        binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));


       Retrofit instance = ApiClint.instance();
        apiInterface = instance.create(ApiInterface.class);


      apiInterface.getProducts().enqueue(new Callback<List<ModelAll>>() {
            @Override
            public void onResponse(Call<List<ModelAll>> call, Response<List<ModelAll>> response) {

               // products.clear();
                products.addAll(response.body());
                allProductAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(allProductAdapter);
                recyclerViewsearch.setAdapter(allProductAdapter);



            }

            @Override
            public void onFailure(Call<List<ModelAll>> call, Throwable t) {

                Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

      apiInterface.getcategory().enqueue(new Callback<List<ModelAll>>() {
          @Override
          public void onResponse(Call<List<ModelAll>> call, Response<List<ModelAll>> response) {
              allcategory.addAll(response.body());
              allCategoryAdapter.notifyDataSetChanged();
              binding.recyclerViewCategory.setAdapter(allCategoryAdapter);
          }

          @Override
          public void onFailure(Call<List<ModelAll>> call, Throwable t) {

          }
      });






      editTextsearch.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View view, MotionEvent motionEvent) {

              binding.recyclerViewsearch.setVisibility(View.VISIBLE);
              binding.imageView4.setVisibility(View.VISIBLE);
              binding.recyclerViewCategory.setVisibility(View.GONE);
              binding.linearLayout2.setVisibility(View.GONE);
              binding.categoriesid.setVisibility(View.GONE);
              binding.linearLayout3.setVisibility(View.GONE);
              binding.productsid.setVisibility(View.GONE);
              binding.recyclerViewid.setVisibility(View.GONE);
              return false;
          }
      });


      binding.imageView4.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              binding.recyclerViewsearch.setVisibility(View.GONE);
              binding.imageView4.setVisibility(View.GONE);
              binding.recyclerViewCategory.setVisibility(View.VISIBLE);
              binding.linearLayout2.setVisibility(View.VISIBLE);
              binding.categoriesid.setVisibility(View.VISIBLE);
              binding.linearLayout3.setVisibility(View.VISIBLE);
              binding.productsid.setVisibility(View.VISIBLE);
              binding.recyclerViewid.setVisibility(View.VISIBLE);

              editTextsearch.setText("");

              InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
              imm.hideSoftInputFromWindow(editTextsearch.getWindowToken(), 0);
          }
      });


        editTextsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                product_filter(s.toString());


            }
        });


        return view;
    }

    public void product_filter(String productname){


        ArrayList<ModelAll> filter_product=new ArrayList<>();
        for (ModelAll item: products){

            if (item.getPName().toLowerCase().contains(productname.toLowerCase()))
            {
                filter_product.add(item);
            }



        }

        allProductAdapter.search_filter_list(filter_product);
    }


}