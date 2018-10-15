package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by 郝书逸 on 2018/8/16.
 */

public class CQUPTAppearance_Presenter implements ICQUPTAppearancePresenter{
    private CQUPTAppearance_Model model;
    private ICQUPTAppearanceView activity,fragmnet_union,fragmnet_event;
    CQUPTAppearance_Presenter(ICQUPTAppearanceView activity,ICQUPTAppearanceView fragment_union,ICQUPTAppearanceView fragmnet_event){
        model=new CQUPTAppearance_Model();
        this.activity=activity;
        this.fragmnet_event=fragmnet_event;
        this.fragmnet_union=fragment_union;
        loadData();
    }


    @Override
    public void loadData() {
        model.RequestData(new CQUPTAppearanceOnRequestListner() {
            @Override
            public void OnSuccess(CQUPTAppearance_Bean_10 bean_union, CQUPTAppearance_Bean_10 bean_event) {
                fragmnet_union.show(bean_union);
                fragmnet_event.show(bean_event);

            }

            @Override
            public void OnError(String e) {
                activity.showError(e);
            }
        });

    }

    @Override
    public void loadPicture(String url, ImageView imageView, Context context) {
        model.getPicture(url,imageView,context);
    }
}
