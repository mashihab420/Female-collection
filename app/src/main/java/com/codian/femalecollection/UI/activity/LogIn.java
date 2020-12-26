package com.codian.femalecollection.UI.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Model.ModelAll;
import com.codian.femalecollection.UI.Model.ModelCartRoom;
import com.codian.femalecollection.UI.MysharedPreferance;
import com.codian.femalecollection.UI.Place_order;
import com.codian.femalecollection.UI.Repository.CartRepository;
import com.codian.femalecollection.UI.retrofit.ApiClint;
import com.codian.femalecollection.UI.retrofit.ApiInterface;
import com.codian.femalecollection.databinding.ActivityCreateAccountBinding;
import com.codian.femalecollection.databinding.ActivityLogInBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.codian.femalecollection.UI.activity.CartActivity.getOrderNumberGenerator;

public class LogIn extends AppCompatActivity {

    private ActivityLogInBinding binding;
    ApiInterface apiInterface;
    MysharedPreferance sharedPreferance;

    List<ModelCartRoom> arrayList;
    Context context;
    CartRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        Retrofit instance = ApiClint.instance();
        apiInterface = instance.create(ApiInterface.class);
        arrayList = new ArrayList<>();
        repository = new CartRepository(getApplicationContext());
        String activity = getIntent().getStringExtra("activity");

        if (activity.equals("home")){
            binding.textView39.setVisibility(View.GONE);
            binding.textView41.setVisibility(View.GONE);
        }else {
            binding.textView39.setVisibility(View.VISIBLE);
            binding.textView41.setVisibility(View.VISIBLE);
        }


        binding.btLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (activity.equals("home")){


                    Toast.makeText(LogIn.this, "Go To Home Page", Toast.LENGTH_SHORT).show();
                    binding.progressBar2.setVisibility(View.VISIBLE);
                    String getPhone=binding.etLoginPhone.getText().toString();
                    String pass=binding.editTextTextPassword.getText().toString();


                    apiInterface.login(getPhone,pass).enqueue(new Callback<ModelAll>() {
                        @Override
                        public void onResponse(Call<ModelAll> call, Response<ModelAll> response) {
                            if(response.body().getResponse().equals("ok")){
                                binding.progressBar2.setVisibility(View.GONE);
                                sharedPreferance.setData(response.body().getName());
                                sharedPreferance.setEmail(response.body().getEmail());
                                sharedPreferance.setPhone(response.body().getPhone());
                                sharedPreferance.setAddress(response.body().getAddress());
                                Intent intent = new Intent(LogIn.this, MainActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(LogIn.this, "Incorrect Phone number or Password!", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<ModelAll> call, Throwable t) {
                            Toast.makeText(LogIn.this, "Check the net connection", Toast.LENGTH_SHORT).show();
                        }
                    });


                }else{

                    binding.btLogIn.setVisibility(View.GONE);
                    binding.progressBar2.setVisibility(View.VISIBLE);
                    String getPhone=binding.etLoginPhone.getText().toString();
                    String pass=binding.editTextTextPassword.getText().toString();


                    apiInterface.login(getPhone,pass).enqueue(new Callback<ModelAll>() {
                        @Override
                        public void onResponse(Call<ModelAll> call, Response<ModelAll> response) {

                            if (response.body().getResponse().equals("ok")){
                                binding.progressBar2.setVisibility(View.GONE);

                                sharedPreferance.setData(response.body().getName());
                                sharedPreferance.setEmail(response.body().getEmail());
                                sharedPreferance.setPhone(response.body().getPhone());
                                sharedPreferance.setAddress(response.body().getAddress());


                                repository.getAllData().observe(LogIn.this, new Observer<List<ModelCartRoom>>() {
                                    @Override
                                    public void onChanged(List<ModelCartRoom> modelCartRooms) {
                                        arrayList.addAll(modelCartRooms);

                                        ModelAll modelAll = new ModelAll();
                                        modelAll.setPhone(response.body().getPhone());
                                        modelAll.setAddress(response.body().getAddress());

                                        orderitem(modelAll);
                                    }
                                });


                             /*   Intent intent = new Intent(LogIn.this, Place_order.class);
                                startActivity(intent);*/
                            }else {
                                Toast.makeText(LogIn.this, "Incorrect Phone number or Password!", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<ModelAll> call, Throwable t) {
                            Toast.makeText(LogIn.this, "Check the net connection", Toast.LENGTH_SHORT).show();
                        }
                    });



                }




            }
        });

    }

    public  void orderitem(ModelAll modelAll){
        int i;

        String getInvoiveID=getOrderNumberGenerator();

        Date d = new Date();
        CharSequence datee  = DateFormat.format("d MMMM, yyyy ", d.getTime());


        Intent intent1 = getIntent();
        String subtotall = intent1.getStringExtra("subtotal");
        String totall = intent1.getStringExtra("total");
       for ( i=0;i<arrayList.size();i++)
        {


            modelAll=new ModelAll();
            modelAll.setProduct_name(arrayList.get(i).getP_name());
            modelAll.setProduct_price(arrayList.get(i).getP_price());
            modelAll.setQuantity(arrayList.get(i).getQuantity());
            modelAll.setInvoice_number(getInvoiveID);
            modelAll.setUser_phone(sharedPreferance.getPhone());
            modelAll.setSubtotal(subtotall);
            modelAll.setTotal(totall);
            modelAll.setSize(arrayList.get(i).getSize());
            modelAll.setUrl(arrayList.get(i).getUrl());
            modelAll.setDate(datee.toString());




            sendData(modelAll);





        }




        Intent intent=new Intent(LogIn.this, Place_order.class);
        String orderitem = String.valueOf(arrayList.size());
        intent.putExtra("totalitem",orderitem);
        intent.putExtra("subtotal",subtotall);
        intent.putExtra("total",totall);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void sendData(ModelAll modelAll)
    {


        Retrofit instance = ApiClint.instance();
        apiInterface = instance.create(ApiInterface.class);

        apiInterface.insert_order(modelAll).enqueue(new Callback<ModelAll>() {
            @Override
            public void onResponse(Call<ModelAll> call, Response<ModelAll> response) {


            }

            @Override
            public void onFailure(Call<ModelAll> call, Throwable t) {



            }
        });

    }



    public static String getOrderNumberGenerator() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public void gotoRegPage(View view) {
        Intent intent1 = getIntent();
        String subtotall = intent1.getStringExtra("subtotal");
        String totall = intent1.getStringExtra("total");

        Intent intent = new Intent(LogIn.this,Create_account.class);
        intent.putExtra("activity","cart");
        intent.putExtra("subtotal",subtotall);
        intent.putExtra("total",totall);
        startActivity(intent);
    }
}