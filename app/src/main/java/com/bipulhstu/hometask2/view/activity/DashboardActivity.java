package com.bipulhstu.hometask2.view.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bipulhstu.hometask2.R;
import com.bipulhstu.hometask2.adapter.UserListAdapter;
import com.bipulhstu.hometask2.databinding.ActivityDashboardBinding;
import com.bipulhstu.hometask2.model.UserListData;
import com.bipulhstu.hometask2.validation.MyApplication;
import com.bipulhstu.hometask2.viewModel.UserListViewModel;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private List<UserListData> userListModels;
    private UserListViewModel userListViewModel;
    private LinearLayoutManager layoutManager;
    UserListAdapter adapter;
    String token;

    //View binding layout initialization
    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout using view binding
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //button onClick Listener using view binding
        binding.btnCreateNewUser.setOnClickListener(view -> startActivity(new Intent(this, RegistrationActivity.class)));
        binding.toolbar.logout.setOnClickListener(view -> MyApplication.logoutCurrentUser(DashboardActivity.this));
        binding.toolbar.backControl.setOnClickListener(view -> MyApplication.exitWarning(DashboardActivity.this));

        userListViewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        initComponent();

        //getUserList(token);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStart() {
        super.onStart();
        getUserList(token);
    }

    public void initComponent() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.proMessage));
        progressDialog.setTitle(getResources().getString(R.string.proTitle));
        layoutManager = new LinearLayoutManager(this);
        binding.refreshLayout.setColorSchemeResources(R.color.colorG, R.color.colorG, R.color.colorG);
        MyApplication.reloadPage(binding.refreshLayout, DashboardActivity.this, DashboardActivity.class);
        token = MyApplication.preferenceInfo.getAccessToken();
        //reload current activity
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getUserList(String token) {

        binding.userListRv.setHasFixedSize(true);
        binding.userListRv.setLayoutManager(new GridLayoutManager(this, 1));

        userListViewModel.apiCallForGetUserList(this, token);
        userListViewModel.getUserList().observe(this, userListResponse -> {
            userListModels = userListResponse.getUserData().getUserListDataList();


            //now set current all user list to view
            adapter = new UserListAdapter(this, userListModels);
            binding.userListRv.setAdapter(adapter);

        });
    }


    //exit dialog on backpressed
    @Override
    public void onBackPressed() {
        MyApplication.exitWarning(DashboardActivity.this);
    }
}