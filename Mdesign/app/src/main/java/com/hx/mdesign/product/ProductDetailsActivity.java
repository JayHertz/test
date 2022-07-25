package com.hx.mdesign.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.hx.mdesign.databinding.ActivityProductDetialsBinding;


public class ProductDetailsActivity extends AppCompatActivity {

    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_IMAGE = "product_image_id";

    private ActivityProductDetialsBinding binding;

    public static void startActivity(Context context, String carName, int carImageId) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.PRODUCT_NAME, carName);
        intent.putExtra(ProductDetailsActivity.PRODUCT_IMAGE, carImageId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetialsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //获取数据
        Intent intent = getIntent();
        String productName = intent.getStringExtra(PRODUCT_NAME);
        int productImageId = intent.getIntExtra(PRODUCT_IMAGE, 0);


        //View
        Toolbar toolbar = binding.Toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = binding.collapsingToolbarLayout;
        ImageView carImageView = binding.productImageView;
        TextView carContentText = binding.productContentText;
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(productName);
        Glide.with(this).load(productImageId).into(carImageView);
//        String carContent = generateCarContent(productName);
        carContentText.setText(productName);
    }

    private String generateCarContent(String carName) {
        StringBuilder carContent = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            carContent.append(carName);
        }
        return carContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}