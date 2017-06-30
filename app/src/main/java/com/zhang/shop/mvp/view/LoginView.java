package com.zhang.shop.mvp.view;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.zhang.shop.model.User;

/**
 * 作者：王小超
 * 邮箱：wxcican@qq.com
 */

public interface LoginView extends MvpView{

    void showPrb();

    void hidePrb();

    void loginFailed();

    void loginSuccess(User user);

    void showMsg(String msg);
}
