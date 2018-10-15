package com.mredrock.cyxbs.freshman.EssentialToRegister;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

public interface IEssentialMainPresenter {

    void loadList(String url,String type,EssentialMainAdapter adapter);

    void loadList(String url, RefreshLayout refreshLayout,EssentialMainAdapter adapter);

    void addItem();
}
