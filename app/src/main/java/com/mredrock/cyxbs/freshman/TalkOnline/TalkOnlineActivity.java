package com.mredrock.cyxbs.freshman.TalkOnline;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent.BGPercentFragment;
import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DataDetailActivity;
import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DataTabEntity;
import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DiffcultColumn.DiffcultColumnFragment;
import com.mredrock.cyxbs.freshman.R;

import java.util.ArrayList;

public class TalkOnlineActivity extends AppCompatActivity {

    Toolbar toolbar;
    CommonTabLayout tabLayout;
    ViewPager viewPager;
    ImageView backView;
    ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    String[] titles  = {"学院群","老乡群"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.freshman_talkonline_main_activity);

        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //绑定toolbar
        /*setSupportActionBar(toolbar);*/
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mTabEntities.add(new DataTabEntity(titles[0]));
        mTabEntities.add(new DataTabEntity(titles[1]));
        mFragments.add(new TalkOnlineFragment(this,"学校群"));
        mFragments.add(new TalkOnlineFragment(this,titles[1]));
        tabLayout.setTabData(mTabEntities);
        viewPager.setAdapter(new TalkOnlineActivity.MyPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                tabLayout.setCurrentTab(position);
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void initView(){
        backView = findViewById(R.id.freshman_talkonline_back_imageview);
        toolbar = findViewById(R.id.freshman_talkonline_main_toolbar);
        tabLayout = findViewById(R.id.freshman_talkonline_main_main_tablayout);
        viewPager = findViewById(R.id.freshman_talkonline_main_viewpager);

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
