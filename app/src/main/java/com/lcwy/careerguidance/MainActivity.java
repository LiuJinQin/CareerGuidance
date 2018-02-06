package com.lcwy.careerguidance;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lcwy.careerguidance.fragment.HomeFragment;
import com.lcwy.careerguidance.fragment.LeadFragment;
import com.lcwy.careerguidance.fragment.MineFragment;
import com.lcwy.careerguidance.fragment.RecommendFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private BottomNavigationBar bottomNavigationBar;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Log.d("MainActivity","onCreate:主页加载完毕");
    }
    // 初始化
    private void init(){

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);//初始化
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//设置模式
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);//设置背景样式
        bottomNavigationBar//添加选项
                .addItem(new BottomNavigationItem(R.drawable.ic_main_home_selected, R.string.home)
                        .setInactiveIconResource(R.drawable.ic_main_home_normal)
                        .setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_lead_selected, R.string.lead)
                        .setInactiveIconResource(R.drawable.ic_main_lead_normal)
                        .setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_recommend_selected, R.string.recommend)
                        .setInactiveIconResource(R.drawable.ic_main_recommend_normal)
                        .setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_mine_selected, R.string.mine)
                        .setInactiveIconResource(R.drawable.ic_main_mine_normal)
                        .setActiveColorResource(R.color.colorPrimary))
//                .addItem(new BottomNavigationItem(R.drawable.icon_main_category_selected, R.string.lead)
//                        .setInactiveIconResource(R.drawable.icon_main_category_normal)
//                        .setActiveColorResource(R.color.colorPrimary))
//                .addItem(new BottomNavigationItem(R.drawable.icon_main_discover_selected, R.string.recommend)
//                        .setInactiveIconResource(R.drawable.icon_main_discover_normal)
//                        .setActiveColorResource(R.color.colorPrimary))
//                .addItem(new BottomNavigationItem(R.drawable.icon_main_mine_selected, R.string.mine)
//                        .setInactiveIconResource(R.drawable.icon_main_mine_normal)
//                        .setActiveColorResource(R.color.colorPrimary))
                .initialise();
        fragments = getFragments();
        setDefaultFragment(fragments.get(0));
        bottomNavigationBar.setTabSelectedListener(this);
    }

    //获取碎片
    private ArrayList<Fragment> getFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new LeadFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new MineFragment());
//        fragments.add(MusicFragment.newInstance("Music"));
//        fragments.add(TvFragment.newInstance("Movies & TV"));
//        fragments.add(GameFragment.newInstance("Games"));
        return fragments;
    }

    //设置默认碎片为HomeFragment
    private void setDefaultFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();//获得管理对象：Activity管理fragment
        FragmentTransaction transaction = fm.beginTransaction();//获得事务对象：增删fragment
        transaction.replace(R.id.framelayout,fragment);//向容器内添加碎片，这里指定碎片为HomeFragment
        //transaction.replace(R.id.home_layout,HomeFragment.instantiate(this,"首页"));//activity_main.xml里面的FrameLayout，添加homefragment碎片
        transaction.commit();//提交事务
    }


    //为BottomNavigigationBar设置监听选项点击事件
    @Override
    public void onTabSelected(int position) {//未选中 -> 选中

        if(fragments != null){
            if(position < fragments.size()){
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()){//如果该Fragment对象被添加到了它的Activity中，那么它返回true，否则返回false。
                    ft.replace(R.id.framelayout,fragment);//替换容器中已经存在的碎片，先remove再add
                    Log.d("MainActivity", "onTabSelected() replace: " + "position = [" + position + "]");
                }else{
                    ft.add(R.id.framelayout,fragment);//添加一个碎片到容器中
                    Log.d("MainActivity", "onTabSelected() add: " + "position = [" + position + "]");
                }
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {//选中 -> 未选中
        Toast.makeText(this, "unselected"+position, Toast.LENGTH_SHORT).show();
        if(fragments != null){
            if(position < fragments.size()){
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);//移除一个已经存在的碎片
                Log.d("MainActivity", "onTabUnSelected: remove"+position);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {//选中 -> 选中
    }
}

