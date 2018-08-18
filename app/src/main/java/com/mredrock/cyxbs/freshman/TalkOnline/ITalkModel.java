package com.mredrock.cyxbs.freshman.TalkOnline;


public interface ITalkModel {

    void RequestData(String url, final TalkOnRequestListener onRequesListner,
                     String type,String content);

}
