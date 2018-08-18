package com.mredrock.cyxbs.freshman.CampusStrategy.BBE;

public interface IBBEModel {

    void RequestData(String url, final BBEOnRequestionListner onRequesListner,
                     String type , int pageNum , int pageSize);

}
