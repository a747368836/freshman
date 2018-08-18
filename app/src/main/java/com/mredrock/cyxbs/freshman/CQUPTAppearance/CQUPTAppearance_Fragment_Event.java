package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Fragment_Mien;
import com.mredrock.cyxbs.freshman.R;

/**
 * Created by 郝书逸 on 2018/8/15.
 */

public class CQUPTAppearance_Fragment_Event extends Fragment implements ICQUPTAppearanceView{
    RecyclerView recyclerView;

    public static CQUPTAppearance_Fragment_Event newInstance(){
        CQUPTAppearance_Fragment_Event fragment = new CQUPTAppearance_Fragment_Event();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.freshman_cquptappearance_fragment_event, container, false);
        recyclerView=view.findViewById(R.id.recycler_cquptappearance_event);
        return view;
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void show( CQUPTAppearance_Bean_10 bean_event) {
        int amount=bean_event.getArray().size();
        String[] name=new String[amount];
        String[] pic=new String[amount];
        String[] content=new String[amount];
        String[][] pic_url=new String[amount][];
        for (int i = 0; i <amount ; i++) {
            name[i]=bean_event.getArray().get(i).getName();
            content[i]=bean_event.getArray().get(i).getContent();
            pic[i]=bean_event.getArray().get(i).getPicture().get(0);
            pic_url[i]=new String[bean_event.getArray().get(i).getPicture().size()];
            bean_event.getArray().get(i).getPicture().toArray(pic_url[i]);
        }
        CQUPTAppearance_RecyclerAdapter_Event adapter=new CQUPTAppearance_RecyclerAdapter_Event(pic_url,pic,name,content,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
