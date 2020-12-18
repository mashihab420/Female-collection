package com.codian.femalecollection.UI.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
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
import com.codian.femalecollection.databinding.ActivityProductDescriptionBinding;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Create_account extends AppCompatActivity {

    private ActivityCreateAccountBinding binding;
    ApiInterface apiInterface;
    MysharedPreferance sharedPreferance;

    List<ModelCartRoom> arrayList;
    Context context;
    CartRepository repository;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());

        arrayList = new ArrayList<>();
        repository = new CartRepository(getApplicationContext());

        String activity = getIntent().getStringExtra("activity");
        if (activity.equals("home")) {
            binding.textView5.setVisibility(View.GONE);
            binding.textView18.setVisibility(View.GONE);
            Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "cart", Toast.LENGTH_SHORT).show();
            binding.textView5.setVisibility(View.VISIBLE);
            binding.textView18.setVisibility(View.VISIBLE);
        }

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (activity.equals("home")){
                    String get_name = binding.etName.getText().toString();
                    String get_email = binding.etEmail.getText().toString();
                    String get_phone = binding.etPhone.getText().toString();
                    String get_address = binding.etAddress.getText().toString();
                    String get_pass = binding.createPass.getText().toString();
                    String get_con_pass = binding.confirmPassword.getText().toString();

                    if (!get_con_pass.equals(get_pass)) {
                        Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                    } else if (get_name.isEmpty()) {
                        binding.etName.setError("Invalid name");
                    } else if (get_email.isEmpty()) {
                        binding.etEmail.setError("Invalid email");
                    } else if (get_phone.isEmpty() || get_phone.length() < 11 || get_phone.length() > 11) {
                        binding.etPhone.setError("Invalid phone");
                    } else if (get_address.isEmpty()) {
                        binding.etAddress.setError("Invalid address");
                    } else {
                        binding.progressBar.setVisibility(View.VISIBLE);
                        binding.btSave.setVisibility(View.GONE);
                        Retrofit instance = ApiClint.instance();
                        apiInterface = instance.create(ApiInterface.class);
                        ModelAll modelAll = new ModelAll();
                        modelAll.setName(get_name);
                        modelAll.setEmail(get_email);
                        modelAll.setAddress(get_address);
                        modelAll.setPhone(get_phone);
                        modelAll.setPassword(get_con_pass);


                        apiInterface.create_account(modelAll).enqueue(new Callback<ModelAll>() {
                            @Override
                            public void onResponse(Call<ModelAll> call, Response<ModelAll> response) {


                                Toast.makeText(getApplicationContext(), "Successfully created", Toast.LENGTH_SHORT).show();
                                sharedPreferance.setData(get_name);
                                sharedPreferance.setPhone(get_phone);
                                sharedPreferance.setEmail(get_email);
                                sharedPreferance.setAddress(get_address);


                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);


                            }

                            @Override
                            public void onFailure(Call<ModelAll> call, Throwable t) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.btSave.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }else {

                    String get_name = binding.etName.getText().toString();
                    String get_email = binding.etEmail.getText().toString();
                    String get_phone = binding.etPhone.getText().toString();
                    String get_address = binding.etAddress.getText().toString();
                    String get_pass = binding.createPass.getText().toString();
                    String get_con_pass = binding.confirmPassword.getText().toString();

                    if (!get_con_pass.equals(get_pass)) {
                        Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                    } else if (get_name.isEmpty()) {
                        binding.etName.setError("Invalid name");
                    } else if (get_email.isEmpty()) {
                        binding.etEmail.setError("Invalid email");
                    } else if (get_phone.isEmpty() || get_phone.length() < 11 || get_phone.length() > 11) {
                        binding.etPhone.setError("Invalid phone");
                    } else if (get_address.isEmpty()) {
                        binding.etAddress.setError("Invalid address");
                    } else {
                        binding.progressBar.setVisibility(View.VISIBLE);
                        binding.btSave.setVisibility(View.GONE);
                        Retrofit instance = ApiClint.instance();
                        apiInterface = instance.create(ApiInterface.class);
                        ModelAll modelAll = new ModelAll();
                        modelAll.setName(get_name);
                        modelAll.setEmail(get_email);
                        modelAll.setAddress(get_address);
                        modelAll.setPhone(get_phone);
                        modelAll.setPassword(get_con_pass);


                        apiInterface.create_account(modelAll).enqueue(new Callback<ModelAll>() {
                            @Override
                            public void onResponse(Call<ModelAll> call, Response<ModelAll> response) {


                              //  Toast.makeText(getApplicationContext(), "Successfully created", Toast.LENGTH_SHORT).show();
                                sharedPreferance.setData(get_name);
                                sharedPreferance.setPhone(get_phone);
                                sharedPreferance.setEmail(get_email);
                                sharedPreferance.setAddress(get_address);


                                repository.getAllData().observe(Create_account.this, new Observer<List<ModelCartRoom>>() {
                                    @Override
                                    public void onChanged(List<ModelCartRoom> modelCartRooms) {
                                        arrayList.addAll(modelCartRooms);

                                        ModelAll modelAll = new ModelAll();
                                        modelAll.setPhone(get_phone);
                                        modelAll.setAddress(get_address);

                                        orderitem(modelAll);
                                    }
                                });


                            }

                            @Override
                            public void onFailure(Call<ModelAll> call, Throwable t) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.btSave.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }



            }
        });


    }


    public  void orderitem(ModelAll modelAll){


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
            modelAll.setDate(datee.toString());




            sendData(modelAll);





        }



        Intent intent=new Intent(Create_account.this, Place_order.class);
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


    public void gotologinpage(View view) {
        Intent intent = new Intent(getApplicationContext(), LogIn.class);
        startActivity(intent);
    }
}