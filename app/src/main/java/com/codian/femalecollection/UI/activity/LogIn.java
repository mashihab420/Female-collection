package com.codian.femalecollection.UI.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Model.ModelAll;
import com.codian.femalecollection.UI.MysharedPreferance;
import com.codian.femalecollection.UI.retrofit.ApiInterface;
import com.codian.femalecollection.databinding.ActivityCreateAccountBinding;
import com.codian.femalecollection.databinding.ActivityLogInBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {

    private ActivityLogInBinding binding;
    ApiInterface apiInterface;
    MysharedPreferance sharedPreferance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());

        binding.btLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.btLogIn.setVisibility(View.GONE);
                binding.progressBar2.setVisibility(View.VISIBLE);
                String getPhone=binding.etLoginPhone.getText().toString();
                String pass=binding.editTextTextPassword.getText().toString();


                apiInterface.login(getPhone,pass).enqueue(new Callback<ModelAll>() {
                    @Override
                    public void onResponse(Call<ModelAll> call, Response<ModelAll> response) {


                    }

                    @Override
                    public void onFailure(Call<ModelAll> call, Throwable t) {

                    }
                });


            }
        });

    }
}