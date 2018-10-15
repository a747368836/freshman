package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBERecyAdapter;

import java.util.List;

public interface IDataRevealView {


    void showList(List<String> dataList);

    void showError(String errorMessage);
}
