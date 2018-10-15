package com.mredrock.cyxbs.freshman.HowToRegist;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEActivity;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEPresenter;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBERecyAdapter;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.IBBEView;
import com.mredrock.cyxbs.freshman.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import static com.mredrock.cyxbs.freshman.FreshmanMainActivity.BASEURL;

public class RegistActivity extends AppCompatActivity implements IBBEView {

    RecyclerView recyclerView;
    Toolbar toolbar;
    BBERecyAdapter adapter;
    ImageView backView;
    String type = "报道流程";
    List<BBEBean.ArrayBean> dataBean = new ArrayList<>();
    SmartRefreshLayout refreshLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.freshman_how_to_regist_main_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        /*setSupportActionBar(toolbar);*/
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        adapter = new BBERecyAdapter(this,dataBean,type);
        initRecy();
        BBEPresenter presenter = new BBEPresenter(this);
        presenter.loadList(BASEURL,type,1,100,adapter);

    }

    public void initView(){
        backView = findViewById(R.id.freshman_regist_main_activity_back_imageview);
        toolbar = findViewById(R.id.freshman_regist_main_activity_toolbar);
        recyclerView = findViewById(R.id.freshman_regist_main_activity_recycler);
    }

    public void initRecy(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager
                (RegistActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void showList(List<BBEBean.ArrayBean> dataList, BBERecyAdapter adapter) {
        dataBean = dataList;
        recyclerView.setAdapter(adapter);
        adapter.refreshDatabean(dataBean);
    }

    @Override
    public void showList(List<BBEBean.ArrayBean> dataList) {

    }

    @Override
    public void showList(List<BBEBean.ArrayBean> dataList, BBERecyAdapter adapter, String type) {

    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();
    }
}
