package com.mredrock.cyxbs.freshman.EssentialToRegister;


import android.annotation.SuppressLint;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EssentialMainModel implements IEssentialMainModel {

    List<EssentialDataBean.DescribeBean> list = new ArrayList<>();

    @Override
    public void RequestData(String url,final EssentialOnRequesListner onRequesListner) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EssentialApiService apiService = retrofit.create(EssentialApiService.class);
        Observable<EssentialDataBean> getData = apiService.getData();
        getData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EssentialDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EssentialDataBean dataBean) {
                        list = dataBean.getDescribe();
                        onRequesListner.OnSuccess(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequesListner.OnError(e.getMessage());


                    }

                    @Override
                    public void onComplete() {


                    }
                });

    }



}
