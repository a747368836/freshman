package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Bean_Mien;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 郝书逸 on 2018/8/11.
 */

public interface CQUPTAppearanceApiService_10 {
    @GET("data/get/byindex")
    Observable<CQUPTAppearance_Bean_10> getData(@Query("index") String index, @Query("pagenum") int pagenum, @Query("pagesize") int pagesize);
}
