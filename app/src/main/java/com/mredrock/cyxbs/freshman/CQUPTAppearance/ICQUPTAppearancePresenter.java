package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by 郝书逸 on 2018/8/16.
 */

public interface ICQUPTAppearancePresenter {
    void loadData();
    void loadPicture(String url, ImageView imageView, Context context);
}
