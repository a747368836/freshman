package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;

import java.util.List;

/**
 * Created by 郝书逸 on 2018/8/17.
 */

public class CQUPTAppearance_TabPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private List<Fragment> Fragments;
    private Context context;

    public CQUPTAppearance_TabPagerAdapter(FragmentManager fm, List<Fragment> fragments, Context context) {
        super(fm);
        this.context=context;
        Fragments = fragments;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return Fragments.get(position);
    }

    @Override
    public int getCount() {
        return Fragments.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment;
        fragment = (Fragment) super.instantiateItem(container, position);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
    public View getCustomView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.freshman_cquptappearance_mytab, null);
        TextView tv = (TextView) view.findViewById(R.id.title_cquptappearance_mytablayout);
        tv.setText(titles[position]);
        return view;
    }
}
