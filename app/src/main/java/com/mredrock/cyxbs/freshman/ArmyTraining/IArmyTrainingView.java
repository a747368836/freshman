package com.mredrock.cyxbs.freshman.ArmyTraining;

import android.content.Context;

/**
 * Created by 郝书逸 on 2018/8/12.
 */

public interface IArmyTrainingView {
    void showError(String errorMessage);
    void show(ArmyTraining_Bean_Mien bean_mien, ArmyTraining_Bean_Tip bean_tip);
}
