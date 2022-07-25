package com.hx.mdesign.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hx.mdesign.R;

import java.util.List;

/**
 * @author: Hx
 * @date: 2022年03月04日 16:30
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private ProductClickCallBack clickCallBack;
    private Context mContext;
    private List<Product> products;

    public ProductAdapter(List<Product> productList){
        this.products = productList;
    }

    public void setClickCallBack(ProductClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView car_image;
        TextView car_name;

        public ViewHolder(@NonNull View view) {
            super(view);
            cardView = (CardView) view;
            car_image = view.findViewById(R.id.car_image);
            car_name = view.findViewById(R.id.car_name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent, false);
        //        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = holder.getLayoutPosition();
//                Product product = products.get(position);
//                ProductDetailsActivity.startActivity(mContext, product.getCarName(), product.getImageId());
//
//            }
//        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.car_name.setText(product.getCarName());
        Glide.with(mContext).load(product.getImageId()).into(holder.car_image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                clickCallBack.onItemClick(holder.cardView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
