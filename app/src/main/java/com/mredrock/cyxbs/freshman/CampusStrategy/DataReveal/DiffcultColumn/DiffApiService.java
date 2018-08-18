package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DiffcultColumn;

import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent.BGBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiffApiService {

    @GET("search/school/2?")
    Observable<DiffColumnBean> getData(@Query("name") String name);

}
