package com.mredrock.cyxbs.freshman.TalkOnline;

import android.util.Log;

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

public class TalkModel implements ITalkModel {
    List<TalkBean.ArrayBean.Array1Bean> list = new ArrayList<>();

    @Override
    public void RequestData(String url, TalkOnRequestListener onRequesListner, String type, String content) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TalkApiService apiService = retrofit.create(TalkApiService.class);
        Observable<TalkBean> getData = apiService.getData(type, content);
        getData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TalkBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TalkBean dataBean) {
                        list.clear();
                        System.out.println("真是null1");
                        if (dataBean == null){
                            System.out.println("真是null2");
                        }else {
                            System.out.println("真是null3");
                            if (dataBean.getArray()!=null){
                                for (int i = 0; i < dataBean.getArray().size(); i++) {
                                    for (int j = 0; j < dataBean.getArray().get(i).getArray1().size(); j++) {
                                        list.add(dataBean.getArray().get(i).getArray1().get(j));
                                    }
                                }
                            }

                        }
                        System.out.println("真是null4");


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
