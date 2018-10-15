package com.mredrock.cyxbs.freshman.ArmyTraining;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.Tools.PicLoader.GlideRoundTransform;
import com.youth.banner.loader.ImageLoader;



/**
 * Created by 郝书逸 on 2018/8/12.
 */

public class ArmyTraining_PicLoader extends ImageLoader {
    private ArmyTraining_PicLoader() {

    }




    private static final ArmyTraining_PicLoader picLoader = new ArmyTraining_PicLoader();



    //对外提供方法，返回实例对象

    public static ArmyTraining_PicLoader getInstance() {

        return picLoader;

    }
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
        //改回path
            Glide.with(context)
                    .load("http://47.106.33.112:8080/welcome2018"+path)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageView);
        }



    public static int dp2px(Context context, float dpValue) {

        float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);

    }

}

