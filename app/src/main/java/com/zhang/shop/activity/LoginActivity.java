package com.zhang.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.zhang.shop.R;
import com.zhang.shop.model.CachePreferences;
import com.zhang.shop.model.User;
import com.zhang.shop.mvp.presenter.LoginPresenter;
import com.zhang.shop.mvp.view.LoginView;
import com.zhang.shop.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView {

    @BindView(R.id.et_username)
    EditText et_userName;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;

    private ActivityUtils activityUtils;
    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);

        init();
    }


    private void init() {
        //给EditText添加监听
        et_userName.addTextChangedListener(textWatcher);
        et_pwd.addTextChangedListener(textWatcher);
        //从本地得到登录过的用户信息
        CachePreferences.init(this);//初始化
        User user = CachePreferences.getUser();
        et_userName.setText(user.getData().getUsername());
        et_pwd.setText(user.getData().getPassword());
    }


    //EditText监听
    private TextWatcher textWatcher = new TextWatcher() {
        //这里的s表示改变之前的内容，通常start和count组合，可以在s中读取本次改变字段中被改变的内容。
        //而after表示改变后新的内容的数量
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        //这里的s表示改变之后的内容，通常start和count组合，可以在s中读取本次改变字段中新的内容。
        //而before表示被改变的内容的数量。
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        //表示最终内容
        @Override
        public void afterTextChanged(Editable s) {
            username = et_userName.getText().toString();
            password = et_pwd.getText().toString();
            //判断用户民和密码是否为空
            boolean canLogin = !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password));
            btn_login.setEnabled(canLogin);
        }
    };

    //登录按钮点击事件
    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                // TODO: 2017/6/23 0023 执行登录的网络请求
                //业务：登录操作
                presenter.login(username, password);
                break;
            case R.id.tv_register:
                // TODO: 2017/6/23 0023 跳转注册页面
                activityUtils.showToast("跳转注册页面");
                startActivityForResult(new Intent(this, Activity_Register.class), 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            et_userName.setText(data.getStringExtra("name"));
            et_pwd.setText(data.getStringExtra("pwd"));
        }

    }

    /**
     * 得到中间人Presenter
     *
     * @return
     */
    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void showPrb() {
        activityUtils.hideSoftKeyboard();
        progressBar2.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePrb() {
        progressBar2.setVisibility(View.INVISIBLE);
    }

    //登录成功
    @Override
    public void loginSuccess(User user) {

        CachePreferences.init(this);//初始化
        CachePreferences.clearAllData();//清除之前的用户信息
        CachePreferences.setUser(user);//登录成功保存用户信息
        activityUtils.startActivity(MainActivity.class);
        finish();

    }

    @Override
    public void loginFailed() {

    }


    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
