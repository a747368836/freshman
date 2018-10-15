package com.mredrock.cyxbs.freshman.Tools.Album;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;


/**
 * Created by 郝书逸 on 2018/8/10.
 */

public class Album_Fragment extends Fragment implements IAlbumView{
    private Album_Fragment_Presenter presenter;
    private ImageView imageView;
    private String murl;
    public static Album_Fragment newInstance(String murl){
        Bundle bundle = new Bundle();
        bundle.putString("murl",murl);
        Album_Fragment fragment = new Album_Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            murl = bundle.getString("murl");
            Log.d("TAG", "onCreateView: 000000000000000000000"+murl);
        }
        View view = inflater.inflate(R.layout.freshman_album_fragment , container, false);
        imageView=view.findViewById(R.id.pic_album);
        presenter=new Album_Fragment_Presenter(this,murl,getContext());
        return view;
    }

    @Override
    public void startViewPager(Album_PagerAdapter adapter) {

    }
    @Override
    public ImageView getImageView() {
        return imageView;
    }

}
