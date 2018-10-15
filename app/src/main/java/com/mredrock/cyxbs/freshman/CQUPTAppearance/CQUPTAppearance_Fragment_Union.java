package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郝书逸 on 2018/8/15.
 */

public class CQUPTAppearance_Fragment_Union extends Fragment implements ICQUPTAppearanceView{

    TabLayout tablayout;
    ViewPager viewPager;


    public static CQUPTAppearance_Fragment_Union newInstance(){
        CQUPTAppearance_Fragment_Union fragment = new CQUPTAppearance_Fragment_Union();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.freshman_cquptappearance_fragment_union, container, false);
        viewPager=view.findViewById(R.id.viewpager_cquptappearance_union);
        tablayout=view.findViewById(R.id.mytablayout_cquptappearance_union);
        return view;
    }


    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void show(CQUPTAppearance_Bean_10 bean_union) {
        String[] title=new String[bean_union.getArray().size()];
        List<Fragment>fragments=new ArrayList<Fragment>();
        for (int i = 0; i <bean_union.getArray().size(); i++) {
            String picurl=bean_union.getArray().get(i).getPicture().get(0);
            String name=bean_union.getArray().get(i).getName();
            title[i]=name;
            String content=bean_union.getArray().get(i).getContent();
            fragments.add(CQUPTAppearance_Fragment_UnionFragment.newInstance(picurl,name,content));
        }
        CQUPTAppearance_TabPagerAdapter adapter=new CQUPTAppearance_TabPagerAdapter(getFragmentManager(),fragments,getContext());
        adapter.setTitles(title);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
        for (int i = 0; i < bean_union.getArray().size(); i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            tab.setCustomView(adapter.getCustomView(i));
            if (i == 0) {
                ((TextView) tab.getCustomView().findViewById(R.id.title_cquptappearance_mytablayout)).setTextColor(ContextCompat.getColor(getContext(),R.color.freshman_app_blue));
            }
        }
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.title_cquptappearance_mytablayout)).setTextColor(ContextCompat.getColor(getContext(),R.color.freshman_app_blue));
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.title_cquptappearance_mytablayout)).setTextColor(ContextCompat.getColor(getContext(),R.color.freshman_text_grey));
            }
            @Override public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }
}
