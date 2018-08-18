package com.mredrock.cyxbs.freshman.Tools.Album;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郝书逸 on 2018/5/26.
 */

public class Album_PagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private ArrayList<Fragment> Fragments;

    public Album_PagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        Fragments = fragments;
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
}

