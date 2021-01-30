package com.bipulhstu.hometask2.retrofit;


import com.bipulhstu.hometask2.model.UserListResponse;
import com.bipulhstu.hometask2.model.UserLoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
//We need api interface to end to end point connection and method
public interface ApiConfig {

    @FormUrlEncoded
    @POST("user/login")
    Call<UserLoginResponse> userLoginRequest(
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("f_client_id") int f_client_id
    );

    @FormUrlEncoded
    @POST("user/registration")
    Call<UserLoginResponse> userRegistrationRequest(
            @Query("token") String token,
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("password_confirmation") String confirm_password
    );

    //@FormUrlEncoded
    @PUT("user/{id}")
    Call<UserLoginResponse> updateUserData(
            @Path("id") String id,
            @Query("token") String token,
            @Query("name") String name,
            @Query("email") String email,
            @Query("mobile") String mobile,
            @Query("status") String status
    );

    //@FormUrlEncoded
    @GET("user/list")
    Call<UserListResponse> getUserList(
            @Query("token") String token
    );


}
