package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent;

import android.widget.Toast;

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

public class BGModel implements IBGModel {
    @Override
    public void RequestData(String url, String name, IBGRequestListener onRequesListner) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BGApiService apiService = retrofit.create(BGApiService.class);
        Observable<BGBean> getData = apiService.getData(name);

        getData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BGBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BGBean dataBean) {
                        onRequesListner.OnSuccess(dataBean);

                    }

                    @Override
                    public void onError(Throwable e) {

                         }

                    @Override
                    public void onComplete() {


                    }
                });

    }
}
