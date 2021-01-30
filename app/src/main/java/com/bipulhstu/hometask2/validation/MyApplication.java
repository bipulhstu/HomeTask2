package com.bipulhstu.hometask2.validation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bipulhstu.hometask2.R;
import com.bipulhstu.hometask2.database.SharedPreferenceInfo;
import com.bipulhstu.hometask2.view.activity.LoginActivity;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Objects;


public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    public static CheckInternetConnection internetConnection;
    public static SharedPreferenceInfo preferenceInfo;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    //initialize object
    private void initComponent() {
        internetConnection = new CheckInternetConnection(this);
        preferenceInfo = new SharedPreferenceInfo(this);
    }


    //warning alert dialog
    public static void unableToAccessInternet(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        @SuppressLint("InflateParams")
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.alert_dialog_layout, null);
        //Set the view
        builder.setView(view);
        TextView tvTitle, tvMessage;
        ImageView imageIcon = view.findViewById(R.id.img_icon);
        tvMessage = view.findViewById(R.id.tv_message);
        tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText(context.getResources().getString(R.string.noInternetTitle));
        tvMessage.append(context.getResources().getString(R.string.noInternetMsg));
        imageIcon.setImageDrawable(context.getDrawable(R.drawable.icon_no_internet_connection));
        Button bOk = view.findViewById(R.id.btn_ok);
        AlertDialog alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        bOk.setOnClickListener(v -> alertDialog.dismiss());
        if (alertDialog.getWindow() != null) {
            //for show sliding animation in alert dialog
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        }
        alertDialog.show();
    }

    public static void logoutCurrentUser(Activity context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        @SuppressLint("InflateParams")
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.alert_dialog_layout, null);
        //Set the view
        builder.setView(view);
        TextView tvTitle, tvMessage;
        LinearLayout linearLayout = view.findViewById(R.id.ln_button_layout);
        linearLayout.setVisibility(View.VISIBLE);
        CircularImageView imageIcon = view.findViewById(R.id.img_icon);
        tvMessage = view.findViewById(R.id.tv_message);
        tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText(context.getResources().getString(R.string.logoutExitTitle));
        tvMessage.append(context.getResources().getString(R.string.logout_Text));
        imageIcon.setImageDrawable(context.getDrawable(R.drawable.photo));
        Button bOk = view.findViewById(R.id.btn_ok);
        bOk.setVisibility(View.GONE);
        Button bPos, bNeg;
        bNeg = view.findViewById(R.id.btn_neg);
        bPos = view.findViewById(R.id.btn_pos);

        android.app.AlertDialog alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        if (alertDialog.getWindow() != null) {
            //for show sliding animation in alert dialog
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingUpAnimation;
        }
        alertDialog.show();

        bNeg.setOnClickListener(v -> alertDialog.dismiss());
        bPos.setOnClickListener(v -> {
            ((Activity) context).runOnUiThread(() -> {
                MyApplication.preferenceInfo.userLogStatus(false);
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                context.finish();
                alertDialog.dismiss();
            });

        });
    }


    //close specific activity from any activity
    public static void closeActivity(Activity context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        context.finish();
    }


    //reload current activity by swipe refresh layout
    public static void reloadPage(final SwipeRefreshLayout refreshLayout, Activity activity, final Class<?> nameOfClass) {
        refreshLayout.setOnRefreshListener(() -> {
            refreshLayout.setRefreshing(true);
            (new Handler()).postDelayed(() -> {
                refreshLayout.setRefreshing(false);
                activity.startActivity(new Intent(activity, nameOfClass));
                activity.finish();
            }, 2500);
        });
    }


    //Application exit warning
    //If press yes, terminate app
    //If press No, AlertDialog dismiss
    public static void exitWarning(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        @SuppressLint("InflateParams")
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.alert_dialog_layout, null);
        //Set the view
        builder.setView(view);
        TextView tvTitle, tvMessage;
        LinearLayout linearLayout = view.findViewById(R.id.ln_button_layout);
        linearLayout.setVisibility(View.VISIBLE);
        CircularImageView imageIcon = view.findViewById(R.id.img_icon);
        tvMessage = view.findViewById(R.id.tv_message);
        tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText(context.getResources().getString(R.string.appExitTitle));//set warning title
        tvMessage.append(context.getResources().getString(R.string.appExitMessage));//set warning message details text
        imageIcon.setImageDrawable(context.getDrawable(R.drawable.photo));//set warning image
        Button bOk = view.findViewById(R.id.btn_ok);
        bOk.setVisibility(View.GONE);
        Button bPos, bNeg;
        bNeg = view.findViewById(R.id.btn_neg);
        bPos = view.findViewById(R.id.btn_pos);

        AlertDialog alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        if (alertDialog.getWindow() != null) {
            //for show sliding animation in alert dialog
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        }
        alertDialog.show();

        bNeg.setOnClickListener(v -> alertDialog.dismiss());
        bPos.setOnClickListener(v -> ((Activity) context).finish());
    }


    //If user access token has been expired
    //This warning show will show when user access token expired
    //User must log in again after showing this warning
    public static void tokenExpireWarning(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        @SuppressLint("InflateParams")
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.alert_dialog_layout, null);
        //Set the view
        builder.setView(view);
        TextView tvTitle, tvMessage;
        ImageView imageIcon = view.findViewById(R.id.img_icon);
        tvMessage = view.findViewById(R.id.tv_message);
        tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText(context.getResources().getString(R.string.expireTokenTitle));//set warning title
        tvMessage.append(context.getResources().getString(R.string.expireTokenMsg));//set warning message details text
        imageIcon.setImageDrawable(context.getDrawable(R.drawable.icon_warning));//set warning image
        Button bOk = view.findViewById(R.id.btn_ok);
        AlertDialog alertDialog = builder.create();
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        bOk.setOnClickListener(v -> {
            userLogOut(context);
            alertDialog.dismiss();
        });
        alertDialog.show();
    }


    //Current user sign out from system
    //Remove all stored data from database base
    //Delay 1.5s to logged out
    //Set user log in status false
    public static void userLogOut(Context context) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getResources().getString(R.string.proTitle));
        progressDialog.setMessage(context.getResources().getString(R.string.proMessage));
        progressDialog.setCancelable(true);
        progressDialog.show();
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }).start();
        progressDialog.setOnDismissListener(dialog -> {
            //Note when a user logout auto clear his local information like token profile id etc
            MyApplication.preferenceInfo.userLogStatus(false);
        });
    }

}
