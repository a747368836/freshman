package com.mredrock.cyxbs.freshman.ArmyTraining;

/**
 * Created by 郝书逸 on 2018/8/11.
 */

public interface ArmyTrainingOnRequestListner {
    void OnSuccess(ArmyTraining_Bean_Mien bean_mien,ArmyTraining_Bean_Tip bean_tip);
    void OnError(String e);
}
