package com.bipulhstu.hometask2.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bipulhstu.hometask2.R;
import com.bipulhstu.hometask2.databinding.ActivityLoginBinding;
import com.bipulhstu.hometask2.validation.DataValidation;
import com.bipulhstu.hometask2.validation.MyApplication;
import com.bipulhstu.hometask2.viewModel.LoginViewModel;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private String userName, userPassword;
    private DataValidation validation;
    private ProgressDialog progressDialog;

    //View binding layout initialization
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        initComponent();

        binding.btnLogin.setOnClickListener(view -> {
            //validate user log in information
            if (userLogInInfoValidation())
                userLogInRequest();
        });

    }

    //first check if an user is logged in not. If logged in then take him to user list
    @Override
    protected void onStart() {
        super.onStart();
        if (MyApplication.preferenceInfo.getLogInStatus())//if user logged in
        {
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }
    }

    //initialize all component
    public void initComponent() {

        validation = new DataValidation();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getResources().getString(R.string.proTitle));
        progressDialog.setMessage(getResources().getString(R.string.proMessage));
        progressDialog.setCancelable(false);
    }


    //validate user log in information
    //password must be follow password policy[must in 6 to 16 character]
    //email must be a valid email
    //phone number must be a valid phone number
    //Must be Not empty
    private boolean userLogInInfoValidation() {
        userName = binding.edtPhoneEmail.getText().toString().trim();
        userPassword = binding.edtPassword.getText().toString().trim();
        final Drawable iconInvalid = getResources().getDrawable(R.drawable.icon_error);//invalid icon
        iconInvalid.setBounds(0, 0, iconInvalid.getIntrinsicWidth(), iconInvalid.getIntrinsicHeight());

        if ((!validation.phoneNumberValidation(userName)) || TextUtils.isEmpty(userName)) {
            binding.edtPhoneEmail.setError("Invalid phone number", iconInvalid);//show invalid warning
            binding.edtPhoneEmail.requestFocus();
            return false;
        }

        if (!validation.passwordValidation(userPassword) || TextUtils.isEmpty(userPassword)) {
            binding.edtPassword.setError("Invalid password", iconInvalid);
            binding.edtPassword.requestFocus();
            return false;
        }

        return true;

    }


    //make user log in request
    //Parameter valid username and password
    //Handle log in response
    private void userLogInRequest() {
        if (!MyApplication.internetConnection.isOnline())//if internet connection enable
        {
            MyApplication.unableToAccessInternet(this);
            return;
        }
        progressDialog.show();

        loginViewModel.sendLoginRequest(this, userName, userPassword, 1);
        loginViewModel.getLoginResponse().observe(this, userLoginResponse -> {
            //after successfully login save logged user data to local database
            Map<String, String> map = new HashMap<>();
            map.put("token", userLoginResponse.getUserLoginData().getToken());
            map.put("userId", String.valueOf(userLoginResponse.getUserLoginData().getCompanyData().getId()));
            map.put("company_id", String.valueOf(userLoginResponse.getUserLoginData().getCompanyData().getCompanyId()));
            map.put("name", userLoginResponse.getUserLoginData().getCompanyData().getName());
            map.put("mobile", userLoginResponse.getUserLoginData().getCompanyData().getMobile());
            map.put("address", userLoginResponse.getUserLoginData().getCompanyData().getAddress());
            map.put("email", userLoginResponse.getUserLoginData().getCompanyData().getEmail());
            map.put("opening_balance", userLoginResponse.getUserLoginData().getCompanyData().getOpeningBalance().toString());

            MyApplication.preferenceInfo.currentUsreInformation(map);//store current user information
            MyApplication.preferenceInfo.userLogStatus(true);

            FancyToast.makeText(LoginActivity.this, "Log in success",
                    Toast.LENGTH_LONG, FancyToast.SUCCESS, false).show();

            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
            progressDialog.dismiss();

        });
    }

    //functions for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();

    }

}
