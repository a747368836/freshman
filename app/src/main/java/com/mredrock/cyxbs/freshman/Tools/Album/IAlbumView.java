package com.mredrock.cyxbs.freshman.Tools.Album;

import android.widget.ImageView;



/**
 * Created by 郝书逸 on 2018/8/10.
 */

interface IAlbumView {
    void startViewPager(Album_PagerAdapter adapter);
    ImageView getImageView();
}
