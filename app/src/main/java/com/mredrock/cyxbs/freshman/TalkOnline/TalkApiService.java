package com.mredrock.cyxbs.freshman.TalkOnline;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TalkApiService {

    @GET("search/chatgroup/abstractly?")
    Observable<TalkBean> getData(@Query("index") String type,
                                @Query("key") String content);

}
