package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.IBBEModel;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.IBBEView;

import java.util.List;

public class DataRevealPresenter implements IDataRevealPresenter{

    private IDataRevealView mView;
    private IDataRaveealModel mModel;

    public DataRevealPresenter(IDataRevealView view){
        this.mView = view;
        mModel = new DataRevealModel();
    }

    @Override
    public void loadList(String url){
        mModel.RequestData(url, new DataRevealRequestListner() {
            @Override
            public void OnSuccess(List<String> list) {
                mView.showList(list);
            }

            @Override
            public void OnError(String e) {
                mView.showError(e);
            }
        });
    }
}
