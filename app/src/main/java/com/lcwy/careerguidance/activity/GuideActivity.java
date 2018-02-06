package com.lcwy.careerguidance.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.lcwy.careerguidance.MainActivity;
import com.lcwy.careerguidance.R;
import com.lcwy.careerguidance.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页-第一次进入APP时为用户做一个引导
 * Created by LMX on 2018/2/3.
 */

public class GuideActivity extends Activity {

    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViews();
        Log.d("test", "onCreate: 引导页完成");
    }

    private void initViews(){
        //加载3个view
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.guide_one,null));
        views.add(inflater.inflate(R.layout.guide_two,null));
        views.add(inflater.inflate(R.layout.guide_three,null));
        vpAdapter = new ViewPagerAdapter(views,this);
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);

        //引导页最后一页按钮点击事件，进入主页面
        btn_start = (Button) views.get(2).findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
