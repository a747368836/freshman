package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTrainingApiService_Tip;
import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Bean_Mien;
import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Bean_Tip;
import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_PicLoader;


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

public class CQUPTAppearance_Model implements ICQUPTAppearanceModel {
    private CQUPTAppearanceOnRequestListner onRequestListner;
    private String url="http://47.106.33.112:8080/welcome2018/";
    String[] indexarray={"学生组织","大型活动"};
    private static int union_amount;
    private static int event_amount;
    private CQUPTAppearance_Bean_10 bean_union;
    private CQUPTAppearance_Bean_10 bean_event;


    @Override
    public void RequestData(CQUPTAppearanceOnRequestListner onRequestListner) {
        this.onRequestListner=onRequestListner;
        request1(0);
    }
    private void request1(final int index){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CQUPTAppearanceApiService_1 apiService_1 = retrofit.create(CQUPTAppearanceApiService_1.class);
        Observable<CQUPTAppearance_Bean_1> getData = apiService_1.getData(indexarray[index]);
        getData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CQUPTAppearance_Bean_1>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CQUPTAppearance_Bean_1 bean_1) {
                        if(index==0){
                            union_amount=Integer.valueOf(bean_1.getAmount()).intValue();
                            request10(index,union_amount);
                        }else {
                            event_amount=Integer.valueOf(bean_1.getAmount()).intValue();
                            request10(index,event_amount);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListner.OnError(e.getMessage());
                        Log.d("TAG", "onError: 11111111111111111111111111111111"+index);

                    }

                    @Override
                    public void onComplete() {


                    }
                });
    }
    private void request10(final int index, int amount){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CQUPTAppearanceApiService_10 apiService10 = retrofit.create(CQUPTAppearanceApiService_10.class);
        Observable<CQUPTAppearance_Bean_10> getData = apiService10.getData(indexarray[index],1,amount);
        getData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CQUPTAppearance_Bean_10>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CQUPTAppearance_Bean_10 bean_10) {
                        if(index==0){
                            bean_union=bean_10;
                            request1(1);
                        }else{
                            bean_event=bean_10;
                            onRequestListner.OnSuccess(bean_union,bean_event);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListner.OnError(e.getMessage());
                        Log.d("TAG", "onError: 22222222222222222222222"+index);

                    }

                    @Override
                    public void onComplete() {


                    }
                });
    }







    @Override
    public void getPicture(String url,ImageView imageView,Context context ) {
        CQUPTAppearance_PicLoader.getInstance().displayImage(context,url,imageView);
    }

    @Override
    public void getBanner() {
        // TODO: 2018/8/12
    }


}
