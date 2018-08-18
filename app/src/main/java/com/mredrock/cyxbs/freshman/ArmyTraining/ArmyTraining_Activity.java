package com.mredrock.cyxbs.freshman.ArmyTraining;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.mredrock.cyxbs.freshman.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by 郝书逸 on 2018/8/9.
 */

public class ArmyTraining_Activity extends AppCompatActivity implements IArmyTrainingView{
    private ViewPager viewpager;
    private TabLayout tablayout;
    private Toolbar toolbar;
    private Button back;
    static FragmentManager fragmentManager;
    private List<Fragment> fragments;
    private pagerAdapter adapter;
    private  String[]titles ={"军训风采","小贴士"};
    static ArmyTraining_Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //安卓版本大于4.4设置状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //绑定toolbar
        setSupportActionBar(toolbar);
        setContentView(R.layout.freshman_armytraining_main);
        this.back=(Button)findViewById(R.id.back_armytraining_main);
        this.tablayout = (TabLayout) findViewById(R.id.tablayout);
        this.viewpager = (ViewPager) findViewById(R.id.weekviewpager);
        this.toolbar=(Toolbar)findViewById(R.id.toolbar_armytraining);
        initData();
    }
    private void initData(){
        fragments = new ArrayList<>();
        ArmyTraining_Fragment_Mien armyTraining_fragment_mien=ArmyTraining_Fragment_Mien.newInstance();
        ArmyTraining_Fragment_Tip armyTraining_fragment_tip=ArmyTraining_Fragment_Tip.newInstance();
        fragments.add(armyTraining_fragment_mien);
        fragments.add(armyTraining_fragment_tip);
        presenter=new ArmyTraining_Presenter(this,armyTraining_fragment_mien,armyTraining_fragment_tip);
        fragmentManager=getSupportFragmentManager();
        adapter = new pagerAdapter(fragmentManager,fragments);
        adapter.setTitles(titles);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
        reflex(tablayout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this,errorMessage,LENGTH_SHORT).show();
    }

    @Override
    public void show(ArmyTraining_Bean_Mien bean_mien, ArmyTraining_Bean_Tip bean_tip) {

    }
    //还在想办法改变tablayout的线条长度，暂时没有解决方法

    public void reflex(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                     LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                    int dp10 = dip2px(tabLayout.getContext(), 55);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        int width = 0;
                        width = mTextView.getWidth()-120;
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width=mTextView.getMeasuredWidth();
                        }
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();

                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();

                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
