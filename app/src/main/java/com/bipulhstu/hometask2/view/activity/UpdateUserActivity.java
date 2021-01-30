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
import com.bipulhstu.hometask2.databinding.ActivityUpdateUserBinding;
import com.bipulhstu.hometask2.validation.DataValidation;
import com.bipulhstu.hometask2.validation.MyApplication;
import com.bipulhstu.hometask2.viewModel.UpdateUserViewModel;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Objects;

public class UpdateUserActivity extends AppCompatActivity {
    private String userName, userEmail, userPhone, userStatus, name, email, phone, code, id, token;
    private UpdateUserViewModel updateUserViewModel;
    private ProgressDialog progressDialog;
    private DataValidation validation;
    ActivityUpdateUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        updateUserViewModel = ViewModelProviders.of(this).get(UpdateUserViewModel.class);
        token = MyApplication.preferenceInfo.getAccessToken();
        initComponent();

        binding.toolbar.backControl.setOnClickListener(view -> finish());
        binding.btnUpdateUser.setOnClickListener(view -> {
            //validate user register information
            if (userProvidedDataValidation())
                userDataUpdateRequest();
        });
    }

    //initialize all component
    public void initComponent() {

        validation = new DataValidation();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getResources().getString(R.string.proTitle));
        progressDialog.setMessage(getResources().getString(R.string.proMessage));
        progressDialog.setCancelable(false);

        //get data from previous activity
        name = Objects.requireNonNull(getIntent().getExtras()).getString("name");
        email = Objects.requireNonNull(getIntent().getExtras()).getString("email");
        phone = Objects.requireNonNull(getIntent().getExtras()).getString("mobile");
        code = Objects.requireNonNull(getIntent().getExtras()).getString("status");
        id = Objects.requireNonNull(getIntent().getExtras()).getString("id");

        //set data to edittext
        binding.edtUserName.setText(name);
        binding.edtUserEmail.setText(email);
        binding.edtPhoneNumber.setText(phone);
        binding.edtStatusCode.setText(code);
    }

    //validate user log in information
    //password must be follow password policy[must in 6 to 16 character]
    //email must be a valid email
    //phone number must be a valid phone number
    //Must be Not empty
    private boolean userProvidedDataValidation() {
        userName = binding.edtUserName.getText().toString().trim();
        userEmail = binding.edtUserEmail.getText().toString().trim();
        userPhone = binding.edtPhoneNumber.getText().toString().trim();
        userStatus = binding.edtStatusCode.getText().toString().trim();
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
            binding.edtPhoneNumber.setError("Invalid phone number", iconInvalid);//show invalid warning
            binding.edtPhoneNumber.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(userStatus)) {
            binding.edtStatusCode.setError("Invalid status", iconInvalid);
            binding.edtStatusCode.requestFocus();
            return false;
        }


        return true;

    }


    //make user log in request
    //Parameter valid username, useremail, userphone, userstatus and access token
    //Handle log in response
    private void userDataUpdateRequest() {
        //if internet connection enable
        if (!MyApplication.internetConnection.isOnline()) {
            MyApplication.unableToAccessInternet(this);
            return;
        }
        progressDialog.show();

        updateUserViewModel.sendUpdateRequest(this, id, token, userName, userEmail, userPhone, userStatus);
        updateUserViewModel.getUpdatedResponse().observe(this, userUpdatedResponse -> {
            if (userUpdatedResponse.getSuccess()) {
                //if data updated successfully
                FancyToast.makeText(UpdateUserActivity.this, "Updated successfully",
                        Toast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                finish();
            } else {
                FancyToast.makeText(UpdateUserActivity.this, "Update failed",
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