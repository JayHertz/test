package com.hx.mdesign.request;

import com.hx.mdesign.garbage.Garbage_info;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author: Hx
 * @date: 2022年03月08日 15:57
 */
public interface MyRequest {

    @POST("garbageImageSearch")
    @Headers("Content-Type:application/json;charset=UTF-8")
    @FormUrlEncoded
    Call<Garbage_info> getGarbageInfoByImage(@Query("timestamp") long timestamp,
                                             @QueryMap Map<String, String> q, @FieldMap Map<String, String> p);

    @POST("garbageTextSearch")
    @Headers("Content-Type:application/json;charset=UTF-8")
    @FormUrlEncoded
    Call<Garbage_info> getGarbageInfoByText(@Query("timestamp") long timestamp,
                                            @QueryMap Map<String, String> q, @FieldMap Map<String, String> p);

}
