package com.mredrock.cyxbs.freshman.Tools.Album;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by 郝书逸 on 2018/8/10.
 */

public class Album_Model implements IAlbumModel{

    @Override
    public void getPicture(String url,ImageView imageView,Context context ) {
        Glide.with(context)
                .load("http://47.106.33.112:8080/welcome2018"+url)
                .into(imageView);
    }


}
