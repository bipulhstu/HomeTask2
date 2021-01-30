package com.bipulhstu.hometask2.model;

import com.google.gson.annotations.SerializedName;

public class LogInResponseModel {
    @SerializedName("message")
    private String message;
    @SerializedName("customer_login")
    private String customerLogin;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("profile_id")
    private String profileId;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("user_type")
    private String userType;
    @SerializedName("FullName")
    private String fullName;
    @SerializedName("PrimaryMobile")
    private String primaryMobile;
    @SerializedName("Email")
    private String email;
    @SerializedName("DateOfBirth")
    private String dateOfBirth;
    @SerializedName("Gender")
    private String gender;
    @SerializedName("status")
    private int status;
    @SerializedName("token")
    private String token;
    @SerializedName("customerID")
    private String customerID;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCustomerLogin() {
        return customerLogin;
    }

    public void setCustomerLogin(String customerLogin) {
        this.customerLogin = customerLogin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrimaryMobile() {
        return primaryMobile;
    }

    public void setPrimaryMobile(String primaryMobile) {
        this.primaryMobile = primaryMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}
