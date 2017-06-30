package com.zhang.shop;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 管理员账户 on 2017/6/27.
 */

public abstract class UICallBack implements Callback {
   private Handler handler=new Handler(Looper.getMainLooper());
    @Override
    public void onFailure(final Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailureUI(call,e);
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    onResponseUI(call,response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public abstract void onFailureUI(Call call, IOException e);

    public abstract void onResponseUI(Call call, String json);
}
