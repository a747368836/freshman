package com.mredrock.cyxbs.freshman.Tools.Album;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 郝书逸 on 2018/8/10.
 */

interface IAlbumModel {
    void getPicture(String url, ImageView imageView, Context context);
}
