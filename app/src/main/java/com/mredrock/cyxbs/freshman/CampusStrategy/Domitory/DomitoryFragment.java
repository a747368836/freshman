package com.mredrock.cyxbs.freshman.CampusStrategy.Domitory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEPresenter;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBERecyAdapter;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.IBBEPresenter;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.IBBEView;
import com.mredrock.cyxbs.freshman.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import static com.mredrock.cyxbs.freshman.FreshmanMainActivity.BASEURL;

@SuppressLint("ValidFragment")
public class DomitoryFragment extends Fragment implements IBBEView{
    /*TextView priceTextView;
    RelativeLayout foodTagRelativeLayout;
    TextView foodRankTextView;*/
    View view;
    RecyclerView recyclerView;
    Context mContext;
    RefreshLayout refreshLayout;
    List<BBEBean.ArrayBean> dataBean = new ArrayList<>();
    BBERecyAdapter adapter;
    String type = "";
    String name = "";

    @SuppressLint("ValidFragment")
    public DomitoryFragment(String type, Context context,String name) {
        super();
        this.type = type;
        this.mContext = context;
        this.name = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.freshman_campus_domitory_card_fragment, container, false);
        initView();


        adapter = new BBERecyAdapter(mContext,dataBean,type);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        BBEPresenter presenter = new BBEPresenter(this);

        presenter.loadList(BASEURL,type,1,100,adapter);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.loadList(BASEURL,type,1,100,refreshLayout,adapter);
            }
        });



        return view;
    }


    public void initView() {
        refreshLayout = view.findViewById(R.id.freshman_campus_domitory_recycler_smartRefreshlayout);
        recyclerView = view.findViewById(R.id.freshman_campus_domitory_recycler);
       /* priceTextView = view.findViewById(R.id.freshman_campus_recy_card_price_textview);
        foodTagRelativeLayout = view.findViewById(R.id.freshman_campus_recy_card_food_tag);
        foodRankTextView  = view.findViewById(R.id.freshman_campus_recy_card_food_rank_textview);
        priceTextView.setVisibility(View.GONE);
        foodTagRelativeLayout.setVisibility(View.GONE);
        foodRankTextView.setVisibility(View.GONE);*/

    }

    @Override
    public void showList(List<BBEBean.ArrayBean> dataList, BBERecyAdapter adapter) {
        List<BBEBean.ArrayBean> tmpList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getName().indexOf(name)!=-1) {
                tmpList.add(dataList.get(i));
            }
        }
        dataBean = tmpList;

        recyclerView.setAdapter(adapter);
        adapter.refreshDatabean(dataBean);
    }

    @Override
    public void showList(List<BBEBean.ArrayBean> dataList) {

    }

    @Override
    public void showList(List<BBEBean.ArrayBean> dataList, BBERecyAdapter adapter, String type) {
        switch (type){
            case "refresh":
                List<BBEBean.ArrayBean> tmpList = new ArrayList<>();
                for (int i = 0; i < dataList.size(); i++) {
                    if (dataList.get(i).getName().indexOf(name)!=-1) {
                        tmpList.add(dataList.get(i));
                    }
                }
                dataBean = tmpList;
                adapter.refreshDatabean(dataBean);
                break;
        }
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(mContext,errorMessage,Toast.LENGTH_SHORT).show();
    }
}
