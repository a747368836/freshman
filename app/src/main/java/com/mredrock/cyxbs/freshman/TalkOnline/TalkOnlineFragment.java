package com.mredrock.cyxbs.freshman.TalkOnline;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.TalkOnline.ITalkView;
import com.mredrock.cyxbs.freshman.TalkOnline.TalkBean;
import com.mredrock.cyxbs.freshman.TalkOnline.TalkRecyAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.mredrock.cyxbs.freshman.FreshmanMainActivity.BASEURL;

@SuppressLint("ValidFragment")
public class TalkOnlineFragment extends Fragment implements ITalkView {

    View view;
    EditText editText;
    ImageView imageView;
    Context context;
    RecyclerView recyclerView;
    List<TalkBean.ArrayBean.Array1Bean> dataBean = new ArrayList<>();
    TalkRecyAdapter adapter ;
    String type = "";
    static boolean first1 = true;
    static boolean first2 = true;



    @SuppressLint("ValidFragment")
    public TalkOnlineFragment(Context context,String type){
        this.context = context;
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.freshman_talk_hometown_fragment,container,false);
        initView();


        adapter = new TalkRecyAdapter(getActivity(),dataBean);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    imageView.setVisibility(View.INVISIBLE);
                }else {
                    imageView.setVisibility(View.VISIBLE);
                    editText.setText("");
                }
            }
        });
        TalkPresenter presenter = new TalkPresenter(this);
       editText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
               presenter.loadList(BASEURL,type,editText.getText().toString(),adapter);
               System.out.println("666居然变了");
           }
       });



        return view;
    }

    private void initView(){
        editText = view.findViewById(R.id.freshman_talk_fragment_search_edittext);
        imageView = view.findViewById(R.id.freshman_talk_fragment_search_imageview);
        recyclerView = view.findViewById(R.id.freshman_talk_fragment_recycler);
    }



    @Override
    public void showList(List<TalkBean.ArrayBean.Array1Bean> dataList, TalkRecyAdapter adapter, String type) {
        dataBean = dataList;
        System.out.println("666"+dataList.size());

        if (first1&&type.equals("学校群")){
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter.refreshDatabean(dataBean);
        }else if (first2&&type.equals("老乡群")){
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter.refreshDatabean(dataBean);
        }else {
            adapter.refreshDatabean(dataBean);
        }

    }

    @Override
    public void showError(String errorMessage) {

    }
}
