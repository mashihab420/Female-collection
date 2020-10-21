package com.codian.femalecollection.UI.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Adapter.CartAdapter;
import com.codian.femalecollection.UI.Model.ModelCartRoom;
import com.codian.femalecollection.UI.MysharedPreferance;
import com.codian.femalecollection.UI.Repository.CartRepository;
import com.codian.femalecollection.UI.retrofit.ApiClint;
import com.codian.femalecollection.UI.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartActivity extends AppCompatActivity implements OnDataSend {

    ConstraintLayout emptyimage,constraintLayout;
    RecyclerView recyclerView;
    CartAdapter adapter;
    TextView address,subtotal,total,discount;
    List<ModelCartRoom> arrayList;
    Context context;
    CartRepository repository;
    ApiInterface apiInterface;
    MysharedPreferance sharedPreferance;
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

        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        //apiInterface = ApiClint.instance();
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter = new CartAdapter(getApplicationContext(),arrayList,repository,this);
        recyclerView.setAdapter(adapter);


        repository = new CartRepository(getApplicationContext());


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

        String useraddress =  sharedPreferance.getAddress();
        if (useraddress.equals("none")){
            address.setText("");

        }else {
            address.setText(useraddress);
        }




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



    public void confirmorderId(View view) {

        String ordernumberselfservice = getOrderNumberGenerator();
        String useraddress =  sharedPreferance.getAddress();
        String sub = subtotal.getText().toString();
        String totalll = total.getText().toString();

        if (useraddress.equals("none")){
            Intent intent = new Intent(getApplicationContext(),Create_account.class);

            intent.putExtra("subtotall", sub);
            intent.putExtra("totall", totalll);
            intent.putExtra("invoicenum",ordernumberselfservice);

            startActivity(intent);

        }else {
            Toast.makeText(context, "Ok Done", Toast.LENGTH_SHORT).show();
        }


    }
}