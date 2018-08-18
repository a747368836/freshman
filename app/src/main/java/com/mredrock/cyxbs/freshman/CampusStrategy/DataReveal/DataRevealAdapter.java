package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class DataRevealAdapter extends RecyclerView.Adapter<DataRevealAdapter.DataRevealViewHolder> {
    Context mContext;
    List<String> titles = new ArrayList<>();

    public DataRevealAdapter(Context context,List<String> list){
        this.mContext = context;
        this.titles = list;
    }

    @Override
    public DataRevealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.freshman_campus_data_recy_card,parent,false);
        DataRevealViewHolder holder = new DataRevealViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DataRevealViewHolder holder, int position) {
        holder.titleTextview.setText(titles.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DataDetailActivity.class);
                intent.putExtra("name",titles.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.titles.size();
    }

    public class DataRevealViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextview;
        public DataRevealViewHolder(View itemView) {
            super(itemView);
            titleTextview = itemView.findViewById(R.id.campuss_data_recy_card_title_textview);
        }
    }


    public void reFreshData(List<String> list){
        this.titles = list;
        notifyDataSetChanged();
    }




}
