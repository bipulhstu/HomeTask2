package com.bipulhstu.hometask2.database;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;


/**
 * @ store user necessary information
 * @ store cart data
 * @ return information where needed
 * @ store log in information and response
 * @ User Log in status
 */

public class SharedPreferenceInfo {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String IS_LOG_IN = "isLogin";
    private static final String USER_INFO = "userInfo";
    private static final String TOTAL_NUM_OF_CART_ITEM = "totalCartItem";
    private static final String TOTAL_NUM_OF_WISHLIST_ITEM = "totalWishlistItem";

    public SharedPreferenceInfo(Context context) {
        this.context = context;
    }


    /**
     * @ store user log in status[boolean value]
     * @ To identify is user log in or not ??
     */
    public void userLogStatus(boolean flag) {
        sharedPreferences = context.getSharedPreferences(IS_LOG_IN, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean("login", flag);
        editor.apply();
    }

    /**
     * @ Get User log in status
     * @ If user logged in return true otherwise false
     */
    public boolean getLogInStatus() {
        sharedPreferences = context.getSharedPreferences(IS_LOG_IN, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login", false);
    }

    /**
     * user information map
     *
     * @param info log in information like user id,profile id,user name,full name
     */
    public void currentUsreInformation(Map<String, String> info) {
        sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putString("token", info.get("token"));
        editor.putString("userId", info.get("userId"));
        editor.putString("company_id", info.get("company_id"));
        editor.putString("name", info.get("name"));
        editor.putString("mobile", info.get("mobile"));
        editor.putString("address", info.get("address"));
        editor.putString("email", info.get("email"));
        editor.putString("opening_balance", info.get("opening_balance"));


        editor.apply();
    }




    /**
     * @ Get User access token
     * @ use this in token every requests
     */
    public String getAccessToken() {
        sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", null);
    }


    /**
     * @ Get user id
     * @
     */
    public String getCurrentUserId() {
        sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString("userId", null);
    }

    /**
     * @ Get company id
     * @
     */
    public String getCurrentCompanyId() {
        sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString("company_id", null);
    }

    /**
     * @ Get company name
     * @ use this in token every requests
     */
    public String getCompanyName() {
        sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString("name", null);
    }

    /**
     * @ Get company mobile number
     * @ use this in token every requests
     */
    public String getCompanyMobile() {
        sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString("mobile", null);
    }

    /**
     * @ Get company address
     * @ use this in token every requests
     */
    public String getCompanyAddress() {
        sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString("address", null);
    }

    /**
     * @ Get company email
     * @ use this in token every requests
     */
    public String getCompanyEmail() {
        sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString("email", null);
    }

    /**
     * @ Get company opening balance
     * @
     */
    public String getCompanyOpeningBalance() {
        sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString("opening_balance", null);
    }
}
