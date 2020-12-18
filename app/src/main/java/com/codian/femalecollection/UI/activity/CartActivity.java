package com.codian.femalecollection.UI.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Adapter.CartAdapter;
import com.codian.femalecollection.UI.Model.ModelAll;
import com.codian.femalecollection.UI.Model.ModelCartRoom;
import com.codian.femalecollection.UI.MysharedPreferance;
import com.codian.femalecollection.UI.Place_order;
import com.codian.femalecollection.UI.Repository.CartRepository;
import com.codian.femalecollection.UI.retrofit.ApiClint;
import com.codian.femalecollection.UI.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity implements OnDataSend {

    ConstraintLayout emptyimage,constraintLayout;
    RecyclerView recyclerView;
    CartAdapter adapter;
    TextView address,addresstext,subtotal,total,discount,order_now;
    List<ModelCartRoom> arrayList;
    Context context;
    CartRepository repository;
    ApiInterface apiInterface;
    MysharedPreferance sharedPreferance;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        emptyimage = findViewById(R.id.empty_cartimg);
        constraintLayout = findViewById(R.id.subtotallayout);
        subtotal = findViewById(R.id.textView12);
        discount = findViewById(R.id.textView13);
        total = findViewById(R.id.textView15);
        address = findViewById(R.id.textView6);
        addresstext = findViewById(R.id.textView7);
        order_now = findViewById(R.id.order_now);




        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        //apiInterface = ApiClint.instance();
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());

        String useraddress =  sharedPreferance.getAddress();
        if (useraddress.equals("none")){
            address.setVisibility(View.GONE);
            addresstext.setVisibility(View.GONE);
        }else {
            address.setVisibility(View.VISIBLE);
            addresstext.setVisibility(View.VISIBLE);
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter = new CartAdapter(getApplicationContext(),arrayList,repository,this);
        recyclerView.setAdapter(adapter);

        sharedPreferance = MysharedPreferance.getPreferences(CartActivity.this);

        repository = new CartRepository(getApplicationContext());




         address.setText(sharedPreferance.getAddress());


        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                arrayList.clear();
                arrayList.addAll(modelCartRooms);
                adapter.notifyDataSetChanged();



                if (modelCartRooms.size() == 0){
                    constraintLayout.setVisibility(View.GONE);
                    emptyimage.setVisibility(View.VISIBLE);

                    emptyimage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /*Intent intent = new Intent(CartActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();*/
                            onBackPressed();
                        }
                    });

                }else {
                    constraintLayout.setVisibility(View.VISIBLE);
                    emptyimage.setVisibility(View.GONE);
                }


            }
        });




        order_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (address.getText().toString().isEmpty())
                {
                    address.setError("Invalid address");

                }
                else {

                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {


                        if (sharedPreferance.getPhone().equals("none"))
                        {

                            String addresss = address.getText().toString();
                            Intent intent=new Intent(CartActivity.this,LogIn.class);


                            intent.putExtra("activity","cart");
                            intent.putExtra("address",addresss);
                            intent.putExtra("subtotal",subtotal.getText().toString());
                            intent.putExtra("total",total.getText().toString());
                            startActivity(intent);
                        }
                        else {


                            String addresss = address.getText().toString();
                            String phonee = sharedPreferance.getPhone();

                            sharedPreferance.setAddress(addresss);

                            ModelAll modelAll = new ModelAll();
                            modelAll.setPhone(phonee);
                            modelAll.setAddress(addresss);

                            updateAddress(modelAll);

                            String getInvoiveID=getOrderNumberGenerator();

                            Date d = new Date();
                            CharSequence s  = DateFormat.format("d MMMM, yyyy ", d.getTime());

                            for ( i=0;i<arrayList.size();i++)
                            {

                                 modelAll=new ModelAll();
                                modelAll.setProduct_name(arrayList.get(i).getP_name());
                                modelAll.setProduct_price(arrayList.get(i).getP_price());
                                modelAll.setQuantity(arrayList.get(i).getQuantity());
                                modelAll.setInvoice_number(getInvoiveID);
                                modelAll.setUser_phone(sharedPreferance.getPhone());
                                modelAll.setSubtotal(subtotal.getText().toString());
                                modelAll.setTotal(total.getText().toString());
                                modelAll.setSize(arrayList.get(i).getSize());
                                modelAll.setDate(s.toString());




                                sendData(modelAll);





                            }


                            Intent intent=new Intent(CartActivity.this, Place_order.class);
                            String orderitem = String.valueOf(arrayList.size());
                            intent.putExtra("totalitem",orderitem);
                            intent.putExtra("subtotal",subtotal.getText().toString());
                            intent.putExtra("total",total.getText().toString());
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }




                    }
                    else {

                        Toast.makeText(CartActivity.this, "Check the internet connection", Toast.LENGTH_SHORT).show();


                    }

                }


            }
        });









    }

public void updateAddress(ModelAll modelAll){

    Retrofit instance = ApiClint.instance();
    apiInterface = instance.create(ApiInterface.class);

    apiInterface.updateUserInfo(modelAll).enqueue(new Callback<ModelAll>() {
        @Override
        public void onResponse(Call<ModelAll> call, Response<ModelAll> response) {


        }

        @Override
        public void onFailure(Call<ModelAll> call, Throwable t) {



        }
    });

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

    @Override
    public void totalPrice(String subtotall, String discount) {
        subtotal.setText(subtotall+" BDT");

        int totall = 50+Integer.parseInt(subtotall);

        total.setText(totall+" BDT");


    }

    public void backbtn(View view) {
        onBackPressed();
    }



   /* public void confirmorderId(View view) {

        String ordernumberselfservice = getOrderNumberGenerator();
        String useraddress =  sharedPreferance.getAddress();
        String sub = subtotal.getText().toString();
        String totalll = total.getText().toString();

        if (useraddress.equals("none")){
            Intent intent = new Intent(getApplicationContext(),LogIn.class);

            intent.putExtra("subtotall", sub);
            intent.putExtra("totall", totalll);
            intent.putExtra("invoicenum",ordernumberselfservice);
            intent.putExtra("activity","cart");

            startActivity(intent);

        }else {
            Toast.makeText(context, "Ok Done", Toast.LENGTH_SHORT).show();
        }


    }*/


}