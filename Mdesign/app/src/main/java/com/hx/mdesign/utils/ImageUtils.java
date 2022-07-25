package com.hx.mdesign.utils;

import static com.hx.mdesign.utils.Conversion.fileToBytes;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;

import com.hx.mdesign.R;
import com.hx.mdesign.product.Product;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

/**
 * @author: Hx
 * @date: 2022年03月04日 16:50
 */
public final class ImageUtils {

    public static Product[] initCarArray() {

        Product[] products = {new Product("英梨梨", R.drawable.yinglili),
                new Product("麻中 蓬", R.drawable.mazhongteng),
                new Product("凸守", R.drawable.tushou),
                new Product("八重神子", R.drawable.bachong)
        };

        return products;
    }

    public static List<Product> initCarList(Product[] products, List<Product> productList) {

        productList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(products.length);
            productList.add(products[index]);
        }
        return productList;
    }

    /**
     * Bitmap转Base64
     * @param bitmap
     * @return
     */
    public static String bitmap2Base64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;

        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();
                // 转换为字节数组
                byte[] byteArray = baos.toByteArray();
                // 转换为字符串
                result = Base64.encodeToString(byteArray, Base64.DEFAULT);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    /**
     * File转换为Base64
     *
     * @param path
     * @return
     */
    public static String fileToBase64(String path) {
        return Base64.encodeToString(fileToBytes(path), Base64.DEFAULT);
    }

    /**
     *
     * @param uri
     */
    @TargetApi(19)
    public static String imgPathFromUri(Uri uri) {

        String path = null;

        if (DocumentsContract.isDocumentUri(App.getContext(), uri)) {
            //Uri是DocumentsContract类型的,通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                path = imgPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.document".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content:" +
                        "//downloads/public_downloads"), Long.parseLong(docId));
                path = imgPath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的Uri，则使用普通方法处理
            path = imgPath(uri, null);
        } else if ("file".equals(uri.getScheme())) {
            //如果是file类型的Uri，直接获取图片路径即可
            path = uri.getPath();
        }

        return path;
    }

    /**
     * 获取照片路径
     * @param uri
     * @param selection
     * @return
     */
    private static String imgPath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = App.getContext().getContentResolver().query(uri,
                null, selection, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}
