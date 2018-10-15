package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent;

import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DataRevealRequestListner;

public interface IBGModel {

    void RequestData(String url, String name, final IBGRequestListener onRequesListner);

}
