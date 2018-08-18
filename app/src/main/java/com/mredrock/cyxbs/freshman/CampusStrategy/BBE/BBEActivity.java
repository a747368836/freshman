package com.mredrock.cyxbs.freshman.CampusStrategy.BBE;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.mredrock.cyxbs.freshman.EssentialToRegister.EssentialMainActivity;
import com.mredrock.cyxbs.freshman.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import static com.mredrock.cyxbs.freshman.FreshmanMainActivity.BASEURL;


//BBE---- bank bus environment 但是后来发现。。这个可以复用。。。然后。。。就这样吧。。

//ApiService 好像也写多了。。。都能写在一个里面的。。。

public class BBEActivity extends AppCompatActivity implements IBBEView {
    TextView mainTitleView;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ImageView topImageView;
    List<BBEBean.ArrayBean> dataBean = new ArrayList<>();
    BBERecyAdapter adapter;
    String type;
    SmartRefreshLayout refreshLayout ;
    ImageView backImage;

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这个得放到前面才能去掉actionbar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.freshman_campus_bbe_main_activity);
        //绑定控件
        initView();

        //安卓版本大于4.4设置状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //绑定toolbar

        /*setSupportActionBar(toolbar);*/
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if (type.equals("周边美食")){
            topImageView.setVisibility(View.VISIBLE);
        }else {
            topImageView.setVisibility(View.GONE);
        }
        adapter = new BBERecyAdapter(this, dataBean,type);

        //初始化recyclerview
        initRecy();
        BBEPresenter presenter = new BBEPresenter(this);

        presenter.loadList(BASEURL,type,1,100,adapter);
        mainTitleView.setText(type);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.loadList(BASEURL,type,1,100,refreshLayout,adapter);
            }
        });



    }

    public void initRecy() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager
                (BBEActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        //adapter的绑定在showList里面
    }

    public void initView() {
        backImage = findViewById(R.id.freshman_campus_recy_card_back_imageview);
        refreshLayout = findViewById(R.id.freshman_campus_bbe_recycler_smartRefreshLayout);
        mainTitleView = findViewById(R.id.freshman_essential_bbe_toolbar_title_textview);
        toolbar = findViewById(R.id.freshman_campus_bbe_main_toolbar);
        recyclerView = findViewById(R.id.freshman_campus_bbe_recycler);
        topImageView = findViewById(R.id.freshman_campus_main_topImage_food);
    }



    @Override
    public void showList(List<BBEBean.ArrayBean> dataList,BBERecyAdapter adapter) {
        dataBean = dataList;
        recyclerView.setAdapter(adapter);
        adapter.refreshDatabean(dataBean);
    }

    @Override
    public void showList(List<BBEBean.ArrayBean> dataList) {

    }

    @Override
    public void showList(List<BBEBean.ArrayBean> dataList, BBERecyAdapter adapter, String type) {
        switch (type){
            case "refresh":
                dataBean = dataList;
                adapter.refreshDatabean(dataBean);
                break;
        }

    }


    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
