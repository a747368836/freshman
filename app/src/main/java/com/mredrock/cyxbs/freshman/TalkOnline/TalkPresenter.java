package com.mredrock.cyxbs.freshman.TalkOnline;

import java.util.List;

public class TalkPresenter implements ITalkPresenter {

    private ITalkModel mModel;
    private ITalkView mView;

    public TalkPresenter (TalkOnlineFragment view) {
        mView = view;
        mModel = new TalkModel();
    }
    @Override
    public void loadList(String url, String type, String content, TalkRecyAdapter adapter) {
        mModel.RequestData(url, new TalkOnRequestListener() {
            @Override
            public void OnSuccess(List<TalkBean.ArrayBean.Array1Bean> list) {
                mView.showList(list,adapter,type);
            }

            @Override
            public void OnError(String e) {
                mView.showError(e);
            }
        },type,content);

    }
}
