package com.codian.femalecollection.UI.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codian.femalecollection.R;
import com.codian.femalecollection.databinding.ActivityMainBinding;
import com.codian.femalecollection.databinding.ActivityProductDescriptionBinding;

public class Product_description extends AppCompatActivity {

    ActivityProductDescriptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Intent intent = getIntent();
        String name = intent.getStringExtra("pname");
        String price = intent.getStringExtra("pprice");
        String des = intent.getStringExtra("description");
        String urll = intent.getStringExtra("url");

        Glide
                .with(getApplicationContext())
                .load(urll)
                .centerCrop()
                .into(binding.imageView);

        binding.textView6.setText(name);
        binding.textView7.setText(price);
        binding.textView14.setText(des);


    }

    public void addToCart(View view) {

      //  String size = (binding.radioGroup.getCheckedRadioButtonId()


       if (binding.radioGroup.getCheckedRadioButtonId()==-1){
           Toast.makeText(this, "You Must Select Dress Size ", Toast.LENGTH_SHORT).show();
       }else{
           String value = ((RadioButton)findViewById(binding.radioGroup.getCheckedRadioButtonId())).getText().toString();

           Toast.makeText(this, ""+value, Toast.LENGTH_SHORT).show();
       }

    }
}