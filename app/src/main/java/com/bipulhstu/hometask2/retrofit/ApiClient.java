package com.bipulhstu.hometask2.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//For Any Network We need Connection : Here we can make Connection
public class ApiClient {
    //base URL
    public static final String BASE_URL = "https://demoapi.hovata.com/public/api/v1/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
