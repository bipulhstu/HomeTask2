package com.bipulhstu.hometask2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserLoginData {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;
    @SerializedName("F_CLIENT_ID")
    @Expose
    private String fCLIENTID;
    @SerializedName("Company")
    @Expose
    private CompanyData companyData;
    @SerializedName("permissions")
    @Expose
    private List<String> permissions = null;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getfCLIENTID() {
        return fCLIENTID;
    }

    public void setfCLIENTID(String fCLIENTID) {
        this.fCLIENTID = fCLIENTID;
    }

    public CompanyData getCompanyData() {
        return companyData;
    }

    public void setCompanyData(CompanyData companyData) {
        this.companyData = companyData;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
