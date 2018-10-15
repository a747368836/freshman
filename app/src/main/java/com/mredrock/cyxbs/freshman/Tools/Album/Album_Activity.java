package com.mredrock.cyxbs.freshman.Tools.Album;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;


/**
 * Created by 郝书逸 on 2018/8/10.
 */

public class Album_Activity extends AppCompatActivity implements IAlbumView {

    protected Album_Presenter presenter;
    private Button back;
    private ViewPager viewPager;
    private String[] picurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //安卓版本大于4.4设置状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.freshman_album_activity);
        Intent intent=getIntent();
        picurl=intent.getStringArrayExtra("url");

        //应该还有一个返回按钮
        back=findViewById(R.id.back_album_main);
        viewPager = findViewById(R.id.viewpager_album);
        presenter = new Album_Presenter(this, picurl,this ,getSupportFragmentManager());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    public void startViewPager(Album_PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @Override
    public ImageView getImageView() {
        return null;
    }
}
