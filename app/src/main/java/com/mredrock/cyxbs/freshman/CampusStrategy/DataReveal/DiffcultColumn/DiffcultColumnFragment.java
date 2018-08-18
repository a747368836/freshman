package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DiffcultColumn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent.BGApiService;
import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent.BGBean;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.Tools.GrowImage.GrowImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mredrock.cyxbs.freshman.FreshmanMainActivity.BASEURL;

@SuppressLint("ValidFragment")
public class DiffcultColumnFragment extends Fragment implements IDiffView {

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {

        }
        super.setUserVisibleHint(isVisibleToUser);
    }
    View view;
    TextView tx1, tx2, tx3, tx4, tx5, tx6;
    TextView course1Txv, course2Txv, course3Txv;
    GrowImage course1Img, course2Img, course3Img;
    int maxNum = 0;
    int progress = 0;
    List<DiffColumnBean.ArrayBean> list = new ArrayList<>();
    String name = "";
    int num = 0;
    boolean isToStart = true;


    @SuppressLint("ValidFragment")
    public DiffcultColumnFragment(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.freshman_campus_data_diff_column_fragment, container, false);
        initView();
        DiffPresenter presenter = new DiffPresenter(this);
        presenter.loadList(BASEURL,name);
        return view;
    }

    @Override
    public void onStart() {
        isToStart = false;
        super.onStart();

    }

    public void initView() {
        tx1 = view.findViewById(R.id.freshman_campus_data_diff_text1);
        tx2 = view.findViewById(R.id.freshman_campus_data_diff_text2);
        tx3 = view.findViewById(R.id.freshman_campus_data_diff_text3);
        tx4 = view.findViewById(R.id.freshman_campus_data_diff_text4);
        tx5 = view.findViewById(R.id.freshman_campus_data_diff_text5);
        tx6 = view.findViewById(R.id.freshman_campus_data_diff_text6);
        course1Txv = view.findViewById(R.id.freshman_campus_data_diff_course1);
        course2Txv = view.findViewById(R.id.freshman_campus_data_diff_course2);
        course3Txv = view.findViewById(R.id.freshman_campus_data_diff_course3);
        course1Img = view.findViewById(R.id.freshman_campus_data_diff_couse1_imageview);
        course2Img = view.findViewById(R.id.freshman_campus_data_diff_couse2_imageview);
        course3Img = view.findViewById(R.id.freshman_campus_data_diff_couse3_imageview);


    }



    @Override
    public void onStop() {

        super.onStop();
    }

    @Override
    public void showList(List<DiffColumnBean.ArrayBean> list) {

        for (int i = 0; i < list.size(); i++) {
            if (maxNum < list.get(i).getBelow_amount()) {
                maxNum = list.get(i).getBelow_amount();
            }
        }
        while (maxNum % 10 != 0 && maxNum % 6 != 0) {
            maxNum++;
        }
        List<TextView> textList = new ArrayList<>();
        textList.add(tx1);
        textList.add(tx2);
        textList.add(tx3);
        textList.add(tx4);
        textList.add(tx5);
        textList.add(tx6);
        //数学渣。。只会这么算了
        tx1.setText(maxNum + "");
        tx2.setText(maxNum / 6 * 5 + "");
        tx3.setText(maxNum / 6 * 4 + "");
        tx4.setText(maxNum / 6 * 3 + "");
        tx5.setText(maxNum / 6 * 2 + "");
        tx6.setText(maxNum / 6 + "");
        for (int i = 0; i < textList.size(); i++) {
            if (Integer.valueOf(textList.get(i).getText().toString()) >= 10
                    && Integer.valueOf(textList.get(i).getText().toString()) < 100) {
                textList.get(i).setPadding(8, 0, 0, 0);
            } else if (Integer.valueOf(textList.get(i).getText().toString()) >= 10
                    && Integer.valueOf(textList.get(i).getText().toString()) < 100) {
                textList.get(i).setPadding(10, 0, 0, 0);
            }
        }
        course1Txv.setText(list.get(0).getSubject_name());
        course2Txv.setText(list.get(1).getSubject_name());
        course3Txv.setText(list.get(2).getSubject_name());
        System.out.println("list的大小是" + list.size());
        List<GrowImage> imageList = new ArrayList<>();
        imageList.add(course1Img);
        imageList.add(course2Img);
        imageList.add(course3Img);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int num0 = 0;
                int progress0 = 0;
                int num1 = 0;
                int progress1 = 0;
                int num2 = 0;
                int progress2 = 0;
                boolean flag1 = true;
                boolean flag2 = true;
                boolean flag3 = true;
                while (flag1||flag2||flag3) {
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(imageList.get(0).getProgress() < (100 * list.get(0).getBelow_amount() / maxNum)){
                        num0 = (int) (imageList.get(0).getProgress() * maxNum / 100.0);
                        progress0++;
                        imageList.get(0).setProgress(progress0, ++num0 + "人");
                    }else {
                        flag1 = false;
                    }
                    if(imageList.get(1).getProgress() < (100 * list.get(1).getBelow_amount() / maxNum)){
                        num1 = (int) (imageList.get(1).getProgress() * maxNum / 100.0);
                        progress1++;
                        imageList.get(1).setProgress(progress1, num1 + "人");
                    }else {
                        flag2 = false;
                    }
                    if(imageList.get(2).getProgress() < (100 * list.get(2).getBelow_amount() / maxNum)){
                        num2 = (int) (imageList.get(2).getProgress() * maxNum / 100.0);
                        progress2++;
                        imageList.get(2).setProgress(progress2, num2 + "人");
                    }else {
                        flag3 = false;
                    }
                    if(!(flag1||flag2||flag3)){
                        imageList.get(0).setProgress(progress0, list.get(0).getBelow_amount() + "人");
                        imageList.get(1).setProgress(progress1, list.get(1).getBelow_amount() + "人");
                        imageList.get(2).setProgress(progress2, list.get(2).getBelow_amount() + "人");
                    }
                }



            }
        }).start();
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getActivity(),errorMessage,Toast.LENGTH_SHORT).show();
    }

    /*public  ExecutorService newCachedThreadPool(){

        return new ThreadPoolExecutor(0,Integer.MAX_VALUE,60L,TimeUtil.SECONDS,new SynchronousQueue());
    }*/


}




