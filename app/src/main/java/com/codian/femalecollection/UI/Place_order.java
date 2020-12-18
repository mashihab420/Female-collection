package com.codian.femalecollection.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.activity.CartActivity;
import com.codian.femalecollection.UI.activity.MainActivity;

public class Place_order extends AppCompatActivity {

    TextView name,phone,email,address,gotohome;
    MysharedPreferance mysharedPreferance;

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


        name.setText(mysharedPreferance.getData());
        phone.setText(mysharedPreferance.getPhone());
        email.setText(mysharedPreferance.getemail());
        address.setText(mysharedPreferance.getAddress());

        gotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Place_order.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


            }
        });



    }
}