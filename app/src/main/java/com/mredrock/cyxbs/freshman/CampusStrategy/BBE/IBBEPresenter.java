package com.mredrock.cyxbs.freshman.CampusStrategy.BBE;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

public interface IBBEPresenter {
    void loadList(String url,String type,int pageNum,int pageSize,BBERecyAdapter adapter);

    void loadList(String url,String type,int pageNum,int pageSize);

    void loadList(String url, String type, int pageNum, int pageSize, RefreshLayout refreshLayout,BBERecyAdapter adapter);


}
