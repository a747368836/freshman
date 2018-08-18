package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by 郝书逸 on 2018/8/12.
 */

public interface ICQUPTAppearanceModel {
    void RequestData(CQUPTAppearanceOnRequestListner onRequestListner);
    void getPicture(String url, ImageView imageView, Context context);
    void getBanner();
}
