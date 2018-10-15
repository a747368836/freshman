package com.mredrock.cyxbs.freshman.ArmyTraining;



import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 郝书逸 on 2018/8/11.
 */

public interface ArmyTrainingApiService_Tip {
    @GET("data/get/describe?index=军训小贴士")
    Observable<ArmyTraining_Bean_Tip> getData();
}
