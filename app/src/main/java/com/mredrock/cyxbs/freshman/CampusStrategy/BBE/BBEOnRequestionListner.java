package com.mredrock.cyxbs.freshman.CampusStrategy.BBE;

import java.util.List;

public interface BBEOnRequestionListner {

    void OnSuccess(List<BBEBean.ArrayBean> list);
    void OnError(String e);

}
