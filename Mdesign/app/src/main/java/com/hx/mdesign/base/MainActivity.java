package com.hx.mdesign.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;
import com.hx.mdesign.R;
import com.hx.mdesign.databinding.ActivityMainBinding;
import com.hx.mdesign.garbage.CameraActivity;
import com.hx.mdesign.permission.MyRequestPermission;
import com.hx.mdesign.product.Product;
import com.hx.mdesign.product.ProductAdapter;
import com.hx.mdesign.product.ProductDetailsActivity;
import com.hx.mdesign.utils.App;
import com.hx.mdesign.utils.ImageUtils;
import com.hx.mdesign.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG_MainActivity";
    private final List<Product> products = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProductAdapter adapter;
    private ActivityResultLauncher permissionLauncher;
    private ActivityResultLauncher permission;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //ActivityResultLauncher
        permission = registerForActivityResult(new MyRequestPermission(), result ->
                LogUtils.d(TAG, "已获取权限ACTION_MANAGE_OVERLAY_PERMISSION"));
        
        //Toolbar
        Toolbar toolbar = binding.Toolbar;
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.toolbar);
        toolbar.setTitle("MyDesign");

        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.refresh: {
                    swipeRefreshLayout.setRefreshing(true);
                    refreshImage();
                    return true;
                }
                case R.id.bell: {
                    return true;
                }
                case R.id.About: {

                    //检查是否有ACTION_MANAGE_OVERLAY_PERMISSION权限
                    if (!Settings.canDrawOverlays(App.getContext())) {
                        permission.launch(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    }

                    Dialog dialog = new Dialog(this);
                    TextView tv = new TextView(MainActivity.this);
                    tv.setText("this is Toast");
                    dialog.setContentView(tv);
//                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_BASE_APPLICATION);
                    dialog.show();
                    return true;
                }
            }
            return false;
        });

        //DrawerLayout
        drawerLayout = binding.drawerLayout;
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }

        //NavigationView
        NavigationView navigationView = binding.navigation;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Camera: {
                        LogUtils.d(TAG, "Intent to open Camera Activity");
                        CameraActivity.startActivity(MainActivity.this);
                    }
                    break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //recycleView
        RecyclerView recyclerView = binding.recyclerView;
        ImageUtils.initCarList(ImageUtils.initCarArray(), products);
        adapter = new ProductAdapter(products);
        adapter.setClickCallBack((view, position) -> ProductDetailsActivity.startActivity(App.getContext(),
                products.get(position).getCarName(),
                products.get(position).getImageId()));

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //SwipeRefreshLayout
        swipeRefreshLayout = binding.SwipeRefreshLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.cardview_shadow_start_color);
        swipeRefreshLayout.setOnRefreshListener(this::refreshImage);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home: {
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return false;
    }

    private void refreshImage() {
        new Thread(() -> {

            try {
                Thread.sleep(1000);
                Log.d("TAG", "finish sleeping");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                ImageUtils.initCarList(ImageUtils.initCarArray(), products);
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            });
        }).start();
    }
}