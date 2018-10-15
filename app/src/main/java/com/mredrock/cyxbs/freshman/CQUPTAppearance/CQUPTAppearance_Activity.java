package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Fragment_Mien;
import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Fragment_Tip;
import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Presenter;
import com.mredrock.cyxbs.freshman.ArmyTraining.pagerAdapter;
import com.mredrock.cyxbs.freshman.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by 郝书逸 on 2018/8/15.
 */

public class CQUPTAppearance_Activity extends AppCompatActivity implements ICQUPTAppearanceView{
    private CQUPTAppearance_Presenter presenter;
    private FragmentManager fragmentManager;
    private Toolbar toolbar;
    private Button back;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[]titles={"学生组织","大型活动"};

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
        setContentView(R.layout.freshman_cquptappearance_main);
        initView();
        initData();
    }

    private void initView(){
        back=findViewById(R.id.back_cquptappearance_main);
        toolbar=findViewById(R.id.toolbar_cquptappearance);
        tabLayout=findViewById(R.id.tablayout_cquptappearance_main);
        viewPager=findViewById(R.id.viewpager_cquptappearance_main);
    }
    private void initData(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        CQUPTAppearance_Fragment_Union cquptAppearance_fragment_union=CQUPTAppearance_Fragment_Union.newInstance();
        CQUPTAppearance_Fragment_Event cquptAppearance_fragment_event=CQUPTAppearance_Fragment_Event.newInstance();
        fragments.add(cquptAppearance_fragment_union);
        fragments.add(cquptAppearance_fragment_event);
        presenter=new CQUPTAppearance_Presenter(this,cquptAppearance_fragment_union,cquptAppearance_fragment_event);
        fragmentManager=getSupportFragmentManager();
        CQUPTAppearance_PagerAdapter adapter = new CQUPTAppearance_PagerAdapter(fragmentManager, fragments);
        adapter.setTitles(titles);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        reflex(tabLayout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
    }
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

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this,errorMessage,LENGTH_SHORT).show();
    }

    @Override
    public void show(CQUPTAppearance_Bean_10 bean) {

    }
}
