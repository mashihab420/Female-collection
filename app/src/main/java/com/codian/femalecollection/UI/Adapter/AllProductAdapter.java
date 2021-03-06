package com.codian.femalecollection.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Model.ModelAll;
import com.codian.femalecollection.UI.Model.ModelCartRoom;
import com.codian.femalecollection.UI.Repository.CartRepository;
import com.codian.femalecollection.UI.activity.Product_description;

import java.util.ArrayList;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelAll> products;

    public AllProductAdapter(Context context, ArrayList<ModelAll> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(products.get(position).getPName());
        holder.price.setText(products.get(position).getPPrice());
        String pname = products.get(position).getPName();
        String pprice = products.get(position).getPPrice();
        String description = products.get(position).getDescription();
        String url = products.get(position).getUrl();
        String imageurl = "http://femalefashion88.000webhostapp.com/femalefashion/file_upload_api/" + products.get(position).getUrl();
        Glide
                .with(context)
                // .load(R.drawable.demopic1)
                .load(imageurl)
                .centerCrop()
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Product_description.class);
                intent.putExtra("pname", pname);
                intent.putExtra("pprice", pprice);
                intent.putExtra("description", description);
                intent.putExtra("url", url);
                context.startActivity(intent);


            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Add to Cart", Toast.LENGTH_SHORT).show();

                final CartRepository repository = new CartRepository(context);
                String name = products.get(position).getPName();
                String price = products.get(position).getPPrice();
                String url = "http://femalefashion88.000webhostapp.com/femalefashion/file_upload_api/" + products.get(position).getUrl();
                // String url = products.get(position).getUrl();
                String quantity = "1";
                String size = "M";

                repository.insertSingleData(new ModelCartRoom(name, price, quantity, url, size));


            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void search_filter_list(ArrayList<ModelAll> filter_list) {

        products = filter_list;
        notifyDataSetChanged();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, add;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView6);
            price = itemView.findViewById(R.id.priceid);
            image = itemView.findViewById(R.id.imageView3);
            add = itemView.findViewById(R.id.addid);
        }
    }
}
