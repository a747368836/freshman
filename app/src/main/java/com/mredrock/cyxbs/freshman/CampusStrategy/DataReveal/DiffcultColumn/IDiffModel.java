package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DiffcultColumn;

import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent.IBGRequestListener;

public interface IDiffModel {
    void RequestData(String url, String name,  IDiffRequestListener onRequesListner);

}
