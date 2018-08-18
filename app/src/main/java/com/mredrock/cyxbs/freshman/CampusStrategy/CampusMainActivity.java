package com.mredrock.cyxbs.freshman.CampusStrategy;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEActivity;
import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DataRevealActivity;
import com.mredrock.cyxbs.freshman.CampusStrategy.Domitory.DomitoryActivity;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.Tools.FixedCardview.CardView;

public class CampusMainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView canteenCard;
    CardView domitoryCard;
    CardView foodCard;
    CardView spotCard;
    CardView environmentCard;
    CardView dataRevealCard;
    CardView bankCard;
    CardView expressCard;
    CardView busCard;
    Toolbar toolbar;
    ImageView backImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.freshman_campus_main_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        /*setSupportActionBar(toolbar);*/
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.freshman_campus_main_canteen_cardview:
                intent = new Intent(CampusMainActivity.this, BBEActivity.class);
                intent.putExtra("type","学生食堂");
                startActivity(intent);
                break;
            case R.id.freshman_campus_main_domitory_cardview:
                intent = new Intent(CampusMainActivity.this, DomitoryActivity.class);
                intent.putExtra("type","学生寝室");
                startActivity(intent);
                break;
            case R.id.freshman_campus_main_food_cardview:
                intent = new Intent(CampusMainActivity.this, BBEActivity.class);
                intent.putExtra("type","周边美食");
                startActivity(intent);
                break;
            case R.id.freshman_campus_main_spot_cardview:
                intent = new Intent(CampusMainActivity.this, BBEActivity.class);
                intent.putExtra("type","附近景点");
                startActivity(intent);
                break;
            case R.id.freshman_campus_main_environment_cardview:
                intent = new Intent(CampusMainActivity.this, BBEActivity.class);
                intent.putExtra("type","校园环境");
                startActivity(intent);
                break;
            case R.id.freshman_campus_main_datareaveal_cardview:
                intent = new Intent(CampusMainActivity.this, DataRevealActivity.class);
                intent.putExtra("type","数据揭秘");
                startActivity(intent);
                break;
            case R.id.freshman_campus_main_express_cardview:
                intent = new Intent(CampusMainActivity.this, BBEActivity.class);
                intent.putExtra("type","快递收发");
                startActivity(intent);
                break;
            case R.id.freshman_campus_main_bank_cardview:
                intent = new Intent(CampusMainActivity.this, BBEActivity.class);
                intent.putExtra("type","附近银行");
                startActivity(intent);
                break;
            case R.id.freshman_campus_main_bus_cardview:
                intent = new Intent(CampusMainActivity.this, BBEActivity.class);
                intent.putExtra("type","公交线路");
                startActivity(intent);
                break;

        }
    }

    public void initView() {
        backImage = findViewById(R.id.freshman_campus_main_back_imageview);
        toolbar = findViewById(R.id.freshman_campus_main_toolbar);
        canteenCard = findViewById(R.id.freshman_campus_main_canteen_cardview);
        domitoryCard = findViewById(R.id.freshman_campus_main_domitory_cardview);
        foodCard= findViewById(R.id.freshman_campus_main_food_cardview);
        spotCard= findViewById(R.id.freshman_campus_main_spot_cardview);
        environmentCard= findViewById(R.id.freshman_campus_main_environment_cardview);
        dataRevealCard= findViewById(R.id.freshman_campus_main_datareaveal_cardview);
        bankCard= findViewById(R.id.freshman_campus_main_bank_cardview);
        expressCard= findViewById(R.id.freshman_campus_main_express_cardview);
        busCard= findViewById(R.id.freshman_campus_main_bus_cardview);
         canteenCard.setOnClickListener(this);
         domitoryCard.setOnClickListener(this);
         foodCard.setOnClickListener(this);
         spotCard.setOnClickListener(this);
         environmentCard.setOnClickListener(this);
         dataRevealCard.setOnClickListener(this);
         bankCard.setOnClickListener(this);
         expressCard.setOnClickListener(this);
         busCard.setOnClickListener(this);
        
    }
}
