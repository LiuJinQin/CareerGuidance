package com.lcwy.careerguidance.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LMX on 2018/2/3.
 */

public class ViewPagerAdapter extends PagerAdapter{

    private List<View> views;
    private Context context;

    public ViewPagerAdapter(List<View> views,Context context){
        //构造函数
        this.views = views;
        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //销毁view
        ((ViewPager)container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //加载view
        ((ViewPager)container).addView(views.get(position));
        return views.get(position);
    }

    @Override
    public int getCount() {
        //返回当前views数量
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //判断当前view是不是我们需要的对象
        return (view == object);//是返回true，不是返回false
    }
}
