package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BGApiService {

    @GET("search/school/1?")
    Observable<BGBean> getData(@Query("name") String name);


}
