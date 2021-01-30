package com.bipulhstu.hometask2.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//How repository will product the data from the server and how it will supply to the viewmodel
public class RetrofitClient {
    private static final String BASE_URL = "https://demoapi.hovata.com/public/api/v1/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public ApiConfig getApi() {
        return retrofit.create(ApiConfig.class);
    }

}