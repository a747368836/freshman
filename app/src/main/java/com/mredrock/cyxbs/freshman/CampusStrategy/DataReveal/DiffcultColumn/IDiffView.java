package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DiffcultColumn;

import com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent.BGBean;

import java.util.List;

public interface IDiffView {
    void showList(List<DiffColumnBean.ArrayBean> list);

    void showError(String errorMessage);
}
