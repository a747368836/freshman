package com.mredrock.cyxbs.freshman.CampusStrategy.BBE;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

public class BBEPresenter implements IBBEPresenter {
    private IBBEView mView;
    private IBBEModel mModel;
    public BBEPresenter (IBBEView view) {
        mView = view;
        mModel = new BBEModel();
    }
    @Override
    public void loadList(String url,String type,int pageNum,int pageSize,BBERecyAdapter adapter) {
        mModel.RequestData(url, new BBEOnRequestionListner() {
            @Override
            public void OnSuccess(List<BBEBean.ArrayBean> list) {
                mView.showList(list,adapter);
            }

            @Override
            public void OnError(String e) {
                mView.showError(e);
            }
        },type,pageNum,pageSize);
    }

    @Override
    public void loadList(String url, String type, int pageNum, int pageSize) {
        mModel.RequestData(url, new BBEOnRequestionListner() {
            @Override
            public void OnSuccess(List<BBEBean.ArrayBean> list) {
                mView.showList(list);
            }

            @Override
            public void OnError(String e) {
                mView.showError(e);
            }
        },type,pageNum,pageSize);
    }

    @Override
    public void loadList(String url, String type, int pageNum, int pageSize,
                         RefreshLayout refreshLayout,BBERecyAdapter adapter) {
        mModel.RequestData(url, new BBEOnRequestionListner() {
            @Override
            public void OnSuccess(List<BBEBean.ArrayBean> list) {
                mView.showList(list,adapter,"refresh");
                refreshLayout.finishRefresh();
            }

            @Override
            public void OnError(String e) {
                mView.showError(e);
                refreshLayout.finishRefresh(false);
            }
        },type,pageNum,pageSize);
    }


}
