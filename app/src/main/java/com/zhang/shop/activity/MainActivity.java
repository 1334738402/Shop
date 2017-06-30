package com.zhang.shop.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zhang.shop.R;
import com.zhang.shop.fragment.UnLoginFragment;
import com.zhang.shop.util.ActivityUtils;
import com.zhang.shop.fragment.MeFragment;
import com.zhang.shop.fragment.ShopFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_title)
    TextView main_title;
    @BindView(R.id.main_toobar)
    Toolbar main_toobar;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottom_navigation_bar;

    private ActivityUtils activityUtils;
    //点击2次返回，退出应用程序
    private boolean isExit = false;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ShopFragment shopFragment;
    private UnLoginFragment unLoginFragment;
    private MeFragment meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        fragmentManager = getSupportFragmentManager();


        //初始化数据
        initBottomNavigationItem();
        //bottom_navigation_bar选择监听
        setTabSelectedListener();

    }

    //bottom_navigation_bar选择监听
    private void setTabSelectedListener() {
        bottom_navigation_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    //市场
                    case 0:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.ll, shopFragment);
                        fragmentTransaction.commit();
                        break;
                    //消息
                    case 1:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.ll, unLoginFragment);
                        fragmentTransaction.commit();
                        break;
                    //通讯录
                    case 2:
                        //我的
                        break;
                    case 3:
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.ll, meFragment);
                        fragmentTransaction.commit();
                        break;
                }

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    //初始化数据
    private void initBottomNavigationItem() {
        shopFragment = new ShopFragment();
        unLoginFragment = new UnLoginFragment();
        meFragment = new MeFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ll, shopFragment);
        fragmentTransaction.commit();
        bottom_navigation_bar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .initialise();
        bottom_navigation_bar.addItem(new BottomNavigationItem(R.drawable.selector_ic_shopping, "市场"))
                .addItem(new BottomNavigationItem(R.drawable.selector_ic_chat, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.selector_ic_identity, "通讯录"))
                .addItem(new BottomNavigationItem(R.drawable.selector_ic_people, "我的"))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    //如何知道用户点击了返回按钮？返回按钮点击监听
    @Override
    public void onBackPressed() {
        //点击2次返回，退出应用程序
        if (!isExit) {
            isExit = true;
            activityUtils.showToast("再摁一次退出程序！");
            //如果2s内再次点击返回则退出（如果2s内不点击返回，则设置false）
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }


}


