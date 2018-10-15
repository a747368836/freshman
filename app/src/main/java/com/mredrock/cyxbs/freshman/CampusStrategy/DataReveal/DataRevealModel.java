package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEOnRequestionListner;

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

public class DataRevealModel implements IDataRaveealModel {
    List<String> list = new ArrayList<>();
    @Override
    public void RequestData(String url, DataRevealRequestListner onRequesListner) {

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataRevealService apiService  = retrofit.create(DataRevealService.class);
        Observable<DataRevealBean> getData = apiService.getData();
        getData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataRevealBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataRevealBean dataBean) {
                        list = dataBean.getName();
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
