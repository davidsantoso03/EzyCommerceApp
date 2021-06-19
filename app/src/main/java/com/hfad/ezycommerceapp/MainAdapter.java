package com.hfad.ezycommerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<ProductModel.Product> products ;
    private Context context;
    private AdapterListener listener;

    public MainAdapter(Context context,List<ProductModel.Product> products,AdapterListener listener) {
        this.products = products;
        this.context = context;
        this.listener = listener;
    }



    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main,parent,false)
            );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int x) {
       final  ProductModel.Product product = products.get(x);
        holder.textView.setText(product.getName());
        Picasso.get()
                .load( product.getImg() )
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .fit(). centerCrop()
                .into(holder.imageView);
        holder.price.setText("$" +  String.valueOf(product.getPrice()));

        holder.itemView.setOnClickListener(v -> listener.onClick(product));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        TextView price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.price);

        }
    }

    public void setData(List<ProductModel.Product> data){
        products.clear();
        products.addAll(data);
        notifyDataSetChanged();
    }

    public interface AdapterListener{
        void onClick(ProductModel.Product products);
    }



}
