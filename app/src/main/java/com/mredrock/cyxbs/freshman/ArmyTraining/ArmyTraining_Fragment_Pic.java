package com.mredrock.cyxbs.freshman.ArmyTraining;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;

/**
 * Created by 郝书逸 on 2018/8/12.
 */

public class ArmyTraining_Fragment_Pic extends Fragment {
    ImageView imageView;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.freshman_armytraining_fragment_video , container, false);
        imageView=view.findViewById(R.id.armytraining_videoImage);
        textView=view.findViewById(R.id.armytraining_txt);
        return view;
    }

    public void show(String title,String video_url){
        textView.setText(title);
        ArmyTraining_PicLoader.getInstance().displayImage(getContext(),video_url,imageView);
    }
}
