package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent;

import java.util.List;

public class BGPresenter implements  IBGPresenter {
    private IBGModel mModel;
    private IBGView mView;
    public BGPresenter(IBGView view){
        this.mModel = new BGModel();
        this.mView = view;
    }
    @Override
    public void loadList(String url, String name) {
        mModel.RequestData(url, name, new IBGRequestListener() {

            @Override
            public void OnSuccess(BGBean bean) {
                mView.showList(bean);
            }

            @Override
            public void OnError(String e) {
                mView.showError(e);
            }
        });
    }
}
