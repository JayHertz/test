package com.hx.mdesign.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hx.mdesign.utils.App;

/**
 * @author: Hx
 * @date: 2022年03月19日 17:38
 */
public class MyRequestPermission extends ActivityResultContract<String, Boolean> {

    public static final String ACTION_REQUEST_PERMISSIONS =
            "androidx.activity.result.contract.action.REQUEST_PERMISSIONS";

    public static final String EXTRA_PERMISSIONS =
            "androidx.activity.result.contract.extra.PERMISSIONS";

    public static final String EXTRA_PERMISSION_GRANT_RESULTS =
            "androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS";

    @NonNull
    static Intent createIntent(@NonNull String[] input) {
        return new Intent(ACTION_REQUEST_PERMISSIONS).putExtra(EXTRA_PERMISSIONS, input);
    }

    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, String input) {

        Intent intent = new Intent();

        switch (input) {
            case Settings.ACTION_MANAGE_OVERLAY_PERMISSION: {
                intent.setAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + App.getContext().getPackageName()));
            }
            break;

            default: {
                return createIntent(new String[]{input});
            }
        }
        return intent;
    }

    @Override
    public Boolean parseResult(int resultCode, @Nullable Intent intent) {

        if (intent == null || resultCode != Activity.RESULT_OK) return false;
        //已获取权限的数组
        int[] grantResults = intent.getIntArrayExtra(EXTRA_PERMISSION_GRANT_RESULTS);
        //未获取权限
        if (grantResults == null || grantResults.length == 0) return false;
        //返回权限获取结果
        return grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }

}
