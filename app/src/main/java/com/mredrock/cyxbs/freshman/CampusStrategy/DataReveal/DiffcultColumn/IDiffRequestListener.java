package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DiffcultColumn;

import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent.BGBean;

import java.util.List;

public interface IDiffRequestListener {
    void OnSuccess(List<DiffColumnBean.ArrayBean> list);
    void OnError(String e);
}
