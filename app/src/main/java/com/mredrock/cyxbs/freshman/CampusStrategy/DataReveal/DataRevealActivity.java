package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEApiService;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEOnRequestionListner;
import com.mredrock.cyxbs.freshman.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mredrock.cyxbs.freshman.FreshmanMainActivity.BASEURL;


public class DataRevealActivity extends AppCompatActivity implements IDataRevealView{
    Toolbar toolbar;
    RecyclerView mRecyclerView;
    ImageView backView;
    List<String> list = new ArrayList<>();
    DataRevealAdapter adapter;
    Context mContext = this;
    public boolean finished = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.freshman_campus_datareveal_activity);
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //绑定toolbar
        /*setSupportActionBar(toolbar);*/
        adapter = new DataRevealAdapter(mContext,list);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DataRevealPresenter presenter = new DataRevealPresenter(this);
        presenter.loadList(BASEURL);


    }

    public void initView(){
        backView = findViewById(R.id.freshman_campus_data_back_imageview);
        toolbar = findViewById(R.id.freshman_campus_data_toolbar);
        mRecyclerView = findViewById(R.id.freshman_campus_data_recycler);
    }




    @Override
    public void showList(List<String> dataList) {
        this.list = dataList;
        mRecyclerView.setAdapter(adapter);
        finished = true;
        adapter.reFreshData(list);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();
    }
}
