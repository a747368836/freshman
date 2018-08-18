package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_Activity_Video;
import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_PicLoader;
import com.mredrock.cyxbs.freshman.ArmyTraining.ArmyTraining_RecyclerAdapter;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.Tools.Album.Album_Activity;

/**
 * Created by 郝书逸 on 2018/8/16.
 */

public class CQUPTAppearance_RecyclerAdapter_Event extends RecyclerView.Adapter<CQUPTAppearance_RecyclerAdapter_Event.ViewHolder>{
    //添加数据，如每个item中显示的图片文字或其url
    interface mOnClickListener extends View.OnClickListener{};
    private String[] event_name;
    private String[] event_pic;
    private String[] event_content;
    private String[][] event_picurl;
    private Context context;
    public CQUPTAppearance_RecyclerAdapter_Event(String[][]event_picurl,String[]event_pic,String[]event_name,String[]event_content, Context context){
        this.event_picurl=event_picurl;
        this.event_pic=event_pic;
        this.event_content=event_content;
        this.event_name=event_name;
        this.context=context;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView content;
        ImageView eventpic;
        public ViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.name_cquptappearance_eventfragment);
            content=view.findViewById(R.id.content_cquptappearance_eventfragment);
            eventpic=view.findViewById(R.id.pic_cquptappearance_eventfragment);
        }
    }

    @Override
    public CQUPTAppearance_RecyclerAdapter_Event.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.freshman_cquptappearance_fragment_eventfragment,parent,false);
        CQUPTAppearance_RecyclerAdapter_Event.ViewHolder holder = new CQUPTAppearance_RecyclerAdapter_Event.ViewHolder(view);
        return holder;
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final CQUPTAppearance_RecyclerAdapter_Event.ViewHolder holder, final int position) {
        holder.name.setText(event_name[position]);
        holder.content.setText(event_content[position]);
        holder.eventpic.setAdjustViewBounds(true);
        CQUPTAppearance_PicLoader.getInstance().displayImage(context,event_pic[position],holder.eventpic);
        holder.eventpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, Album_Activity.class);
                intent.putExtra("url",event_picurl[position]);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        //获取item的数量（可以从url数量获得）
        return event_name.length;
    }
}
