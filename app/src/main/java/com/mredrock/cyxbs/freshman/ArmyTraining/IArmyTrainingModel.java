package com.mredrock.cyxbs.freshman.ArmyTraining;

import android.content.Context;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.EssentialToRegister.EssentialDataBean;
import com.mredrock.cyxbs.freshman.EssentialToRegister.EssentialOnRequesListner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郝书逸 on 2018/8/12.
 */

public interface IArmyTrainingModel {
    void RequestData(ArmyTrainingOnRequestListner onRequestListner);
    void getPicture(String url, ImageView imageView, Context context);
    void getBanner();
}
