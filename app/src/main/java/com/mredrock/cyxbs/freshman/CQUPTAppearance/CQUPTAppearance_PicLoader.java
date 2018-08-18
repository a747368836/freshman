package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import com.mredrock.cyxbs.freshman.R;
import com.youth.banner.loader.ImageLoader;



/**
 * Created by 郝书逸 on 2018/8/12.
 */

public class CQUPTAppearance_PicLoader extends ImageLoader {
    private CQUPTAppearance_PicLoader() {

    }




    private static final CQUPTAppearance_PicLoader picLoader = new CQUPTAppearance_PicLoader();



    //对外提供方法，返回实例对象

    public static CQUPTAppearance_PicLoader getInstance() {

        return picLoader;

    }
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
        //改回path
            Glide.with(context)
                    .load("http://47.106.33.112:8080/welcome2018"+path)
                    /*.apply(RequestOptions.bitmapTransform(new MultiTransformation(
                            new RoundedCornersTransformation(dp2px(context,5), 0, RoundedCornersTransformation.CornerType.ALL))))
*/
                    .into(imageView);
        }


    public static int dp2px(Context context, float dpValue) {

        float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);

    }

}

