package com.codian.femalecollection.UI.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Model.ModelCartRoom;
import com.codian.femalecollection.UI.Repository.CartRepository;
import com.codian.femalecollection.UI.activity.OnDataSend;


import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    List<ModelCartRoom> cart;
    Context context;
    CartRepository repository;
    private OnDataSend dataSend;
    RadioButton radioButton;
    String selecttedtext;
    int total = 0;
    int taka = 0;
    int quantity;

    int disquantity;
    int distotal = 0;
    int distaka = 0;
    int offer;
    int totaldiscount = 0;

    int dispercent;
    int sendSubtotal = 0;
    String value = "";

    public CartAdapter(Context context, List<ModelCartRoom> cart, CartRepository repository, OnDataSend dataSend) {
        this.cart = cart;
        this.context = context;
        this.repository = repository;
        this.dataSend = dataSend;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(cart.get(position).getP_name());
        holder.price.setText(cart.get(position).getP_price());


        holder.cart_quantity.setText(cart.get(position).getQuantity());
        String sizee = cart.get(position).getSize();


        if (sizee.equals("S")){
            holder.bt1.setChecked(true);
        }
        if (sizee.equals("M")){
            holder.bt2.setChecked(true);
        }
        if (sizee.equals("L")){
            holder.bt3.setChecked(true);
        }
        if (sizee.equals("XL")){

            holder.bt4.setChecked(true);
        }



        holder.bt1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 CartRepository repository = new CartRepository(context);
                 cart.get(position).setSize("S");
                 repository.update(cart.get(position));
                 holder.bt1.setChecked(true);
                /* taka = 0;
                 total =0;


                 distotal = 0;
                 distaka = 0;*/

             }
         });
        holder.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartRepository repository = new CartRepository(context);
                cart.get(position).setSize("M");
                repository.update(cart.get(position));
                holder.bt2.setChecked(true);
            /*    taka = 0;
                total =0;


                distotal = 0;
                distaka = 0;*/
            }
        });

        holder.bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartRepository repository = new CartRepository(context);
                cart.get(position).setSize("L");
                repository.update(cart.get(position));
                holder.bt3.setChecked(true);
              /*  taka = 0;
                total =0;


                distotal = 0;
                distaka = 0;*/
            }
        });

        holder.bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartRepository repository = new CartRepository(context);
                cart.get(position).setSize("XL");
                repository.update(cart.get(position));


                holder.bt4.setChecked(true);

            /*    taka = 0;
                total =0;


                distotal = 0;
                distaka = 0;*/
            }
        });




            Glide
                    .with(context)
                    .load(cart.get(position).getUrl())
                    .into(holder.productimage);

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantitys = Integer.parseInt(cart.get(position).getQuantity());

                //TODO ai minus hoya data room a update kora lagbe
                if (quantitys > 1) {
                    quantitys -= 1;

                    CartRepository repository = new CartRepository(context);
                    cart.get(position).setQuantity("" + quantitys);
                    ;
                    repository.update(cart.get(position));

                    holder.cart_quantity.setText(cart.get(position).getQuantity());

                    taka = 0;
                    total = 0;

                    distotal = 0;
                    distaka = 0;

                }
            }
        });


        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantitys = Integer.parseInt(cart.get(position).getQuantity());
                quantitys += 1;

                //TODO ai plus hoya data room a update kora lagbe

                CartRepository repository = new CartRepository(context);
                cart.get(position).setQuantity("" + quantitys);
                repository.update(cart.get(position));
                holder.cart_quantity.setText(cart.get(position).getQuantity());


                taka = 0;
                total = 0;


                distotal = 0;
                distaka = 0;

            }
        });

        //subtotal calculation start
        quantity = Integer.parseInt(cart.get(position).getQuantity());
        taka = Integer.parseInt(cart.get(position).getP_price());
        // Log.d("taka", ""+taka);
        sendSubtotal = getSubtotal(quantity, taka);
        //subtotal calculation end

        Log.d("taka", "" + total);




        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  repository.deleteSingleData(cart.get(position).getId());

                try {
                    CartRepository repository = new CartRepository(context);
                    ModelCartRoom modelCartRoom = new ModelCartRoom();
                    modelCartRoom.setId(cart.get(position).getId());

                    total = 0;

                    distotal = 0;

                    repository.delete(modelCartRoom);
                    cart.clear();
                    cart.remove(cart.get(position).getId() - 1);

                    //taka =0;

                    notifyDataSetChanged();

                } catch (Exception e) {
                    // Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                }


            }
        });


        dataSend.totalPrice("" + sendSubtotal);


    }

    public int getSubtotal(int quantity,int amount){
        int totalamount=0;

        for(int i = 0 ; i < cart.size(); i++) {
            int qun = Integer.parseInt(cart.get(i).getQuantity());
            int price = (Integer.parseInt(cart.get(i).getP_price()))*qun;




            totalamount = totalamount + price;
        }
        return totalamount;
    }


    @Override
    public int getItemCount() {
        return cart.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView productimage, brandimage, offerpercent, delete;
        public TextView title, price, cart_quantity, plus, minus;
        ConstraintLayout productlayout;
        RadioGroup radioGroup;
        RadioButton bt1, bt2, bt3, bt4;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_name_id);
            price = itemView.findViewById(R.id.price_product_id);
            productimage = itemView.findViewById(R.id.product_image_id);
            cart_quantity = itemView.findViewById(R.id.cart_quantity_id);
            productlayout = itemView.findViewById(R.id.productlayoutId);
            delete = itemView.findViewById(R.id.delete_fromCart);
            plus = itemView.findViewById(R.id.plusebtid);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            bt1 = itemView.findViewById(R.id.small);
            bt2 = itemView.findViewById(R.id.medium);
            bt3 = itemView.findViewById(R.id.large);
            bt4 = itemView.findViewById(R.id.extralarge);
            minus = itemView.findViewById(R.id.minusbtid);
            //  value = ((RadioButton)itemView.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        }
    }
}
