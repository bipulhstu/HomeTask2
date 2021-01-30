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

public class RegistrationViewModel extends ViewModel {
    private final MutableLiveData<UserLoginResponse> mutableLiveData;

    public RegistrationViewModel() {
        this.mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<UserLoginResponse> getRegistrationResponse() {
        return mutableLiveData;
    }

    public void sendRegistrationRequest(Activity context, String token, String username, String email, String phone, String password, String confirmPassword) {


        Call<UserLoginResponse> call = RetrofitClient.getInstance().getApi().userRegistrationRequest(token, username, email, phone, password, confirmPassword);
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
