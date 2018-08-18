package com.mredrock.cyxbs.freshman.EssentialToRegister;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Message;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.CharacterPickerDialog;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.Tools.DisplayUtils;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;
import org.litepal.crud.LitePalSupport;


public class EssentialMainAdapter extends
        RecyclerView.Adapter<EssentialMainAdapter.EssentialViewHolder> {
    private Context mContext;
    private List<EssentialDataBean.DescribeBean> beanList;
    static ArrayList positionList = new ArrayList();
    ArrayList positionList1 = new ArrayList();
    static LinkedHashMap map = new LinkedHashMap();
    boolean isShow = true;
    Handler mHandler;
    int checks = 0;
    static int num = 0;



    public EssentialMainAdapter(Context context, List<EssentialDataBean.DescribeBean> bean, Handler handler) {
        mContext = context;
        beanList = bean;
        mHandler = handler;
        num = 0;
        positionList.clear();
        map.clear();
    }

    public boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }


    @Override
    public EssentialViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.freshman_essential_recy_card, viewGroup, false);
        EssentialViewHolder holder = new EssentialViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(final EssentialViewHolder holder, int i) {
        holder.MainTitleView.setText(beanList.get(i).getName());
        //按换行符分问题和回答来设置字体大小和色号,顺便隐藏掉用户输入的项目的下拉框
        if (beanList.get(i).getProperty().equals("自定义")) {
            holder.moreBox.setVisibility(View.INVISIBLE);
        } else {
            String[] str = beanList.get(i).getContent().split("\n");
            for (int j = 0; j < str.length; j++) {
                if (j == 0) {
                    holder.SonTitleView.setText(str[0]);
                } else {
                    holder.viewList.add(addTextView(str[j], j));
                }
            }

        }

        final int position = i;

        if (isShow) {
            holder.deleteBox.setVisibility(View.VISIBLE);
            holder.isCheckedBox.setVisibility(View.INVISIBLE);

            //这两个checkBox名字反了，反了。。就反了吧
            holder.deleteBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int tmp = 0;
                    //换位置没bug了
                    if (b) {
                        holder.MainTitleView.setTextColor(mContext.getResources().getColor(R.color.darkText));
                        notifyItemMoved(positionList.indexOf(beanList.get(position).getName()), num);
                        positionList.remove(beanList.get(position).getName());
                        positionList.add(num, beanList.get(position).getName());
                        num++;
                    } else {
                        holder.MainTitleView.setTextColor(mContext.getResources().getColor(R.color.normalText));
                        notifyItemMoved(positionList.indexOf(beanList.get(position).getName()), beanList.size() - 1);
                        positionList.remove(beanList.get(position).getName());
                        positionList.add(beanList.size() - 1, beanList.get(position).getName());
                        num--;
                    }

                }
            });

        } else {
            boolean showCheckBox = beanList.get(position).getProperty().equals("必需");
            positionList.clear();
            holder.deleteBox.setVisibility(View.INVISIBLE);
            if (showCheckBox) {
                holder.isCheckedBox.setVisibility(View.INVISIBLE);
            } else {
                holder.isCheckedBox.setVisibility(View.VISIBLE);
            }

            holder.isCheckedBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        checks++;
                        beanList.get(position).setChecked(true);
                        ContentValues values = new ContentValues();
                        values.put("isChecked", "true");
                        LitePal.updateAll(EssentialDataBean.DescribeBean.class, values, "name = ?", beanList.get(i).getName());
                        Message message = Message.obtain();
                        message.arg1 = checks;
                        mHandler.sendMessage(message);
                    } else {
                        checks--;
                        beanList.get(position).setChecked(false);
                        ContentValues values = new ContentValues();
                        values.put("isChecked", "false");
                        LitePal.updateAll(EssentialDataBean.DescribeBean.class, values, "name = ?", beanList.get(i).getName());
                    }
                }
            });
        }

        holder.moreBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    holder.SonTitleView.setVisibility(View.VISIBLE);
                    for (int j = 0; j < holder.viewList.size(); j++) {
                        holder.viewList.get(j).setVisibility(View.VISIBLE);
                        holder.contentRelativeLayout.addView(holder.viewList.get(j));
                    }

                } else {
                    for (int j = 0; j < holder.viewList.size(); j++) {
                        holder.contentRelativeLayout.removeView(holder.viewList.get(j));
                        holder.viewList.get(j).setVisibility(View.GONE);
                    }
                    holder.SonTitleView.setVisibility(View.GONE);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        checks = 0;
        return beanList.size();
    }


    public class EssentialViewHolder extends RecyclerView.ViewHolder {

        TextView MainTitleView;
        TextView SonTitleView;
        TextView deleteTextview;
        CheckBox moreBox;
        CheckBox isCheckedBox;
        CheckBox deleteBox;
        RecyclerView recyclerView;
        RelativeLayout contentRelativeLayout;

        private List<TextView> viewList = new ArrayList<>();
        com.mredrock.cyxbs.freshman.Tools.FixedCardview.CardView recyCardView;

        public EssentialViewHolder(View itemView) {
            super(itemView);
            MainTitleView = itemView.findViewById(R.id.freshman_essential_recy_card_title_textview);
            SonTitleView = itemView.findViewById(R.id.freshman_essential_recy_card_sontitle1_textview);
            moreBox = itemView.findViewById(R.id.freshman_essential_recy_card_more_checkbox);
            isCheckedBox = itemView.findViewById(R.id.freshman_essential_recy_card_checkbox);
            deleteTextview = itemView.findViewById(R.id.freshman_essential_main_toolbar_edit_textview);
            deleteBox = itemView.findViewById(R.id.freshman_essential_recy_card_delete_checkbox);
            recyclerView = itemView.findViewById(R.id.freshman_essential_main_recycler);
            recyCardView = itemView.findViewById(R.id.freshman_essential_recy_cardView);
            contentRelativeLayout = itemView.findViewById(R.id.freshman_campus_bbe_recy_card_relativelayout);
        }


    }


    public void upDateDataBean(List<EssentialDataBean.DescribeBean> beanList) {
        this.beanList = beanList;
        positionList.clear();
        num = 0;
        notifyDataSetChanged();
        for (int i = 0; i < beanList.size(); i++) {
            positionList.add(i, beanList.get(i).getName());
        }

    }

    public void reSetPositionList() {
        positionList.clear();
    }


    private TextView addTextView(String content, int type) {
        TextView textView = new TextView(mContext);
        textView.setText(content);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(DisplayUtils.dp2px(mContext, 49)
                , type % 2 == 0 ? type * DisplayUtils.dp2px(mContext, 20) : type * DisplayUtils.dp2px(mContext, 10)
                , 0, DisplayUtils.dp2px(mContext,15));
        lp.addRule(RelativeLayout.BELOW, R.id.freshman_essential_recy_card_sontitle1_textview);
        textView.setLayoutParams(lp);
        if (type % 2 != 0) {
            textView.setTextColor(mContext.getResources().getColor(R.color.freshman_gray_text));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.freshman_black_text));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        }

        textView.setVisibility(View.VISIBLE);
        return textView;
    }


}





