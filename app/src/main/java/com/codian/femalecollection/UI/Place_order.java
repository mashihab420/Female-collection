package com.codian.femalecollection.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.activity.CartActivity;
import com.codian.femalecollection.UI.activity.MainActivity;

public class Place_order extends AppCompatActivity {

    TextView name,phone,email,address,gotohome;
    MysharedPreferance mysharedPreferance;
    RadioButton radioButton1,radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);


        mysharedPreferance = MysharedPreferance.getPreferences(Place_order.this);
        name=findViewById(R.id.delivery_name);
        phone=findViewById(R.id.delivery_phone);
        email=findViewById(R.id.delivery_email);
        address=findViewById(R.id.delivery_address);
        gotohome=findViewById(R.id.button);

        radioButton1 = findViewById(R.id.rb1);
        radioButton2 = findViewById(R.id.rb2);

        radioButton1.setClickable(false);
        radioButton2.setClickable(false);


        name.setText("Order Item : "+getIntent().getStringExtra("totalitem"));
        phone.setText("Subtotal : "+getIntent().getStringExtra("subtotal"));
        email.setText("Total : "+getIntent().getStringExtra("total"));
        address.setText("Address : "+mysharedPreferance.getAddress());

        gotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Place_order.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Place_order.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}