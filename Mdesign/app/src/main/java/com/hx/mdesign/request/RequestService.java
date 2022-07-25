package com.hx.mdesign.request;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class RequestService extends Service {

    private static final String TAG = "TAG_RequestService";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}