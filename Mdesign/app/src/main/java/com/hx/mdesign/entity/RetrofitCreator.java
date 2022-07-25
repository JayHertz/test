package com.hx.mdesign.entity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: Hx
 * @date: 2022年03月08日 16:44
 */
public class RetrofitCreator {

    private static final String JD = "https://aiapi.jd.com/jdai/";
    private static final String WEATHER_URL = "https://v0.yiketianqi.com/";

    private static final Retrofit RetrofitForJD = new Retrofit.Builder().baseUrl(JD)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <T> T createRetrofitForJD(Class<T> retrofitClass) {
        return RetrofitForJD.create(retrofitClass);
    }

    private static final Retrofit RetrofitForWeather = new Retrofit.Builder().baseUrl(WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <T> T createRetrofitForWeather(Class<T> retrofitClass){
        return RetrofitForWeather.create(retrofitClass);
    }

}
