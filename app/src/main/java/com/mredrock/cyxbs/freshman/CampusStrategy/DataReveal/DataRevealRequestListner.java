package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;

import java.util.List;

public interface DataRevealRequestListner  {
    void OnSuccess(List<String> list);
    void OnError(String e);
}
