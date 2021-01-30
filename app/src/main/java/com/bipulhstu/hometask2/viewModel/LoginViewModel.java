package com.bipulhstu.hometask2.viewModel;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bipulhstu.hometask2.model.UserLoginResponse;
import com.bipulhstu.hometask2.retrofit.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<UserLoginResponse> mutableLiveData;

    public LoginViewModel() {
        this.mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<UserLoginResponse> getLoginResponse() {
        return mutableLiveData;
    }

    public void sendLoginRequest(Activity context, String mobile, String password, int f_client_id) {


        Call<UserLoginResponse> call = RetrofitClient.getInstance().getApi().userLoginRequest(mobile, password, f_client_id);
        call.enqueue(new Callback<UserLoginResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NotNull Call<UserLoginResponse> call, @NotNull Response<UserLoginResponse> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserLoginResponse> call, @NotNull Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }
}
