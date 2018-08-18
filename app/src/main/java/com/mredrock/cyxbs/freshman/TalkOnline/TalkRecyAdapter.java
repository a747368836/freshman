package com.mredrock.cyxbs.freshman.TalkOnline;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.R;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;

import java.util.List;

public class TalkRecyAdapter extends RecyclerView.Adapter<TalkRecyAdapter.TalkRecyViewHolder> {

    Context mContext;
    List<TalkBean.ArrayBean.Array1Bean> bean;

    public TalkRecyAdapter(Context context, List<TalkBean.ArrayBean.Array1Bean> bean){
        this.mContext = context;
        this.bean = bean;
        System.out.println(mContext.toString()+"666");
    }
    @Override
    public void onBindViewHolder(TalkRecyViewHolder holder, int position) {
        holder.textView.setText(bean.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(bean.get(position).getCode());
                Toast.makeText(mContext,"已复制到剪切板",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    @Override
    public TalkRecyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.freshman_talk_recy_card,parent,false);
        TalkRecyViewHolder holder = new TalkRecyViewHolder(view);
        return holder;
    }

    public class TalkRecyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public TalkRecyViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.freshman_talk_recy_card_content_textview);
        }

}






    public void refreshDatabean(List<TalkBean.ArrayBean.Array1Bean> beanList) {
        this.bean = beanList;

        notifyDataSetChanged();
    }
}
