package com.mredrock.cyxbs.freshman;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;


import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Activity;
import com.mredrock.cyxbs.freshman.CQUPTAppearance.CQUPTAppearance_Activity;
import com.mredrock.cyxbs.freshman.CampusStrategy.CampusMainActivity;
import com.mredrock.cyxbs.freshman.EssentialToRegister.EssentialMainActivity;
import com.mredrock.cyxbs.freshman.HowToRegist.RegistActivity;
import com.mredrock.cyxbs.freshman.IWantToSay.IWantToSatActivity;
import com.mredrock.cyxbs.freshman.TalkOnline.TalkOnlineActivity;
import com.mredrock.cyxbs.freshman.Tools.Album.Album_Activity;

import static android.widget.Toast.LENGTH_SHORT;


public class FreshmanMainActivity extends AppCompatActivity {
    public static Integer lockposition;

    /*public static final String BASEURL = "http://118.24.175.82/";*/
    public static final String BASEURL = "http://47.106.33.112:8080/welcome2018/";
    private String text= "看完之前的内容才能看这里哦" ;

    private Toolbar toolbar;
    private ImageView essentialtoregister;
    private ImageView armytraining;
    private ImageView campusstrategy;
    private ImageView onlinecommunication;
    private ImageView cquptappearance;
    private ImageView reportingprocess;
    private ImageView somethingtosay;
    private ImageView car_essentialtoregister;
    private ImageView car_campusstrategy;
    private ImageView car_onlinecommunication;
    private ImageView car_reportingprocess;
    private ImageView car_somethingtosay;
    private ImageView car_step1;
    private ImageView car_step2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //安卓版本大于4.4设置状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //绑定toolbar
        setSupportActionBar(toolbar);
        setContentView(R.layout.freshman_main_activity);
        SharedPreferences preferences = getSharedPreferences("setting", 0);
        int position = preferences.getInt("lockposition",-1);
        if(position  == -1){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("lockposition",0);
            editor.apply();
            position = 0;
        }

        if(lockposition==null){
            lockposition = position;
        }
        initView();

       /* Intent intent =new Intent(this, CQUPTAppearance_Activity.class);
        startActivity(intent);
        finish();*/
    }

    //初始化控件
    private void initView(){
        toolbar=findViewById(R.id.toolbar_mainmenu);
        essentialtoregister=findViewById(R.id.pic_mainmenu_essentialtoregister);
        armytraining=findViewById(R.id.pic_mainmenu_armytraining);
        campusstrategy=findViewById(R.id.pic_mainmenu_campusstrategy);
        onlinecommunication=findViewById(R.id.pic_mainmenu_onlinecommunication);
        cquptappearance=findViewById(R.id.pic_mainmenu_cquptappearance);
        reportingprocess=findViewById(R.id.pic_mainmenu_reportingprocess);
        somethingtosay=findViewById(R.id.pic_mainmenu_somethingtosay);
        car_campusstrategy=findViewById(R.id.car_mainmenu_campusstrategy);
        car_essentialtoregister=findViewById(R.id.car_mainmenu_essentialtoregister);
        car_onlinecommunication=findViewById(R.id.car_mainmenu_onlinecommunication);
        car_reportingprocess=findViewById(R.id.car_mainmenu_reportinigprocess);
        car_somethingtosay=findViewById(R.id.car_mainmenu_somethingtosay);
        car_step1=findViewById(R.id.car_mainmenu_step1);
        car_step2=findViewById(R.id.car_mainmenu_step2);

        switch (lockposition){
            case 0:
                Toast.makeText(this, "    欢迎来到新生专题\n请按顺序点击图中气泡", Toast.LENGTH_LONG).show();
                campusstrategy.setImageResource(R.drawable.freshman_mainmenu_lock_campusstrategy);
            case 1:
                onlinecommunication.setImageResource(R.drawable.freshman_mainmenu_lock_onlinecommunication);
            case 2:
                reportingprocess.setImageResource(R.drawable.freshman_mainmenu_lock_reportingprocess);
            case 3:
                somethingtosay.setImageResource(R.drawable.freshman_mainmenu_lock_somethingtosay);
        }
        switch (lockposition){
            case 0:
                car_essentialtoregister.setImageResource(R.drawable.freshman_mainmenu_car_left);
                break;
            case 1:
                car_essentialtoregister.setImageResource(R.drawable.freshman_mainmenu_car_left);
                break;
            case 2:
                car_campusstrategy.setImageResource(R.drawable.freshman_mainmenu_car_left);
                break;
            case 3:
                car_onlinecommunication.setImageResource(R.drawable.freshman_mainmenu_car_right);
                break;
            case 4:
                car_reportingprocess.setImageResource(R.drawable.freshman_mainmenu_car_left);
                break;
            case 5:
                car_somethingtosay.setImageResource(R.drawable.freshman_mainmenu_car_right);
        }
        setlistener();
    }

    private void setlistener(){

        armytraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getBaseContext(), ArmyTraining_Activity.class);
                restartactivity();
                startActivity(intent);

            }
        });

        cquptappearance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2018/8/14
                Intent intent =new Intent(getBaseContext(),CQUPTAppearance_Activity.class);
                restartactivity();
                startActivity(intent);
            }
        });

        essentialtoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lockposition==0){
                    lockposition=1;
                    SharedPreferences preferences = getSharedPreferences("setting", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("lockposition",1);
                    editor.apply();
                }
                Intent intent =new Intent(getBaseContext(),EssentialMainActivity.class );
                restartactivity();
                startActivity(intent);
            }
        });

        campusstrategy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lockposition >= 1) {
                    if (lockposition == 1) {
                        lockposition = 2;
                        SharedPreferences preferences = getSharedPreferences("setting", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("lockposition", 2);
                        editor.apply();
                        //开车动画
                        float lengthX = car_campusstrategy.getX() - car_essentialtoregister.getX();
                        ValueAnimator tx = ObjectAnimator.ofFloat(car_essentialtoregister, "translationX", 0f, lengthX);
                        float lengthY = car_campusstrategy.getY() - car_essentialtoregister.getY();
                        ValueAnimator ty = ObjectAnimator.ofFloat(car_essentialtoregister, "translationY", 0f, lengthY);
                        ValueAnimator sx = ObjectAnimator.ofFloat(car_essentialtoregister, "scaleX", car_essentialtoregister.getScaleX(), car_campusstrategy.getScaleX());
                        ValueAnimator sy = ObjectAnimator.ofFloat(car_essentialtoregister, "scaleY", car_essentialtoregister.getScaleY(), car_campusstrategy.getScaleY());
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(tx, ty, sx, sy);
                        set.setDuration(2000);
                        set.setInterpolator(new AccelerateDecelerateInterpolator());
                        set.start();
                        set.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                // TODO: 2018/8/14
                                Intent intent = new Intent(getBaseContext(), CampusMainActivity.class);
                                restartactivity();
                                intent.putExtra("type", "开学必备");
                                startActivity(intent);
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                    } else {

                        // TODO: 2018/8/14
                        Intent intent = new Intent(getBaseContext(), CampusMainActivity.class);
                        restartactivity();
                        startActivity(intent);
                    }

                }else{
                    Toast.makeText(getBaseContext(),text,LENGTH_SHORT).show();
                }
            }
        });

        onlinecommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lockposition>=2){
                    if(lockposition==2){
                        lockposition=3;
                        SharedPreferences preferences = getSharedPreferences("setting", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("lockposition",3);
                        editor.apply();
                        //开车动画
                        car_campusstrategy.setImageResource(R.drawable.freshman_mainmenu_car_right);
                        float lengthX = car_onlinecommunication.getX()-car_campusstrategy.getX();
                        ValueAnimator tx= ObjectAnimator.ofFloat(car_campusstrategy,"translationX",0f,lengthX);
                        float lengthY = car_onlinecommunication.getY()-car_campusstrategy.getY();
                        ValueAnimator ty= ObjectAnimator.ofFloat(car_campusstrategy,"translationY",0f,lengthY);
                        ValueAnimator sx= ObjectAnimator.ofFloat(car_campusstrategy,"scaleX",car_campusstrategy.getScaleX(),car_onlinecommunication.getScaleX());
                        ValueAnimator sy= ObjectAnimator.ofFloat(car_campusstrategy,"scaleY",car_campusstrategy.getScaleY(),car_onlinecommunication.getScaleY());
                        AnimatorSet set=new AnimatorSet();
                        set.playTogether(tx,ty,sx,sy);
                        set.setDuration(2000);
                        set.setInterpolator(new AccelerateDecelerateInterpolator());
                        set.start();
                        set.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                // TODO: 2018/8/14
                                Intent intent = new Intent(getBaseContext(), TalkOnlineActivity.class);
                                restartactivity();
                                startActivity(intent);
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                    }else {
                        // TODO: 2018/8/14
                        Intent intent = new Intent(getBaseContext(), TalkOnlineActivity.class);
                        restartactivity();
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(getBaseContext(),text,LENGTH_SHORT).show();
                }
            }
        });

        reportingprocess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lockposition>=3){
                    if(lockposition==3){
                        lockposition=4;
                        SharedPreferences preferences = getSharedPreferences("setting", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("lockposition",4);
                        editor.apply();
                        //开车动画
                        float lengthX = car_step1.getX()-car_onlinecommunication.getX();
                        ValueAnimator tx= ObjectAnimator.ofFloat(car_onlinecommunication,"translationX",0f,lengthX);
                        float lengthY = car_step1.getY()-car_onlinecommunication.getY();
                        ValueAnimator ty= ObjectAnimator.ofFloat(car_onlinecommunication,"translationY",0f,lengthY);
                        ValueAnimator sx= ObjectAnimator.ofFloat(car_onlinecommunication,"scaleX",car_onlinecommunication.getScaleX(),car_step1.getScaleX());
                        ValueAnimator sy= ObjectAnimator.ofFloat(car_onlinecommunication,"scaleY",car_onlinecommunication.getScaleY(),car_step1.getScaleY());
                        AnimatorSet set=new AnimatorSet();
                        set.playTogether(tx,ty,sx,sy);
                        set.setDuration(2000);
                        set.setInterpolator(new AccelerateInterpolator());
                        set.start();
                        set.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                car_step1.setImageResource(R.drawable.freshman_mainmenu_car_left);
                                car_onlinecommunication.setAlpha(0f);
                                float lengthX = car_reportingprocess.getX()-car_step1.getX();
                                ValueAnimator tx= ObjectAnimator.ofFloat(car_step1,"translationX",0f,lengthX);
                                float lengthY = car_reportingprocess.getY()-car_step1.getY();
                                ValueAnimator ty= ObjectAnimator.ofFloat(car_step1,"translationY",0f,lengthY);
                                ValueAnimator sx= ObjectAnimator.ofFloat(car_step1,"scaleX",car_step1.getScaleX(),car_reportingprocess.getScaleX());
                                ValueAnimator sy= ObjectAnimator.ofFloat(car_step1,"scaleY",car_step1.getScaleY(),car_reportingprocess.getScaleY());
                                AnimatorSet set=new AnimatorSet();
                                set.playTogether(tx,ty,sx,sy);
                                set.setDuration(2000);
                                set.setInterpolator(new DecelerateInterpolator());
                                set.start();
                                set.addListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animator) {
                                        // TODO: 2018/8/14
                                        Intent intent = new Intent(getBaseContext(),  RegistActivity.class);
                                        restartactivity();
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animator) {

                                    }
                                });
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                    }else {
                        // TODO: 2018/8/14
                        Intent intent = new Intent(getBaseContext(),  RegistActivity.class);
                        restartactivity();
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(getBaseContext(),text,LENGTH_SHORT).show();
                }
            }
        });

        somethingtosay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lockposition>=4){
                    if(lockposition==4){
                        lockposition=5;
                        SharedPreferences preferences = getSharedPreferences("setting", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("lockposition",5);
                        editor.apply();
                        //开车动画
                        float lengthX = car_step2.getX()-car_reportingprocess.getX();
                        ValueAnimator tx= ObjectAnimator.ofFloat(car_reportingprocess,"translationX",0f,lengthX);
                        float lengthY = car_step2.getY()-car_reportingprocess.getY();
                        ValueAnimator ty= ObjectAnimator.ofFloat(car_reportingprocess,"translationY",0f,lengthY);
                        ValueAnimator sx= ObjectAnimator.ofFloat(car_reportingprocess,"scaleX",car_reportingprocess.getScaleX(),car_step2.getScaleX());
                        ValueAnimator sy= ObjectAnimator.ofFloat(car_reportingprocess,"scaleY",car_reportingprocess.getScaleY(),car_step2.getScaleY());
                        AnimatorSet set=new AnimatorSet();
                        set.playTogether(tx,ty,sx,sy);
                        set.setDuration(1000);
                        set.setInterpolator(new AccelerateInterpolator());
                        set.start();
                        set.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                car_step2.setImageResource(R.drawable.freshman_mainmenu_car_right);
                                car_reportingprocess.setAlpha(0f);
                                float lengthX = car_somethingtosay.getX()-car_step2.getX();
                                ValueAnimator tx= ObjectAnimator.ofFloat(car_step2,"translationX",0f,lengthX);
                                float lengthY = car_somethingtosay.getY()-car_step2.getY();
                                ValueAnimator ty= ObjectAnimator.ofFloat(car_step2,"translationY",0f,lengthY);
                                ValueAnimator sx= ObjectAnimator.ofFloat(car_step2,"scaleX",car_step2.getScaleX(),car_somethingtosay.getScaleX());
                                ValueAnimator sy= ObjectAnimator.ofFloat(car_step2,"scaleY",car_step2.getScaleY(),car_somethingtosay.getScaleY());
                                AnimatorSet set=new AnimatorSet();
                                set.playTogether(tx,ty,sx,sy);
                                set.setDuration(1000);
                                set.setInterpolator(new DecelerateInterpolator());
                                set.start();
                                set.addListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animator) {
                                        // TODO: 2018/8/14
                                        Intent intent = new Intent(getBaseContext(), IWantToSatActivity.class);
                                        restartactivity();
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animator) {

                                    }
                                });
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });

                    }else {
                        // TODO: 2018/8/14
                        Intent intent = new Intent(getBaseContext(), IWantToSatActivity.class);
                        restartactivity();
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(getBaseContext(),text,LENGTH_SHORT).show();
                }
            }
        });




    }
    public void restartactivity(){
        Intent intent =getIntent();
        finish();
        startActivity(intent);
    }

}
