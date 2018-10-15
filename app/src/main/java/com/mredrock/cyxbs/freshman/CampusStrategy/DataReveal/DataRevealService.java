package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataRevealService {

    @GET("search/school/getname")
    Observable<DataRevealBean> getData();

}
