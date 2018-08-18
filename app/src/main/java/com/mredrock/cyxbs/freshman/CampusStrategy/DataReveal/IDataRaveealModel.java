package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEOnRequestionListner;

import java.util.List;

public interface IDataRaveealModel {


    void RequestData(String url, final DataRevealRequestListner onRequesListner);


}
