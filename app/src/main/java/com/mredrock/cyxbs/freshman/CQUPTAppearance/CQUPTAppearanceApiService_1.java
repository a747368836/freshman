package com.mredrock.cyxbs.freshman.CQUPTAppearance;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 郝书逸 on 2018/8/15.
 */

public interface CQUPTAppearanceApiService_1 {
    @GET("data/describe/getamount")
    Observable<CQUPTAppearance_Bean_1> getData(@Query("index") String index);
}
