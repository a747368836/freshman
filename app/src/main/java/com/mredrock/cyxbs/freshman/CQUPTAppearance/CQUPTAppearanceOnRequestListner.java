package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Bean_Mien;
import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Bean_Tip;

/**
 * Created by 郝书逸 on 2018/8/11.
 */

public interface CQUPTAppearanceOnRequestListner {
    void OnSuccess(CQUPTAppearance_Bean_10 bean_union, CQUPTAppearance_Bean_10 bean_event);
    void OnError(String e);
}
