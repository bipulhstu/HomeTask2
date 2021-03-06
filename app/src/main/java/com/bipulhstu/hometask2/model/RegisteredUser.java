package com.bipulhstu.hometask2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisteredUser {
    @SerializedName("f_employee_id")
    @Expose
    private Object fEmployeeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("f_wings_id")
    @Expose
    private Object fWingsId;
    @SerializedName("f_client_id")
    @Expose
    private String fClientId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    public Object getFEmployeeId() {
        return fEmployeeId;
    }

    public void setFEmployeeId(Object fEmployeeId) {
        this.fEmployeeId = fEmployeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getFWingsId() {
        return fWingsId;
    }

    public void setFWingsId(Object fWingsId) {
        this.fWingsId = fWingsId;
    }

    public String getFClientId() {
        return fClientId;
    }

    public void setFClientId(String fClientId) {
        this.fClientId = fClientId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
