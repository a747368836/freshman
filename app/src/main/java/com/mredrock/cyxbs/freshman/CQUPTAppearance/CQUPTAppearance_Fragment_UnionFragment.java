package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;

/**
 * Created by 郝书逸 on 2018/8/17.
 */

public class CQUPTAppearance_Fragment_UnionFragment extends Fragment{
    private ImageView Pic;
    private TextView Name;
    private TextView Content;
    private Button button;
    private String picurl;
    private String name;
    private String content;
    private int flag=0;

    public static CQUPTAppearance_Fragment_UnionFragment newInstance(String picurl,String name,String content){
        Bundle bundle=new Bundle();
        bundle.putString("picurl",picurl);
        bundle.putString("name",name);
        bundle.putString("content",content);
        CQUPTAppearance_Fragment_UnionFragment fragment = new CQUPTAppearance_Fragment_UnionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        picurl=bundle.getString("picurl");
        name=bundle.getString("name");
        content=bundle.getString("content");
        View view = inflater.inflate(R.layout.freshman_cquptappearance_fragment_unionfragment, container, false);
        Pic=view.findViewById(R.id.pic_cquptappearance_unionfragment);
        Name=view.findViewById(R.id.name_cquptappearance_unionfragment);
        Content=view.findViewById(R.id.content_cquptappearance_unionfragment);
        button=view.findViewById(R.id.button_cquptappearance_unionfragment);
        Name.setText(name);
        Content.setText(content);
        Pic.setAdjustViewBounds(true);
        CQUPTAppearance_PicLoader.getInstance().displayImage(getContext(),picurl,Pic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==0){
                    button.setBackgroundResource(R.drawable.freshman_essential_recy_card_up);
                    Content.setMaxLines(1000);
                    flag=1;
                }else{
                    button.setBackgroundResource(R.drawable.freshman_essential_recy_card_down);
                    Content.setMaxLines(6);
                    flag=0;
                }

            }
        });
        return view;
    }
}
