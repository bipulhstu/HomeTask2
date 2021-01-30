package com.bipulhstu.hometask2.viewModel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bipulhstu.hometask2.model.UserListResponse;
import com.bipulhstu.hometask2.retrofit.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//viewModel will get data from the repository and ready to show it to the customer/client side
public class UserListViewModel extends ViewModel {
    private final MutableLiveData<UserListResponse> userList;

    public UserListViewModel() {
        userList = new MutableLiveData<>();
    }


    public MutableLiveData<UserListResponse> getUserList() {
        if (userList != null) {
            return userList;
        }
        return null;
    }

    //for get user list
    public void apiCallForGetUserList(Activity context, String token) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        Call<UserListResponse> call = RetrofitClient.getInstance().getApi().getUserList(token);
        call.enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserListResponse> call, @NotNull Response<UserListResponse> response) {

                if (response.isSuccessful()) {
                    userList.postValue(response.body());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserListResponse> call, @NotNull Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }

}