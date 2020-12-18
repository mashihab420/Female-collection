package com.codian.femalecollection.UI.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.HomeFragment;
import com.codian.femalecollection.UI.Model.ModelCartRoom;
import com.codian.femalecollection.UI.MysharedPreferance;
import com.codian.femalecollection.UI.Repository.CartRepository;
import com.codian.femalecollection.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ActivityMainBinding binding;
    HomeFragment homeFragment;
    int valu =0;
    Toolbar toolbarr;
    TextView toolbarTitle,cartQuantity;
    MysharedPreferance sharedPreferance;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    CartRepository repository;
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
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.white));


        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(this);

      //  binding.hideNav.setOnClickListener(this);

        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        if (!sharedPreferance.getData().equals("none"))
        {
            binding.userName.setText(sharedPreferance.getData());
            binding.createaccount.setVisibility(View.GONE);
            binding.loginid.setVisibility(View.GONE);
            binding.logout.setVisibility(View.VISIBLE);
            binding.orderid.setVisibility(View.VISIBLE);
        }else{
            binding.createaccount.setVisibility(View.VISIBLE);
            binding.loginid.setVisibility(View.VISIBLE);
            binding.logout.setVisibility(View.GONE);
            binding.orderid.setVisibility(View.GONE);
        }


        binding.loginid.setOnClickListener(this);
        binding.cart.setOnClickListener(this);
        binding.logout.setOnClickListener(this);
        binding.createaccount.setOnClickListener(this);
        binding.orderid.setOnClickListener(this);
        initFragmentHome();


        repository = new CartRepository(this);

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                if (modelCartRooms.size()==0){
                    cartQuantity.setVisibility(View.GONE);
                }else {
                    cartQuantity.setVisibility(View.VISIBLE);
                    if(modelCartRooms.size()>99){
                        cartQuantity.setText("99+");
                    }else {
                        cartQuantity.setText(""+modelCartRooms.size());
                    }


                }
            }
        });


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

            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);

          /*  valu++;
            if (valu>0){
                cartQuantity.setVisibility(View.VISIBLE);
            }

            cartQuantity.setText(""+valu);

            if (valu>10){
                cartQuantity.setText("10+");
            }*/

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

            case R.id.cart:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent1=new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent1);
                break;


            case R.id.loginid:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                binding.orderid.setVisibility(View.VISIBLE);
                 intent1=new Intent(getApplicationContext(),LogIn.class);
                intent1.putExtra("activity","home");
                startActivity(intent1);

                break;

            case R.id.createaccount:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent=new Intent(getApplicationContext(),Create_account.class);
                intent.putExtra("activity","home");
                startActivity(intent);

                break;

            case R.id.orderid:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                 intent=new Intent(getApplicationContext(),OrdersActivity.class);
                startActivity(intent);

                break;



            case R.id.logout:
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                sharedPreferance.setData("none");
                sharedPreferance.setAddress("none");
                sharedPreferance.setPhone("none");
                sharedPreferance.setEmail("none");
                sharedPreferance.setOwnerID("none");
                binding.userName.setText("");
                binding.createaccount.setVisibility(View.VISIBLE);
                binding.loginid.setVisibility(View.VISIBLE);
                binding.logout.setVisibility(View.GONE);
                binding.orderid.setVisibility(View.GONE);
                break;


            default:


        }
    }
}