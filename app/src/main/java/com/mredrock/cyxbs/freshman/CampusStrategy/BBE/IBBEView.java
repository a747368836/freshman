package com.mredrock.cyxbs.freshman.CampusStrategy.BBE;

import java.util.List;

public interface IBBEView {


    void showList(List<BBEBean.ArrayBean> dataList,BBERecyAdapter adapter);
    void showList(List<BBEBean.ArrayBean> dataList);
    void showList(List<BBEBean.ArrayBean> dataList,BBERecyAdapter adapter,String type);

    void showError(String errorMessage);



}
