package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.ui.fragment.AttentionFragment;

import butterknife.BindView;

/**
 * Created by RuiFight-3 on 2018/5/12.
 */

public class AttentionActivity  extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    //数据源
    private String[] titles = {"已接受","已完成"};
    private int[] state = {1,2};
    @Override
    protected int setLayout() {
        return R.layout.activity_attention;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("关注列表");

    }

    @Override
    protected void initData() {

        initViewPager();
    }

    public void initViewPager(){

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewpager.setAdapter(myPagerAdapter);

        tabLayout.setupWithViewPager(viewpager);



    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
                default:
                    break;
        }
    }
    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            AttentionFragment attentionFragment= new AttentionFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("state",state[position]);
            attentionFragment.setArguments(bundle);
            return attentionFragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
    //解决getActivity()为空的问题
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);   //注释掉该方法， 即不保存状态
    }
}
