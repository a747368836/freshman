package com.mredrock.cyxbs.freshman.IWantToSay;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEPresenter;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBERecyAdapter;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.IBBEView;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.Tools.DisplayUtils;
import com.mredrock.cyxbs.freshman.Tools.TextWithUderLine.CustomedText;

import java.util.ArrayList;
import java.util.List;

import static com.mredrock.cyxbs.freshman.FreshmanMainActivity.BASEURL;

public class IWantToSatActivity extends AppCompatActivity implements IBBEView {

    LinearLayout linearLayout;
    String content = "";
    ImageView backView;
    android.support.v7.widget.Toolbar toolbar;
    Typeface tf  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.freshman_say_main_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
        /*setSupportActionBar(toolbar);*/
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        tf = Typeface.createFromAsset(getAssets(), "say.ttf");
        BBEPresenter presenter = new BBEPresenter(this);
        presenter.loadList(BASEURL,"我想对你说",1,1);
        /*mTextview.setTypeface(tf);*/

    }

    private void initView() {
        backView = findViewById(R.id.freshman_say_main_back_imageview);
        linearLayout = findViewById(R.id.freshman_say_main_text_linearLayout);
        toolbar = findViewById(R.id.freshman_say_main_toolbar);
    }


    private void addTextView(String content,boolean isFirst) {
        CustomedText textView = new CustomedText(this);
        textView.setmDashGap(DisplayUtils.dp2px(this,2));
        textView.setmDashWidth(DisplayUtils.dp2px(this,5));
        textView.setmUnderLineColor(R.color.wantToSayUnderLineColor);
        textView.setTypeface(tf);
        textView.setLineSpacing(12,1);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        if(isFirst){
            lp.setMargins(0, 16, 0, 0);
            textView.setLayoutParams(lp);
            textView.setText(content);
            textView.setTextColor(getResources().getColor(R.color.wantToSayFirstTextColor));
        }else {
            lp.setMargins(0, 20, 0, 0);
            textView.setLayoutParams(lp);
            textView.setText("      "+content);
            textView.setTextColor(getResources().getColor(R.color.wantToSayTextColor));
        }

        linearLayout.addView(textView);
    }

    @Override
    public void showList(List<BBEBean.ArrayBean> dataList, BBERecyAdapter adapter) {

    }


    @Override
    public void showList(List<BBEBean.ArrayBean> dataList) {
       this.content = dataList.get(0).getContent();
        String[] str = dataList.get(0).getContent().split("\n");
        for (int j = 0; j < str.length; j++) {
            if (j == 0) {
               addTextView(str[0],true);
            } else {
                addTextView(str[j],false);
            }
        }
    }

    @Override
    public void showList(List<BBEBean.ArrayBean> dataList, BBERecyAdapter adapter, String type) {

    }

    @Override
    public void showError(String errorMessage) {

    }
}
