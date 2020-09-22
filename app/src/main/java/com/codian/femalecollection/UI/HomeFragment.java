package com.codian.femalecollection.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codian.femalecollection.R;
import com.codian.femalecollection.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }
}