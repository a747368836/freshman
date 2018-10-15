package com.mredrock.cyxbs.freshman.CampusStrategy.BBE;


import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface BBEApiService {
    //@type
    //"学生食堂","周边美食","附近景点","校园环境",
    // "附近银行","公交线路","快递收发",
    // "大型活动","报道流程","我想对你说","学生组织"

    @GET("data/get/byindex?")
    Observable<BBEBean> getData(@Query("index") String type,
                                @Query("pagenum") int pageNum,
                                @Query("pagesize") int pageSize);

    @GET("data/get/sushe?")
    Observable<BBEBean> getData(@Query("name") String type);

}
