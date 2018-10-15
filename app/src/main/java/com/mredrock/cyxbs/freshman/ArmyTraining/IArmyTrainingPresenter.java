package com.mredrock.cyxbs.freshman.ArmyTraining;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by 郝书逸 on 2018/8/12.
 */

public interface IArmyTrainingPresenter {
    void loadData();
    void loadPicture(String url, ImageView imageView,Context context);
}
