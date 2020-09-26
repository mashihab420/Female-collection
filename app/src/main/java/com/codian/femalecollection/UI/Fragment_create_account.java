package com.codian.femalecollection.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Model.ModelAll;
import com.codian.femalecollection.UI.retrofit.ApiClint;
import com.codian.femalecollection.UI.retrofit.ApiInterface;
import com.codian.femalecollection.databinding.FragmentCreateAccountBinding;
import com.codian.femalecollection.databinding.FragmentHomeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Fragment_create_account extends Fragment {

    private FragmentCreateAccountBinding binding;
    ApiInterface apiInterface;
    MysharedPreferance sharedPreferance;

    public Fragment_create_account() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container!=null)
        {

            container.removeAllViews();

        }


        binding = FragmentCreateAccountBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        sharedPreferance = MysharedPreferance.getPreferences(getContext());

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                binding.progressBar.setVisibility(View.VISIBLE);
                binding.btSave.setVisibility(View.GONE);
                String get_name=binding.etName.getText().toString();
                String get_email=binding.etEmail.getText().toString();
                String get_phone=binding.etPhone.getText().toString();
                String get_address=binding.etAddress.getText().toString();
                String get_pass=binding.createPass.getText().toString();
                String get_con_pass=binding.confirmPassword.getText().toString();

                if (!get_con_pass.equals(get_pass))
                {
                    Toast.makeText(getContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
                else if (get_name.isEmpty())
                {
                    binding.etName.setError("Invalid name");
                }

                else if (get_email.isEmpty())
                {
                    binding.etEmail.setError("Invalid email");
                }

                else if (get_phone.isEmpty() || get_phone.length()<11||get_phone.length()>11)
                {
                    binding.etPhone.setError("Invalid phone");
                }

                else if (get_address.isEmpty())
                {
                    binding.etAddress.setError("Invalid address");
                }

               else {

                   Retrofit instance = ApiClint.instance();
                    apiInterface = instance.create(ApiInterface.class);
                    ModelAll modelAll= new ModelAll();
                    modelAll.setName(get_name);
                    modelAll.setEmail(get_email);
                    modelAll.setAddress(get_address);
                    modelAll.setPhone(get_phone);
                    modelAll.setPassword(get_con_pass);


                    apiInterface.create_account(modelAll).enqueue(new Callback<ModelAll>() {
                        @Override
                        public void onResponse(Call<ModelAll> call, Response<ModelAll> response) {


                            Toast.makeText(getContext(), "Successfully created", Toast.LENGTH_SHORT).show();
                            sharedPreferance.setData(get_name);
                            sharedPreferance.setPhone(get_phone);
                            sharedPreferance.setEmail(get_email);
                            sharedPreferance.setAddress(get_address);





                        }

                        @Override
                        public void onFailure(Call<ModelAll> call, Throwable t) {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.btSave.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }
        });





        return view;
    }
}