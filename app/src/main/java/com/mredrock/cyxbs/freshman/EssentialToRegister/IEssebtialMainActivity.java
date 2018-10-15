package com.mredrock.cyxbs.freshman.EssentialToRegister;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

public interface IEssebtialMainActivity {



    void showList(List<EssentialDataBean.DescribeBean> dataList,String type,EssentialMainAdapter adapter);

    void showError(String errorMessage);

    String getTitle(String title);

    void addItem(EssentialDataBean.DescribeBean bean);

    void refreshData(RefreshLayout refreshLayout);

}
