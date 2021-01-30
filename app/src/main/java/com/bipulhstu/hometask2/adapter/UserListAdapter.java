package com.bipulhstu.hometask2.adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bipulhstu.hometask2.model.UserListData;
import com.bipulhstu.hometask2.view.activity.UpdateUserActivity;
import com.bipulhstu.hometask2.R;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<UserListData> userListModels;

    // Constructor
    public UserListAdapter(Context context, List<UserListData> userListModels) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.userListModels = userListModels;
    }

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user_list_item, parent, false);
        return new UserListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {
        UserListData model = userListModels.get(position);
        holder.setData(model);
        holder.onClickListener();
    }

    @Override
    public int getItemCount() {
        return userListModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView userId, userName, userEmail, userPhone;
        private final ConstraintLayout constraintLayout;
        private UserListData currentModel;
        private final ProgressDialog progressDialog;

        private ViewHolder(View view) {
            super(view);
            constraintLayout = view.findViewById(R.id.cl);
            userId = view.findViewById(R.id.userId);
            userName = view.findViewById(R.id.userName);
            userEmail = view.findViewById(R.id.userEmail);
            userPhone = view.findViewById(R.id.userPhone);
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle(context.getResources().getString(R.string.proTitle));
            progressDialog.setMessage(context.getResources().getString(R.string.proMessage));
        }

        @SuppressLint("SetTextI18n")
        private void setData(UserListData model) {
            currentModel = model;
            userId.setText(String.valueOf(model.getId()));
            userName.setText(model.getName());
            userEmail.setText(model.getEmail());
            userPhone.setText(model.getMobile());

        }


        private void onClickListener() {
            constraintLayout.setOnClickListener(UserListAdapter.ViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.cl) {
                Intent intent = new Intent(context, UpdateUserActivity.class);
                intent.putExtra("name", currentModel.getName());
                intent.putExtra("email", currentModel.getEmail());
                intent.putExtra("mobile", currentModel.getMobile());
                intent.putExtra("id", String.valueOf(currentModel.getId()));
                intent.putExtra("status", String.valueOf(currentModel.getStatus()));
                context.startActivity(intent);
            }
        }

    }
}
