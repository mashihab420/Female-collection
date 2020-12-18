package com.codian.femalecollection.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codian.femalecollection.R;
import com.codian.femalecollection.UI.Model.ModelAll;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    List<ModelAll> orders;
    Context context;

    public OrderAdapter(List<ModelAll> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.invoiceno.setText("#"+orders.get(position).getInvoice_number());
        holder.total.setText(orders.get(position).getTotal());
        holder.date.setText(orders.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView invoiceno,total,date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            invoiceno = itemView.findViewById(R.id.textView20);
            total = itemView.findViewById(R.id.totalid);
            date = itemView.findViewById(R.id.textView21);
        }
    }
}
