package  com.mredrock.cyxbs.freshman.ArmyTraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;


import com.bumptech.glide.Glide;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.Tools.Album.Album_Activity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static android.content.ContentValues.TAG;
import static com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Activity.fragmentManager;

/**
 * Created by 郝书逸 on 2018/8/9.
 */

public class ArmyTraining_Fragment_Mien extends Fragment implements IArmyTrainingView ,OnBannerListener{
    private Banner banner_pic;
    ArrayList<Fragment> videofragments;
    private RecyclerView recyclerView;
    private ArmyTraining_RecyclerAdapter adapter;
    private String[]pic_url;
    private String[]video_url;
    private String[]video_pic;
    private String[]pic_title;
    private String[]video_title;
    private int pic_num;
    private int video_num;
    public static ArmyTraining_Fragment_Mien newInstance(){
        ArmyTraining_Fragment_Mien fragment = new ArmyTraining_Fragment_Mien();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.freshman_armytraining_fragment_mien, container, false);
        banner_pic=view.findViewById(R.id.banner_armytraining_pic);
        recyclerView=view.findViewById(R.id.recycler_armytraining_video);
        return view;
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void show(ArmyTraining_Bean_Mien bean_mien, ArmyTraining_Bean_Tip bean_tip) {
        pic_num=bean_mien.getPicture().size();
        video_num=bean_mien.getVideo().size();
        picToArray(bean_mien.getPicture());
        videoToArray(bean_mien.getVideo());
        List<String>pictitlelist=Arrays.asList(pic_title);
        banner_pic.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setImageLoader(ArmyTraining_PicLoader.getInstance())
                .setImages(Arrays.asList(pic_url))
                .setBannerTitles(pictitlelist)
                .setBannerAnimation(Transformer.BackgroundToForeground)
                .setDelayTime(5000)
                .isAutoPlay(true)
                .setOnBannerListener(this)
                .start();
        adapter=new ArmyTraining_RecyclerAdapter(video_pic,video_title,video_url,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnBannerClick(int position) {
        Intent intent =new Intent(getActivity(), Album_Activity.class);
        intent.putExtra("url",pic_url);
        startActivity(intent);
    }
    private void picToArray(List<ArmyTraining_Bean_Mien.PictureBean> list){
        pic_title=new String[list.size()];
        pic_url=new String[list.size()];
        for (int i = 0; i <list.size() ; i++) {
            pic_title[i]=list.get(i).getName();
            pic_url[i]=list.get(i).getUrl();
        }
    }
    private void videoToArray(List<ArmyTraining_Bean_Mien.VideoBean> list){
        video_title=new String[list.size()];
        video_url=new String[list.size()];
        video_pic=new String[list.size()];
        for (int i = 0; i <list.size() ; i++) {
            video_pic[i]=list.get(i).getVideo_pic().getUrl();
            video_title[i]=list.get(i).getVideo_pic().getName();
            video_url[i]=list.get(i).getUrl();
        }
    }
}
