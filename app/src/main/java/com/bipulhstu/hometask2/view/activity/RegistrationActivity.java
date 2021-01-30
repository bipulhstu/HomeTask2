package com.bipulhstu.hometask2.view.activity;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bipulhstu.hometask2.R;
import com.bipulhstu.hometask2.databinding.ActivityRegistrationBinding;
import com.bipulhstu.hometask2.validation.DataValidation;
import com.bipulhstu.hometask2.validation.MyApplication;
import com.bipulhstu.hometask2.viewModel.RegistrationViewModel;
import com.shashank.sony.fancytoastlib.FancyToast;

public class RegistrationActivity extends AppCompatActivity {
    private String userName, userEmail, userPhone, userPassword, userConfirmPassword, token;
    private RegistrationViewModel registrationViewModel;
    private ProgressDialog progressDialog;
    ActivityRegistrationBinding binding;
    private DataValidation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        registrationViewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);
        token = MyApplication.preferenceInfo.getAccessToken();
        initComponent();

        binding.btnRegister.setOnClickListener(view -> {
            //validate user register information
            if (userRegisterInfoValidation())
                userRegistrationRequest();
        });
    }

    //initialize all component
    public void initComponent() {

        validation = new DataValidation();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getResources().getString(R.string.proTitle));
        progressDialog.setMessage(getResources().getString(R.string.proMessage));
        progressDialog.setCancelable(false);
    }

    //validate register information
    //password must be follow password policy[must in 6 to 16 character]
    //email must be a valid email
    //phone number must be a valid phone number
    //Must be Not empty
    private boolean userRegisterInfoValidation() {
        userName = binding.edtUserName.getText().toString().trim();
        userEmail = binding.edtUserEmail.getText().toString().trim();
        userPhone = binding.edtUserPhone.getText().toString().trim();
        userPassword = binding.edtPassword.getText().toString().trim();
        userConfirmPassword = binding.edtConfirmPassword.getText().toString().trim();
        final Drawable iconInvalid = getResources().getDrawable(R.drawable.icon_error);//invalid icon
        iconInvalid.setBounds(0, 0, iconInvalid.getIntrinsicWidth(), iconInvalid.getIntrinsicHeight());

        if (TextUtils.isEmpty(userName)) {
            binding.edtUserName.setError("Invalid user name", iconInvalid);//show invalid warning
            binding.edtUserName.requestFocus();
            return false;
        }
        if ((!validation.emailValidation(userEmail)) || TextUtils.isEmpty(userEmail)) {
            binding.edtUserEmail.setError("Invalid user email", iconInvalid);//show invalid warning
            binding.edtUserEmail.requestFocus();
            return false;
        }
        if ((!validation.phoneNumberValidation(userPhone)) || TextUtils.isEmpty(userPhone)) {
            binding.edtUserPhone.setError("Invalid phone number", iconInvalid);//show invalid warning
            binding.edtUserPhone.requestFocus();
            return false;
        }

        if (!validation.passwordValidation(userPassword) || TextUtils.isEmpty(userPassword)) {
            binding.edtPassword.setError("Invalid password", iconInvalid);
            binding.edtPassword.requestFocus();
            return false;
        }
        if (!validation.passwordValidation(userConfirmPassword) || TextUtils.isEmpty(userConfirmPassword)) {
            binding.edtConfirmPassword.setError("Invalid confirm password", iconInvalid);
            binding.edtConfirmPassword.requestFocus();
            return false;
        }
        if (!userPassword.equals(userConfirmPassword)) {
            binding.edtConfirmPassword.setError("Password didn't match", iconInvalid);
            binding.edtConfirmPassword.requestFocus();
            return false;
        }

        return true;

    }


    //make user log in request
    //Parameter valid password,email or phone number and access token
    //Handle log in response
    private void userRegistrationRequest() {
        //if internet connection enable
        if (!MyApplication.internetConnection.isOnline()) {
            MyApplication.unableToAccessInternet(this);
            return;
        }
        progressDialog.show();

        registrationViewModel.sendRegistrationRequest(this, token, userName, userEmail, userPhone, userPassword, userConfirmPassword);
        registrationViewModel.getRegistrationResponse().observe(this, userRegistrationResponse -> {
            if (userRegistrationResponse.getSuccess()) {
                //Registration successfull and show toast
                FancyToast.makeText(RegistrationActivity.this, "Registration successfull",
                        Toast.LENGTH_LONG, FancyToast.SUCCESS, false).show();

                finish();
            } else {
                FancyToast.makeText(RegistrationActivity.this, "Registration failed",
                        Toast.LENGTH_LONG, FancyToast.ERROR, false).show();

            }
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