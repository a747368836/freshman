package com.mredrock.cyxbs.freshman.EssentialToRegister;


import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class EssentialMainPresenter implements IEssentialMainPresenter {
    private IEssebtialMainActivity mMainView;
    private IEssentialMainModel mModel;

    public EssentialMainPresenter (IEssebtialMainActivity view) {
        mMainView = view;
        mModel = new EssentialMainModel();
    }




    @Override
    public void loadList(String url, String type,EssentialMainAdapter adapter) {
        switch (type){
            case "loadFromNet":
                mModel.RequestData(url, new EssentialOnRequesListner() {
                    @Override
                    public void OnSuccess(List<EssentialDataBean.DescribeBean> list) {
                        mMainView.showList(list,"loadFromNet",adapter);
                    }

                    @Override
                    public void OnError(String e) {
                        mMainView.showError(e);
                        /*List<EssentialDataBean.DescribeBean> list = new ArrayList<>();
                        for (int i = 0; i <LitePal.where("content <> ?"," ").find(EssentialDataBean.DescribeBean.class).size(); i++) {
                            list.add(LitePal.where("content <> ?"," ").find(EssentialDataBean.DescribeBean.class).get(i));
                        }
                        mMainView.showList(list,"loadFromNet");*/
                    }
                });
                break;
            case "loadFromLocal":
                mMainView.showList(LitePal.findAll(EssentialDataBean.DescribeBean.class),"loadFromLocal",adapter);
                break;
            case "firstLoad":
                mModel.RequestData(url, new EssentialOnRequesListner() {
                    @Override
                    public void OnSuccess(List<EssentialDataBean.DescribeBean> list) {
                        mMainView.showList(list,"firstLoad",adapter);
                    }

                    @Override
                    public void OnError(String e) {
                        mMainView.showError(e);
                    }
                });
        }
    }

    @Override
    public void loadList(String url, RefreshLayout refreshLayout,EssentialMainAdapter adapter) {

        mModel.RequestData(url, new EssentialOnRequesListner() {
            @Override
            public void OnSuccess(List<EssentialDataBean.DescribeBean> list) {
                mMainView.showList(list,"refresh",adapter);
                refreshLayout.finishRefresh();
            }

            @Override
            public void OnError(String e) {
                mMainView.showError("refreshFailed");
                refreshLayout.finishRefresh(false);
            }
        });
    }

    @Override
    public void addItem() {
        if (mMainView.getTitle("s").isEmpty()||mMainView.getTitle("s").equals("")){
            mMainView.showError("请输入需要添加的事项");
        }else {
            EssentialDataBean.DescribeBean bean = new EssentialDataBean.DescribeBean();
            bean.setName(mMainView.getTitle("s"));
            bean.setProperty("自定义");
            bean.setContent(" ");
            bean.save();
            mMainView.addItem(bean);
        }

    }
}
