package com.lcwy.careerguidance.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.lcwy.careerguidance.MainActivity;
import com.lcwy.careerguidance.R;

/**
 * Created by LMX on 2018/2/4.
 */

public class WelcomeActivity extends Activity {

    private boolean isFirstIn = false;
    private static final int TIME = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;


    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg){
          switch (msg.what){
              case GO_HOME:
                  goHome();
                  break;
              case GO_GUIDE:
                  goGuide();
                  break;
          }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init(){
        SharedPreferences preferences = getSharedPreferences("lcwy",MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn",true);//获取isFirstIn键对应的值，如果没有默认为ture，意为第一次进入
        if(!isFirstIn){
            mHandler.sendEmptyMessageDelayed(GO_HOME,TIME);//不是第一次进入
        }else{
            mHandler.sendEmptyMessageDelayed(GO_GUIDE,TIME);//是第一次进入
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstIn",false);
            editor.commit();
        }
    }
    //从当前界面跳转到主界面
    private void goHome(){
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //从当前界面跳转到引导界面
    private void goGuide(){
        Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();
    }
}
