package com.mredrock.cyxbs.freshman.CampusStrategy.Domitory;

import android.content.Intent;
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
import com.mredrock.cyxbs.freshman.CampusStrategy.CampusMainActivity;
import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent.BGPercentFragment;
import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DataDetailActivity;
import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DataTabEntity;
import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DiffcultColumn.DiffcultColumnFragment;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBERecyAdapter;

import java.util.ArrayList;

public class DomitoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    String type = "";
    CommonTabLayout tabLayout;
    ViewPager viewPager;
    ImageView backView;
    String titles[] = {"知行苑", "宁静苑",  "兴业苑","明理苑"};
    ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.freshman_campus_domitory_activity);
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        /*setSupportActionBar(toolbar);*/
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        for (int i = 0; i < titles.length; i++) {
            mTabEntities.add(new DataTabEntity(titles[i]));
            mFragments.add(new DomitoryFragment(titles[i],this,titles[i]));
        }
        tabLayout.setTabData(mTabEntities);
        viewPager.setAdapter(new DomitoryActivity.MyPagerAdapter(getSupportFragmentManager()));
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

    private void initView() {
        backView = findViewById(R.id.freshman_campus_domitory_back_imageview);
        toolbar = findViewById(R.id.freshman_campus_domitory_toolbar);
        tabLayout = findViewById(R.id.freshman_campus_domitory_tablayout);
        viewPager = findViewById(R.id.freshman_campus_domitory_viewpager);
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
