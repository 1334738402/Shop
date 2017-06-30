package com.zhang.shop.mvp.presenter;


import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.zhang.shop.UICallBack;
import com.zhang.shop.model.User;
import com.zhang.shop.mvp.view.RegisterView;
import com.zhang.shop.util.LoginAndRegistered_Util;

import java.io.IOException;

import okhttp3.Call;


public class RegisterPresenter extends MvpNullObjectBasePresenter<RegisterView> {

    //业务：执行网络请求，完成注册
    //在特定的地方，触发相应的UI操作

    // TODO: 2017/6/28 0028 环信相关

    private Call call;

    //视图销毁，取消网络请求
    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (call != null) call.cancel();
    }

    public void register(String username, String password){
        //显示进度条
        getView().showPrb();

        call = LoginAndRegistered_Util.get_LoginAndRegistered_Util().register(username, password);
        call.enqueue(new UICallBack() {
            @Override//失败
            public void onFailureUI(Call call, IOException e) {
                //隐藏进度条
                getView().hidePrb();
                //显示异常信息
                getView().showMsg(e.getMessage());
            }

            @Override//成功
            public void onResponseUI(Call call, String json) {
                //隐藏进度条
                getView().hidePrb();
                //拿到返回结果
                User user = new Gson().fromJson(json,User.class);
                //根据不用的结果码来处理
                if (user.getCode() == 1){
                    //成功提示
                    getView().showMsg("注册成功");
                    // TODO: 2017/6/28 0028 用户信息保存到本地配置当中，自动登录
                    //执行注册成功的方法
                    getView().registerSuccess();
                } else if (user.getCode() == 2) {
                    //提示失败信息
                    getView().showMsg(user.getMsg());
                    //执行注册失败的方法
                    getView().registerFailed();
                }else{
                    getView().showMsg("未知错误！");
                }
            }
        });
    }


}
