package com.mredrock.cyxbs.freshman.CampusStrategy.BBE;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maning.imagebrowserlibrary.ImageEngine;
import com.maning.imagebrowserlibrary.MNImageBrowser;
import com.maning.imagebrowserlibrary.listeners.OnClickListener;
import com.maning.imagebrowserlibrary.model.ImageBrowserConfig;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.Tools.DisplayUtils;
import com.mredrock.cyxbs.freshman.Tools.FixedCardview.CardView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.mredrock.cyxbs.freshman.FreshmanMainActivity.BASEURL;


//整个接口9的Adapter全是这个，乱。。。。。 ps：接口9请求的时候都是一页一百条数据，毕竟数据量小
//等考核过完去写个好点的上拉加载下拉刷新的模板。。

public class BBERecyAdapter extends RecyclerView.Adapter<BBERecyAdapter.BBERecyViewHolder> {
    Context mContext;
    List<BBEBean.ArrayBean> bean;
    //下面三个是图片库的三个常量。。
    public ImageBrowserConfig.TransformType transformType = ImageBrowserConfig.TransformType.Transform_Default;
    public ImageBrowserConfig.IndicatorType indicatorType = ImageBrowserConfig.IndicatorType.Indicator_Number;
    public ImageBrowserConfig.ScreenOrientationType screenOrientationType = ImageBrowserConfig.ScreenOrientationType.Screenorientation_Default;

    List<String> picList = new ArrayList<>();

    private ImageEngine imageEngine = new GlideImageEngine();

    String type = "";

    public BBERecyAdapter(Context context, List<BBEBean.ArrayBean> bean, String type) {
        this.bean = bean;
        this.mContext = context;
        this.type = type;

    }




    @Override
    public BBERecyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = null;
        switch (type) {
            case "附近银行":
            case "快递收发":
                view = LayoutInflater.from(mContext).inflate(R.layout.freshman_camous_bbe_recy_card, viewGroup, false);
                break;
            case "报道流程":
                view = LayoutInflater.from(mContext).inflate(R.layout.freshman_regist_recy_card, viewGroup, false);
                break;
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.freshman_campus_recy_card, viewGroup, false);
                break;
        }
        BBERecyViewHolder holder = new BBERecyViewHolder(view);
        return holder;
    }

    //接口没给的时候的测试图地址
    public String picUrl = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/" +
            "timg?image&quality=100" +
            "&size=b4000_4000&sec=1533980678&di=33fc066ded32c581a9b67f9fc16a391d" +
            "&src=http://pic21.photophoto.cn/20111029/0005018383699162_b.jpg";


    @Override
    public void onBindViewHolder(BBERecyViewHolder holder, int i) {

        switch (type) {
            case "快递收发":
            case "附近银行":
                holder.titleView1.setText(bean.get(i).getName());
                holder.contentView1.setText(bean.get(i).getContent());
                Glide.with(mContext)
                        .load(picList.get(i))
                        .into(holder.showImageimageView);
                //点击大图
                holder.showImageimageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList list = new ArrayList();
                        list.add(picList.get(i));
                        MNImageBrowser.with(mContext)
                                .setCurrentPosition(0)
                                .setImageList(list)
                                .setTransformType(transformType)
                                .setIndicatorType(indicatorType)
                                .setImageEngine(imageEngine)
                                .setScreenOrientationType(screenOrientationType)
                                .show(holder.showImageimageView);

                    }
                });

                break;
            case "报道流程":
                holder.registTitleView.setText(bean.get(i).getName());
                holder.registTitleView1.setText(bean.get(i).getContent());
                Glide.with(mContext)
                        .load(picList.get(0))
                        .into(holder.imageView1);
                Glide.with(mContext)
                        .load(picList.get(1))
                        .into(holder.imageView2);
                //展开收齐箭头的点击监听
                holder.downImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewGroup.LayoutParams lp = holder.registBigCard.getLayoutParams();
                        lp.height = WRAP_CONTENT;
                        holder.registBigCard.setLayoutParams(lp);
                        holder.expendView.setVisibility(View.VISIBLE);
                        lp = holder.registTitleView1.getLayoutParams();
                        lp.height = WRAP_CONTENT;
                        holder.registTitleView1.setLayoutParams(lp);
                        holder.registTitleView1.setPadding(0,0,0,DisplayUtils.dp2px(mContext,140));
                        holder.upImageView.setVisibility(View.VISIBLE);
                        holder.downImageView.setVisibility(View.INVISIBLE);
                    }
                });
                holder.upImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.downImageView.setVisibility(View.VISIBLE);
                        holder.upImageView.setVisibility(View.INVISIBLE);
                        ViewGroup.LayoutParams lp = holder.registBigCard.getLayoutParams();
                        lp.height = DisplayUtils.dp2px(mContext, 345);
                        holder.expendView.setVisibility(View.GONE);
                        holder.registBigCard.setLayoutParams(lp);

                        lp = holder.registTitleView1.getLayoutParams();
                        lp.height = DisplayUtils.dp2px(mContext, 85);
                        holder.registTitleView1.setPadding(0,0,0,0);
                        holder.registTitleView1.setLayoutParams(lp);
                    }
                });
                //两个Imageview的点击大图
                holder.imageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList list = new ArrayList();
                        list.add(picList.get(0));
                        MNImageBrowser.with(mContext)
                                .setCurrentPosition(0)
                                .setImageList(list)
                                .setTransformType(transformType)
                                .setIndicatorType(indicatorType)
                                .setImageEngine(imageEngine)
                                .setScreenOrientationType(screenOrientationType)
                                .show(holder.imageView1);
                    }
                });
                holder.imageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList list = new ArrayList();
                        list.add(picList.get(1));
                        MNImageBrowser.with(mContext)
                                .setCurrentPosition(0)
                                .setImageList(list)
                                .setTransformType(transformType)
                                .setIndicatorType(indicatorType)
                                .setImageEngine(imageEngine)
                                .setScreenOrientationType(screenOrientationType)
                                .show(holder.imageView2);
                    }
                });

                break;

            default:
                //周边美食有点特殊。这么处理下算了= =
                if (type.equals("周边美食")) {
                    holder.foodTagRelativeLayout.setVisibility(View.VISIBLE);
                    holder.priceTextView.setVisibility(View.VISIBLE);
                    holder.priceTextView.setText("¥"+bean.get(i).getProperty()+"(人)");
                    holder.foodRankTextView.setText(String.valueOf(i + 1));
                } else {
                    holder.foodTagRelativeLayout.setVisibility(View.INVISIBLE);
                    holder.priceTextView.setVisibility(View.INVISIBLE);
                }
                holder.titleView.setText(bean.get(i).getName());
                holder.contentView.setText(bean.get(i).getContent());
                List<String> itemPicList = new ArrayList();
                for (int j = 0; j < bean.get(i).getPicture().size(); j++) {
                    itemPicList.add(BASEURL+bean.get(i).getPicture().get(j));
                }

                holder.banner.setImageLoader(new GlideImageLoader());
                holder.banner.setImages(itemPicList);

                //对轮播图点击放大的监听
                holder.banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                        ArrayList list = new ArrayList(itemPicList);
                        MNImageBrowser.with(mContext)
                                .setCurrentPosition(position)
                                .setImageList(list)
                                .setTransformType(transformType)
                                .setIndicatorType(indicatorType)
                                .setImageEngine(imageEngine)
                                .setScreenOrientationType(screenOrientationType)
                                .show(holder.banner);
                    }
                });
                //banner.start必须等banner全部设置完再调用
                holder.banner.start();


        }


    }

    @Override
    public int getItemCount() {
        return bean.size();
    }

    public class BBERecyViewHolder extends RecyclerView.ViewHolder {
        //命名困难
        TextView titleView;
        TextView contentView;
        TextView titleView1;
        TextView contentView1;
        ImageView showImageimageView;
        Banner banner;
        TextView priceTextView;
        RelativeLayout foodTagRelativeLayout;
        TextView foodRankTextView;
        ImageView imageView1;
        ImageView imageView2;
        ImageView upImageView;
        ImageView downImageView;
        TextView registTitleView;
        TextView registTitleView1;
        CardView registBigCard;
        View expendView;


        public BBERecyViewHolder(View itemView) {
            super(itemView);
            switch (type) {
                case "附近银行":
                case "快递收发":
                    titleView1 = itemView.findViewById(R.id.freshman_campus_bbe_recy_card_title_textview);
                    showImageimageView = itemView.findViewById(R.id.freshman_campus_bbe_recy_card_imageview);
                    contentView1 = itemView.findViewById(R.id.freshman_campus_bbe_recy_card_content_textview_textview);
                    break;
                case "报道流程":
                    expendView = itemView.findViewById(R.id.freshman_regist_recy_card_view);
                    registBigCard = itemView.findViewById(R.id.freshman_regist_recy_card_BigCard);
                    registTitleView = itemView.findViewById(R.id.freshman_regist_recy_card_title);
                    registTitleView1 = itemView.findViewById(R.id.freshman_regist_recy_card_content);
                    imageView1 = itemView.findViewById(R.id.freshman_regist_recy_card_imageview1);
                    imageView2 = itemView.findViewById(R.id.freshman_regist_recy_card_imageview2);
                    upImageView = itemView.findViewById(R.id.freshman_regist_recy_card_up_imageview);
                    downImageView = itemView.findViewById(R.id.freshman_regist_recy_card_down_imageview);
                    break;
                default:
                    foodRankTextView = itemView.findViewById(R.id.freshman_campus_recy_card_food_rank_textview);
                    priceTextView = itemView.findViewById(R.id.freshman_campus_recy_card_price_textview);
                    foodTagRelativeLayout = itemView.findViewById(R.id.freshman_campus_recy_card_food_tag);
                    titleView = itemView.findViewById(R.id.freshman_campus_recy_card_title_textview);
                    contentView = itemView.findViewById(R.id.freshman_campus_recy_card_content_textview);
                    banner = itemView.findViewById(R.id.freshman_campus_recy_card_imageScroll_banner);
            }
        }
    }


    public class GlideImageEngine implements ImageEngine {

        @Override
        public void loadImage(Context context, final String url, ImageView imageView) {
            //没给展位图就随意吧。。。反正就自己看看、、
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .fitCenter()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        }

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            PhotoView photoView = new PhotoView(context);
            return photoView;
        }


    }

    //emm一个给不是RelativeLayout下控件的控件设置margin的小方法，没用上就是了
    public  void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }

    }

    //外部更新数据的方法
    public void refreshDatabean(List<BBEBean.ArrayBean> beanList) {
        this.bean = beanList;
        notifyDataSetChanged();
        picList.clear();
        for (int i = 0; i < bean.size(); i++) {
            if (beanList.get(i).getPicture().size() > 0) {
                for (int j = 0; j < bean.get(i).getPicture().size(); j++) {
                    picList.add(BASEURL + bean.get(i).getPicture().get(j));
                }
            } else {
                picList.add(picUrl);
            }

        }
    }
}
