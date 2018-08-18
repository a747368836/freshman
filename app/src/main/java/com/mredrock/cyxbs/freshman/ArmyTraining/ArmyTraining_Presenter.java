package com.mredrock.cyxbs.freshman.ArmyTraining;

import android.content.Context;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.EssentialToRegister.EssentialDataBean;
import com.mredrock.cyxbs.freshman.EssentialToRegister.EssentialOnRequesListner;

import java.util.List;

/**
 * Created by 郝书逸 on 2018/8/12.
 */

public class ArmyTraining_Presenter implements IArmyTrainingPresenter {
    private ArmyTraining_Model model;
    private IArmyTrainingView armyTrainingView;
    private IArmyTrainingView armyTrainingFragment_Mien;
    private IArmyTrainingView armyTrainingFragment_Tip;
;    ArmyTraining_Presenter(IArmyTrainingView armyTrainingView,IArmyTrainingView armyTrainingFragment_Mien,IArmyTrainingView armyTrainingFragment_Tip){
        model=new ArmyTraining_Model();
        this.armyTrainingView=armyTrainingView;
        this.armyTrainingFragment_Mien=armyTrainingFragment_Mien;
        this.armyTrainingFragment_Tip=armyTrainingFragment_Tip;
        loadData();
    }
    @Override
    public void loadData() {
        model.RequestData(new ArmyTrainingOnRequestListner() {

            @Override
            public void OnSuccess(ArmyTraining_Bean_Mien bean_mien, ArmyTraining_Bean_Tip bean_tip) {
                armyTrainingView.show(bean_mien,bean_tip);
                armyTrainingFragment_Mien.show(bean_mien,bean_tip);
                armyTrainingFragment_Tip.show(bean_mien,bean_tip);
            }

            @Override
            public void OnError(String e) {
                armyTrainingView.showError(e);
            }
        });

    }

    @Override
    public void loadPicture(String url, ImageView imageView,Context context) {
        model.getPicture(url,imageView,context);
    }
}
