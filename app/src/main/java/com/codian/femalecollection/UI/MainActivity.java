package com.codian.femalecollection.UI;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codian.femalecollection.R;
import com.codian.femalecollection.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ActivityMainBinding binding;
    HomeFragment homeFragment;
    int valu =0;
    Toolbar toolbarr;
    TextView toolbarTitle,cartQuantity;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        toolbarr=findViewById(R.id.toolbar);
        setSupportActionBar(toolbarr);

        cartQuantity = findViewById(R.id.cart_quantity_id);
        toolbarTitle=findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(R.string.app_name);


        setSupportActionBar(binding.include.toolbar);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,
                binding.drawerLayout,binding.include.toolbar,R.string.open, R.string.close);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.black));


        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(this);

      //  binding.hideNav.setOnClickListener(this);
        binding.additem.setOnClickListener(this);
        binding.category.setOnClickListener(this);
        binding.home.setOnClickListener(this);
        binding.logout.setOnClickListener(this);
        binding.profile.setOnClickListener(this);
        binding.setting.setOnClickListener(this);
        initFragmentHome();
    }


    private void initFragmentHome(){
        homeFragment=new HomeFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(binding.include.contentMain.fragmentContainer.getId(),homeFragment,"HomeFragment").commit();

    }

    //for action bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.cartmenuid){
            // Toast.makeText(this, "Q & A", Toast.LENGTH_SHORT).show();
            valu++;
            if (valu>0){
                cartQuantity.setVisibility(View.VISIBLE);
            }

            cartQuantity.setText(""+valu);

            if (valu>10){
                cartQuantity.setText("10+");
            }
            Toast.makeText(this, ""+valu, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*case R.id.hide_nav:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                break;*/
            case R.id.additem:

                break;
            case R.id.profile:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.category:

                break;
            case R.id.logout:
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                break;
            default:


        }
    }
}