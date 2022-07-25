package com.hx.mdesign.garbage;


import static com.hx.mdesign.request.RequestUtil.IMAGE_URL;
import static com.hx.mdesign.request.RequestUtil.getUrl;
import static com.hx.mdesign.utils.ImageUtils.fileToBase64;
import static com.hx.mdesign.utils.ImageUtils.imgPathFromUri;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.hx.mdesign.databinding.ActivityCameraBinding;
import com.hx.mdesign.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CameraActivity extends AppCompatActivity {

    static final String TAG = "TAG_CameraActivity";
    static final int CHOOSE_PHOTO = 2;
    static final int TAKE_PHOTO = 1;
    ImageView picture;
    Button openAlbum;
    Button openCamera;
    Uri imageUri;
    ActivityResultLauncher cameraLauncher;
    ActivityResultLauncher<String> albumLauncher;
    ActivityResultLauncher requestPermission;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CameraActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.hx.mdesign.databinding.ActivityCameraBinding binding = ActivityCameraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //binding
        picture = binding.picture;
        openAlbum = binding.openAlbum;
        openCamera = binding.openCamera;

        //相机回调
        cameraLauncher = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(),
                bitmap -> {
                    if (bitmap != null) {
//                        Glide.with(App.getContext()).load(result).into(picture);
                        Uri uri = BitmapToUri(bitmap);
                        if (uri != null) {
                            String imgBase64 = fileToBase64(imgPathFromUri(uri));
                            String cityId = "310000";
                            OkHttpClient client = new OkHttpClient();
                            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                            RequestBody body = RequestBody.create(JSON, "{cityId:\"" + cityId
                                    + "\",imgBase64:\"" + imgBase64 + "\"}");

                            Request request = new Request.Builder()
                                    .url(getUrl(IMAGE_URL))
                                    .post(body)
                                    .build();

                            client.newCall(request).enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    LogUtils.d(TAG, response.body().string());
                                }
                            });
                        }
                    }
                });

        //权限请求回调
        requestPermission = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                map -> {
                    if (!map.containsValue(false)) {
                        albumLauncher.launch("image/*");
                    }
                });
        //相册回调
        albumLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        String imgBase64 = fileToBase64(imgPathFromUri(uri));
                        String cityId = "310000";
                        OkHttpClient client = new OkHttpClient();
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, "{cityId:\"" + cityId
                                + "\",imgBase64:\"" + imgBase64 + "\"}");

                        Request request = new Request.Builder()
                                .url(getUrl(IMAGE_URL))
                                .post(body)
                                .build();

                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                LogUtils.d(TAG, response.body().string());
                            }
                        });
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initUiFun();
    }

    //设置ui功能
    private void initUiFun() {

        //打开相册
        openAlbum.setOnClickListener(view -> {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermission.launch(permissions);
        });

        //打开相机
        openCamera.setOnClickListener(view -> cameraLauncher.launch(null));


    }

    Uri BitmapToUri(Bitmap bitmap) {
        return Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),
                bitmap, null, null));
    }

}