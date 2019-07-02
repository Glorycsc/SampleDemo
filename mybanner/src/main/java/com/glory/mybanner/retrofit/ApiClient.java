package com.glory.mybanner.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by glorizz on 2018/7/17
 * Describe:
 */
public class ApiClient {
    public static Retrofit getRetorfit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        return new Retrofit.Builder()
                .baseUrl(ApiStore.API_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
