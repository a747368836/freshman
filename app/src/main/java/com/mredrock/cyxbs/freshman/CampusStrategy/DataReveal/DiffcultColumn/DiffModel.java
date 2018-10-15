package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DiffcultColumn;

import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.Tools.GrowImage.GrowImage;

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

public class DiffModel implements IDiffModel {
    @Override
    public void RequestData(String url, String name, IDiffRequestListener onRequesListner) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DiffApiService apiService = retrofit.create(DiffApiService.class);
        Observable<DiffColumnBean> getData = apiService.getData(name);
        getData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DiffColumnBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DiffColumnBean dataBean) {
                        onRequesListner.OnSuccess(dataBean.getArray());
                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequesListner.OnError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
