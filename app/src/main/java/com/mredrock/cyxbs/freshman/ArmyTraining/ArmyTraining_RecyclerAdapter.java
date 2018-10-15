package com.mredrock.cyxbs.freshman.ArmyTraining;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.Tools.Album.Album_Activity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.http.Url;

import static android.content.ContentValues.TAG;

/**
 * Created by 郝书逸 on 2018/8/13.
 */

public class ArmyTraining_RecyclerAdapter extends RecyclerView.Adapter<ArmyTraining_RecyclerAdapter.ViewHolder>{
    //添加数据，如每个item中显示的图片文字或其url
    interface mOnClickListener extends View.OnClickListener{};
    private String[] video_url;
    private String[] video_name;
    private String[] video_pic;
    private Context context;
    ArmyTraining_RecyclerAdapter(String[] video_pic, String[] video_name, String[] video_url, Context context){
        this.video_url = video_url;
        this.video_pic=video_pic;
        this.video_name=video_name;
        this.context=context;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView videopic;
        ViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.armytraining_txt);
            videopic=view.findViewById(R.id.armytraining_videoImage);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.freshman_armytraining_fragment_video,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final ArmyTraining_RecyclerAdapter.ViewHolder holder, final int position) {
        holder.name.setText(video_name[position]);
        //holder.videopic.setAdjustViewBounds(true);
        ArmyTraining_PicLoader.getInstance().displayImage(context,video_pic[position],holder.videopic);
        holder.videopic.setOnClickListener((mOnClickListener) view -> {
            Uri uri = Uri.parse(video_url[position]);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        //获取item的数量（可以从url数量获得）
        return video_url.length;
    }
}
