package com.mredrock.cyxbs.freshman.Tools.Album;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 郝书逸 on 2018/8/10.
 */

public class Album_Fragment_Presenter implements IAlbumPresenter{
    private Album_Model model;
    private IAlbumView albumview;
    public Album_Fragment_Presenter(IAlbumView albumview, String url, Context context){
        model=new Album_Model();
        this.albumview=albumview;
        setImage(url,albumview.getImageView(),context);
    }

    @Override
    public void setImage(String url, ImageView imageView, Context context) {
        model.getPicture(url,imageView,context);
    }
}
