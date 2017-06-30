package com.zhang.shop.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者：王小超
 * 邮箱：wxcican@qq.com
 */


//对用户信息本地存储
public class CachePreferences {

    private static final String NAME = "CachePreferences";
    private static final String USERNAME = "userName";
    private static final String USERPWD = "userPwd";
    private static final String USERHXID = "userHxID";
    private static final String USERUUID = "userUuid";
    private static final String USERHEADIMAGE = "userHeadImage";
    private static final String USERNICKNAME = "userNickName";

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private CachePreferences() {
    }

    //初始化方法，在程序入口进行初始化
    public static void init(Context context) {
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static void clearAllData() {
        editor.clear();//清空本地文件数据
        editor.apply();//提交
    }

    public static void setUser(User user) {
        editor.putString(USERNAME, user.getData().getUsername());
        editor.putString(USERPWD, user.getData().getPassword());
        editor.putString(USERHXID, user.getData().getNickname());
        editor.putString(USERUUID, user.getData().getOther());
//        editor.putString(KEY_USER_HEAD_IMAGE, user.getHead_Image());
//        editor.putString(KEY_USER_NICKNAME, user.getNick_name());
        editor.apply();
    }

    public static User getUser() {
        User user = new User();
        User.DataBean data=new User.DataBean();
        data.setUsername(preferences.getString(USERNAME, null));
        data.setPassword(preferences.getString(USERPWD, null));
        data.setNickname(preferences.getString(USERHXID, null));
        data.setOther(preferences.getString(USERUUID, null));
//        user.setHead_Image(preferences.getString(KEY_USER_HEAD_IMAGE, null));
//        user.setNick_name(preferences.getString(KEY_USER_NICKNAME, null));
        user.setData(data);
        return user;
    }
}
