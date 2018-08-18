package com.mredrock.cyxbs.freshman.Tools.Album;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;


import java.util.ArrayList;

/**
 * Created by 郝书逸 on 2018/8/10.
 */

public class Album_Presenter implements IAlbumPresenter {
    private Album_Model model;
    private IAlbumView albumview;
    private Album_PagerAdapter adapter;
    FragmentManager fragmentManager;
    ArrayList<Fragment>fragments;
    String[]picurl;

    public Album_Presenter(IAlbumView albumview, String[]picurl, Context context, FragmentManager fragmentManager){
        model=new Album_Model();
        this.picurl=picurl;
        this.albumview=albumview;
        this.fragmentManager=fragmentManager;
        initdata();


    }

    private void initdata(){
        fragments = new ArrayList<>();
        for (int i = 0; i < picurl.length; i++) {
            fragments.add(Album_Fragment.newInstance(picurl[i]));
        }
        adapter = new Album_PagerAdapter(fragmentManager,fragments);
        albumview.startViewPager(adapter);
    }

    @Override
    public void setImage(String url, ImageView imageView, Context context) {

    }
}
