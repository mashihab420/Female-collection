package com.codian.femalecollection.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Model.ModelAll;

import java.util.ArrayList;
import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.MyViewHolder> {
    Context context;
   List<ModelAll> products;

    public AllProductAdapter(Context context, List<ModelAll> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(products.get(position).getPName());
        holder.price.setText(products.get(position).getPPrice());

        Glide
                .with(context)
                // .load(R.drawable.demopic1)
                .load(products.get(position).getUrl())
                .centerCrop()
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView6);
            price = itemView.findViewById(R.id.priceid);
            image = itemView.findViewById(R.id.imageView3);
        }
    }
}
