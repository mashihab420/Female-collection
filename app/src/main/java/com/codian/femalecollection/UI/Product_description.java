package com.codian.femalecollection.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

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
}