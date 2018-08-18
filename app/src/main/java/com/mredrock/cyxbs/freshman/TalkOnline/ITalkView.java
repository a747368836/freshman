package com.mredrock.cyxbs.freshman.TalkOnline;


import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBERecyAdapter;

import java.util.List;

public interface ITalkView {

    void showList(List<TalkBean.ArrayBean.Array1Bean> dataList,TalkRecyAdapter adapter,
                  String type);

    void showError(String errorMessage);
}
