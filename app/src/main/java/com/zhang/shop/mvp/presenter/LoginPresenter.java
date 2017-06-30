package com.zhang.shop.mvp.presenter;


import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.zhang.shop.UICallBack;
import com.zhang.shop.model.User;
import com.zhang.shop.mvp.view.LoginView;
import com.zhang.shop.util.LoginAndRegistered_Util;

import java.io.IOException;

import okhttp3.Call;

/**
 * 作者：王小超
 * 邮箱：wxcican@qq.com
 */

public class LoginPresenter extends MvpNullObjectBasePresenter<LoginView> {

    // TODO: 2017/6/28 0028 环信相关

    private Call call;

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (call != null) call.cancel();
    }

    public void login(final String username, String password) {
        getView().showPrb();
        call = LoginAndRegistered_Util.get_LoginAndRegistered_Util().login(username, password);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {
                getView().hidePrb();
                getView().showMsg(e.getMessage());
            }

            @Override
            public void onResponseUI(Call call, String json) {
                getView().hidePrb();
                User user = new Gson().fromJson(json, User.class);
                if (user.getCode() == 1) {
                    // TODO: 2017/6/28 0028 保存用户信息到本地配置
                    getView().showMsg("登录成功");
                    getView().loginSuccess(user);
                } else if (user.getCode() == 2) {
                    getView().showMsg(user.getMsg());
                    getView().loginFailed();
                } else {
                    getView().showMsg("未知错误！");
                }
            }
        });
    }
}
