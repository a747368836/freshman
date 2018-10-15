package com.mredrock.cyxbs.freshman.CampusStrategy.BBE;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BBEModel implements IBBEModel {
    List<BBEBean.ArrayBean> list;
    @Override
    public void RequestData(String url, final BBEOnRequestionListner onRequesListner,
                            String type , int pageNum , int pageSize) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();



            BBEApiService apiService  = retrofit.create(BBEApiService.class);
            Observable<BBEBean> getData ;
            if (type.contains("苑")){
                getData = apiService.getData(type);
            }else {
                getData = apiService.getData(type,1,100);
            }

        getData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BBEBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BBEBean dataBean) {
                        list = dataBean.getArray();
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
