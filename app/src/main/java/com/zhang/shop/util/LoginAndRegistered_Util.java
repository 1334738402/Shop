package com.zhang.shop.util;


import com.zhang.shop.api.API;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by 管理员账户 on 2017/6/27.
 */

public class LoginAndRegistered_Util {
    private static LoginAndRegistered_Util loginAndRegistered_util;

    private OkHttpClient okHttpClient;

    private LoginAndRegistered_Util(){
        //初始化日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设置拦截级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                //添加日志拦截器
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public static LoginAndRegistered_Util get_LoginAndRegistered_Util(){
        if (loginAndRegistered_util == null){
            loginAndRegistered_util = new LoginAndRegistered_Util();
        }
        return loginAndRegistered_util;
    }

    //登录
    public Call login(String username, String password){
        RequestBody requestBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();

        Request request = new Request.Builder()
                .url(API.BASE_URL + API.LOGIN)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }

    //注册
    public Call register(String username, String password){
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(API.BASE_URL + API.REGISTER)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }
}
