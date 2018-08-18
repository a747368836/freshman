package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DiffcultColumn;

import java.util.List;

public class DiffPresenter implements IDiffPresenter {
    IDiffView mView;
    IDiffModel mModel;

    public DiffPresenter(IDiffView view){
        this.mView = view;
        mModel = new DiffModel();
    }
    @Override
    public void loadList(String url, String name) {
        mModel.RequestData(url, name, new IDiffRequestListener() {
            @Override
            public void OnSuccess(List<DiffColumnBean.ArrayBean> list) {
                mView.showList(list);
            }

            @Override
            public void OnError(String e) {
                mView.showError(e);
            }
        });
    }
}
