package com.codian.femalecollection.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codian.femalecollection.R;


public class Fragment_create_account extends Fragment {


    public Fragment_create_account() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container!=null)
        {

            container.removeAllViews();

        }


        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }
}