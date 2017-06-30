package com.zhang.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.zhang.shop.R;
import com.zhang.shop.mvp.presenter.RegisterPresenter;
import com.zhang.shop.mvp.view.RegisterView;
import com.zhang.shop.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 管理员账户 on 2017/6/23.
 */

public class Activity_Register extends MvpActivity<RegisterView, RegisterPresenter>
        implements RegisterView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.et_pwdAgain)
    EditText et_pwdAgain;
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);

        //给左上角加上一个返回图标
        setSupportActionBar(toolbar);
        //toolbar显示返回按钮,但是点击效果需要“实现菜单点击事件”
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //toolbar显示返回按钮,但是点击效果需要“实现菜单点击事件”
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //如果点击的是返回，则finish
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.toolbar, R.id.et_username, R.id.et_pwd, R.id.et_pwdAgain, R.id.btn_register})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                break;
            case R.id.et_username:
                break;
            case R.id.et_pwd:
                break;
            case R.id.et_pwdAgain:
                break;
            case R.id.btn_register://注册
                final String et_usernameString = et_username.getText().toString();
                final String et_pwdString = et_pwd.getText().toString();
                String et_pwdAgainString = et_pwdAgain.getText().toString();

                //判断输入框是否为空
                boolean isNull = !et_usernameString.equals("") && !et_pwdString.equals("")
                        && !et_pwdAgainString.equals("");
                //判断两次输入的密码是否相同
                boolean isEqual = et_pwdString.equals(et_pwdAgainString);

                if (isNull && isEqual) {
                    //业务：网络请求完成注册
                    presenter.register(et_usernameString, et_pwdString);

                }
                break;

        }
    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override//显示进度条
    public void showPrb() {
        //关闭软键盘
        activityUtils.hideSoftKeyboard();
        //进度条
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override//隐藏进度条
    public void hidePrb() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override //注册成功
    public void registerSuccess() {
        //成功转到主页
        Intent intent = new Intent();
        intent.putExtra("name", et_username.getText().toString());
        intent.putExtra("pwd", et_pwd.getText().toString());
        setResult(1, intent);
        finish();
    }

    @Override //注册失败
    public void registerFailed() {
        et_username.setText("");
        et_pwd.setText("");
        et_pwdAgain.setText("");
    }

    @Override//提示信息
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override//用户名或密码不对时提示用户
    public void showUserPasswordError(String msg) {

    }
}
