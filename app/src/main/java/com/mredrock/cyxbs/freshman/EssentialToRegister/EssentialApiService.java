package com.mredrock.cyxbs.freshman.EssentialToRegister;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface EssentialApiService {

    @GET("data/get/describe?index=入学必备")
    Observable<EssentialDataBean> getData();


}
