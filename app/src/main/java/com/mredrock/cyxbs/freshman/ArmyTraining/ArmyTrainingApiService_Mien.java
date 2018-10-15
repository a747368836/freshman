package com.mredrock.cyxbs.freshman.ArmyTraining;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 郝书逸 on 2018/8/11.
 */

public interface ArmyTrainingApiService_Mien {
    @GET("data/get/junxun")
    Observable<ArmyTraining_Bean_Mien> getData();
}
