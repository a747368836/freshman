package com.mredrock.cyxbs.freshman.ArmyTraining;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 郝书逸 on 2018/8/10.
 */

public class ArmyTraining_Model implements IArmyTrainingModel{
    private ArmyTrainingOnRequestListner onRequestListner;
    String url="http://47.106.33.112:8080/welcome2018/";


    @Override
    public void RequestData(ArmyTrainingOnRequestListner onRequestListner) {
        this.onRequestListner=onRequestListner;
        request_mien();
    }

    private void request_mien(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ArmyTrainingApiService_Mien apiService_mien = retrofit.create(ArmyTrainingApiService_Mien.class);
        Observable<ArmyTraining_Bean_Mien> getData = apiService_mien.getData();
        getData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArmyTraining_Bean_Mien>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArmyTraining_Bean_Mien bean_mien) {
                        request_tip(bean_mien);

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListner.OnError(e.getMessage());

                    }

                    @Override
                    public void onComplete() {


                    }
                });
    }

    private void request_tip(final ArmyTraining_Bean_Mien bean_mien){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ArmyTrainingApiService_Tip apiService_tip = retrofit.create(ArmyTrainingApiService_Tip.class);
        Observable<ArmyTraining_Bean_Tip> getData = apiService_tip.getData();
        getData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArmyTraining_Bean_Tip>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArmyTraining_Bean_Tip bean_tip) {
                        onRequestListner.OnSuccess(bean_mien,bean_tip);

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListner.OnError(e.getMessage());

                    }

                    @Override
                    public void onComplete() {


                    }
                });
    }





    @Override
    public void getPicture(String url,ImageView imageView,Context context ) {
        ArmyTraining_PicLoader.getInstance().displayImage(context,url,imageView);
    }

    @Override
    public void getBanner() {
        // TODO: 2018/8/12
    }


}
