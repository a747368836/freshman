package com.mredrock.cyxbs.freshman.ArmyTraining;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;


/**
 * Created by 郝书逸 on 2018/8/9.
 */

public class ArmyTraining_Fragment_Tip extends Fragment implements IArmyTrainingView{
    TextView content1;
    TextView title1;
    TextView content2;
    TextView title2;
    public static ArmyTraining_Fragment_Tip newInstance(){
        ArmyTraining_Fragment_Tip fragment = new ArmyTraining_Fragment_Tip();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.freshman_armytrainning_fragment_tip , container, false);
        title1=(TextView)view.findViewById(R.id.title1_armytraining);
        content1=(TextView)view.findViewById(R.id.tip1_armytraining);
        title2=(TextView)view.findViewById(R.id.title2_armytraining);
        content2=(TextView)view.findViewById(R.id.tip2_armytraining);
        return view;
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void show(ArmyTraining_Bean_Mien bean_mien, ArmyTraining_Bean_Tip bean_tip) {
        title1.setText(bean_tip.getDescribe().get(0).getContent());
        content1.setText(bean_tip.getDescribe().get(1).getContent());
        title2.setText(bean_tip.getDescribe().get(2).getContent());
        content2.setText(bean_tip.getDescribe().get(3).getContent());
    }
}
