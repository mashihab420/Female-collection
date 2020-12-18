package com.codian.femalecollection.UI;

import android.content.Context;
import android.content.SharedPreferences;

public class MysharedPreferance {


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor ;
    private static MysharedPreferance mysharedPreferance=null;

    private MysharedPreferance(Context context)
    {
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }


  public static synchronized MysharedPreferance getPreferences(Context context)
  {

      if(mysharedPreferance==null) {
          mysharedPreferance = new MysharedPreferance(context);
      }

      return mysharedPreferance;
  }


    public void setData(String user)
    {
        editor.putString("login",user);
        editor.apply();
    }

    public String getData()
    {
        return sharedPreferences.getString("login","none");
    }







    public  void setPhone(String phone)
    {
        editor.putString("phone",phone);
        editor.apply();

    }


    public String getPhone(){return  sharedPreferences.getString("phone","none");}


    public  void setAddress(String address)
    {
        editor.putString("address",address);
        editor.apply();

    }

    public String getAddress(){return  sharedPreferences.getString("address","none");}


    public  void setEmail(String email)
    {
        editor.putString("email",email);
        editor.apply();

    }

    public String getemail(){return  sharedPreferences.getString("email","none");}






    public  void setOwnerID(String address)
    {
        editor.putString("ownerID",address);
        editor.apply();

    }

    public String getOwnerId(){return  sharedPreferences.getString("ownerID","none");}





}
