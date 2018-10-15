package com.mredrock.cyxbs.freshman.EssentialToRegister;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.mredrock.cyxbs.freshman.FreshmanMainActivity;
import com.mredrock.cyxbs.freshman.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.litepal.LitePal;

import static android.widget.Toast.LENGTH_SHORT;
import static com.mredrock.cyxbs.freshman.FreshmanMainActivity.BASEURL;

/*
 * 后来加的litePal就没在意mvp了，
 * 新学的，
 * 慢慢来
 * = =
 * 摸清楚就改
 * Bugs: 1.现在第一遍加载列表的时候，重新加载了一遍activity 要不然会出现奇奇怪怪的各种bug
 *       2.删除，选中某项时，有时候会没删掉。。估计是adapter里面的position出了啥问题
 */

public class EssentialMainActivity extends AppCompatActivity
        implements View.OnClickListener, IEssebtialMainActivity {

    Context mContext = this;
    TextView mainTitleTxv;
    TextView sonTitleTxv;
    CheckBox moreCheckBox;
    CheckBox isSelectedBox;
    CardView cardView;
    CardView addCardview;
    RecyclerView mRecyclerView;
    RelativeLayout relativeLayout;
    RelativeLayout addRelativeLayout;
    Toolbar toolbar;
    EditText addItemEditText;
    Button addButton;
    TextView deleteChanegeView;
    FloatingActionButton fabView;
    RefreshLayout refreshLayout;
    EssentialMainAdapter adapter;
    private int scrollToPosition = 0;
    EssentialMainPresenter presenter = new EssentialMainPresenter(this);
    private int checks = 0;
    Handler handler;
    static boolean firstLoadFromNet = true;
    private List<EssentialDataBean.DescribeBean> dataBean = new ArrayList<>();

    public void notifyDeleteItemNumChanged(){
        deleteChanegeView.setText("删("+LitePal.where("isChecked = ?","true").count(EssentialDataBean.DescribeBean.class)+"）除");
    }
  /*  private static EssentialMainAdapter instance = new EssentialMainAdapter();
    public static EssentialMainAdapter getInstance() {
        return instance;
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //输入法弹出，其他部位上滑
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //去掉actionbar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.freshman_essential_main_activity);
        //安卓版本大于4.4设置状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        //绑定toolbar
        setSupportActionBar(toolbar);
        //初始化点击监听
        initBtn();
        //绑定各种view
        initView();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                checks = msg.arg1;
                if (checks != 0 && msg.arg1 - checks < 1) {
                    deleteChanegeView.setText("删除(" + checks + ")");
                }
            }
        };
        adapter = new EssentialMainAdapter(this,dataBean,handler);
        //初始化recyclerView
        initRecy();


        System.out.println("EssentialMainActivity 这时候databean的大小是 ");
        if (firstLoadFromNet) {
           presenter.loadList(BASEURL,"firstLoad",adapter);
           firstLoadFromNet = false;
        } else if (LitePal.where("content <> ?", " ").find(EssentialDataBean.DescribeBean.class).size() == 0) {
            presenter.loadList(BASEURL, "loadFromNet",adapter);
            firstLoadFromNet = false;
        } else {
            Log.d("从数据库加载databean",LitePal.findAll(EssentialDataBean.DescribeBean.class).size()+"");
            presenter.loadList(BASEURL, "loadFromLocal",adapter);
        }


        OnRefreshListener listener = new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.loadList(BASEURL, refreshLayout,adapter);

            }
        };
        refreshLayout.setOnRefreshListener(listener);
        //滚上去de代码放到点击事件里面了，还是会把toolbar也给弄没了。。后期改把





    }


    public void backgroundAlpha(float bgAlpha) {
        //在popupWindow哪儿修改透明度实现阴影
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }


    public void setPopupWindow() {
        backgroundAlpha(0.7f);
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.freshman_essential_main_popupwindow, null, false);
        PopupWindow window = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(findViewById(R.id.freshman_essential_main_recycler),
                Gravity.CENTER, 0, 0);
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.update();
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }


    public void initView() {
        toolbar = findViewById(R.id.freshman_essential_main_toolbar);
        moreCheckBox = findViewById(R.id.freshman_essential_recy_card_more_checkbox);
        isSelectedBox = findViewById(R.id.freshman_essential_recy_card_checkbox);
        cardView = findViewById(R.id.freshman_essential_main_popup_cardview);
        addCardview = findViewById(R.id.freshman_essential_main_add_cardview);
        mainTitleTxv = findViewById(R.id.freshman_essential_recy_card_title_textview);
        sonTitleTxv = findViewById(R.id.freshman_essential_recy_card_sontitle1_textview);
        mRecyclerView = findViewById(R.id.freshman_essential_main_recycler);
        relativeLayout = findViewById(R.id.freshman_essential_main_relativelayout);
        fabView = findViewById(R.id.freshman_essential_main_fab);
        addButton = findViewById(R.id.freshman_essential_main_add_button);
        addItemEditText = findViewById(R.id.freshman_essential_main_addmore_edittext);
        addRelativeLayout = findViewById(R.id.freshman_essential_main_add_relativelayout);
        deleteChanegeView = findViewById(R.id.freshman_essential_main_toolbar_edit_textview);
        refreshLayout = findViewById(R.id.freshman_essential_main_recycler_refreshlayout);
    }

    public void initRecy() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        //adapter的绑定在showList里面
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);



    }

    public void initBtn() {
        findViewById(R.id.freshman_essential_main_toolbar_question_imageview).setOnClickListener(this);
        findViewById(R.id.freshman_essential_main_toolbar_back_imageview).setOnClickListener(this);
        findViewById(R.id.freshman_essential_main_toolbar_edit_textview).setOnClickListener(this);
        findViewById(R.id.freshman_essential_main_fab).setOnClickListener(this);
        findViewById(R.id.freshman_essential_main_add_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.freshman_essential_main_popup_cardview_close_imageview:
                //这个是popup里面的一个图片，提前设置listner会空指针，先空着了
                onBackPressed();
                break;
            case R.id.freshman_essential_main_toolbar_question_imageview:
                setPopupWindow();
                break;
            case R.id.freshman_essential_main_toolbar_back_imageview:
                if (deleteChanegeView.getText().toString().contains("删")) {
                    deleteChanegeView.setText("编辑");
                    checks = 0;
                    adapter.notifyDataSetChanged();
                    fabView.show();
                } else {
                    onBackPressed();
                }

                break;
            case R.id.freshman_essential_main_toolbar_edit_textview:
                adapter.reSetPositionList();
                if (adapter.getIsShow()) {
                    adapter.setIsShow(false);
                    fabView.hide();
                    adapter.notifyItemRangeChanged(0, dataBean.size());
                    System.out.println("22  "+dataBean.size());
                    deleteChanegeView.setText("删除(0)");
                    adapter.notifyDataSetChanged();

                } else if (deleteChanegeView.getText().toString().contains("删")&&!deleteChanegeView.getText().toString().contains("删(0)")) {
                    System.out.println("我执行了删除里的更新databean"+dataBean.size());
                    for (int i = 0; i < dataBean.size(); i++) {
                        if (dataBean.get(i).isChecked()) {
                            LitePal.deleteAll(EssentialDataBean.DescribeBean.class, "name = ?", dataBean.get(i).getName());
                        }
                    }
                    dataBean = LitePal.findAll(EssentialDataBean.DescribeBean.class);
                    System.out.println("删完后，databean还有"+dataBean.size());
                    adapter.upDateDataBean(dataBean);
                    adapter.setIsShow(true);
                    fabView.show();
                    deleteChanegeView.setText("编辑");
                } else {
                    adapter.setIsShow(true);
                    deleteChanegeView.setText("编辑");
                    fabView.show();
                    adapter.notifyDataSetChanged();
                }


                break;

            case R.id.freshman_essential_main_fab:
                fabView.hide();
                addCardview.setVisibility(View.VISIBLE);
                addItemEditText.setFocusable(true);
                addItemEditText.setFocusableInTouchMode(true);
                addItemEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(addItemEditText, 0);

                /*autoScrollView();*/

                break;
            case R.id.freshman_essential_main_add_button:
                fabView.show();
                presenter.addItem();
                adapter.notifyItemRangeChanged(0, dataBean.size());
                addItemEditText.setText("");
                addCardview.setVisibility(View.INVISIBLE);

                //输入完成后收起输入法
                InputMethodManager immHide = (InputMethodManager)
                        mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                immHide.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

                break;


        }

    }


    @Override
    public void showList(List<EssentialDataBean.DescribeBean> dataList, String type,EssentialMainAdapter adapter) {
        if (adapter == null) {
            System.out.println("adapter为null我return了");
            return;
        }
        switch (type) {
            case "refresh":
                if (dataList.size() > 0) {
                    dataBean.clear();
                    dataBean = dataList;
                    for (int i = 0; i < LitePal.where("content = ?", " ").find(EssentialDataBean.DescribeBean.class).size(); i++) {
                        dataBean.add(LitePal.where("content = ?", " ").find(EssentialDataBean.DescribeBean.class).get(i));
                    }
                    adapter.upDateDataBean(dataBean);
                    Log.d("刷新中加载数据databean", dataBean.size() + "");
                } else {
                    Log.d("刷新中加载数据出错，加载数据库数据"
                            , LitePal.findAll(EssentialDataBean.DescribeBean.class).get(1).getName() + "");
                    dataBean = LitePal.findAll(EssentialDataBean.DescribeBean.class);
                   this.adapter.upDateDataBean(dataBean);
                }
                break;
            case "loadFromLocal":
                Log.d("从数据库加载 大小databean", LitePal.findAll(EssentialDataBean.DescribeBean.class).size() + "");
                dataBean = LitePal.findAll(EssentialDataBean.DescribeBean.class);
                mRecyclerView.setAdapter(adapter);
                adapter.upDateDataBean(dataBean);
                break;
            case "firstLoad":
                dataBean = dataList;
                LitePal.deleteAll(EssentialDataBean.DescribeBean.class, "content <> ?", " ");
                for (int i = 0; i < LitePal.findAll(EssentialDataBean.DescribeBean.class).size(); i++) {
                    dataBean.add(LitePal.findAll(EssentialDataBean.DescribeBean.class).get(i));
                }
                for (int i = 0; i < dataBean.size(); i++) {
                    if (dataBean.get(i).getContent() != " ") {
                        dataBean.get(i).save();
                    }
                }
                adapter = new EssentialMainAdapter(this, dataBean, handler);
                Log.d("第一次加载数据 ", dataBean.size() + "");
                mRecyclerView.setAdapter(adapter);
                //这里是为了解决一下bug，如果删掉，会出现奇奇怪怪的现象，但只要过了第一次加载数据就好了
                onBackPressed();
                Intent intent = new Intent(mContext,EssentialMainActivity.class);
                startActivity(intent);
                //到这里结束
                break;
            case "loadFromNet":
                dataBean = dataList;
                LitePal.deleteAll(EssentialDataBean.DescribeBean.class, "content <> ?", " ");
                for (int i = 0; i < LitePal.findAll(EssentialDataBean.DescribeBean.class).size(); i++) {
                    dataBean.add(LitePal.findAll(EssentialDataBean.DescribeBean.class).get(i));
                }
                for (int i = 0; i < dataBean.size(); i++) {
                    if (dataBean.get(i).getContent() != " ") {
                        dataBean.get(i).save();
                    }
                }
                mRecyclerView.setAdapter(adapter);
                adapter.upDateDataBean(dataBean);
        }

    }

    @Override
    public void showError(String errorMessage) {
        Log.d("errorMessage", errorMessage);
        if (errorMessage.equals("请输入需要添加的事项")) {
            Toast.makeText(this, errorMessage, LENGTH_SHORT).show();
        } else if (errorMessage.equals("refreshFailed")) {
            Log.d("刷新中加载数据出错，加载数据库数据"
                    , LitePal.findAll(EssentialDataBean.DescribeBean.class).get(1).getName() + "");
            dataBean = LitePal.findAll(EssentialDataBean.DescribeBean.class);
            adapter.upDateDataBean(dataBean);
        } else {
            Toast.makeText(this, "请检查网络连接", LENGTH_SHORT).show();
        }

    }

    @Override
    public String getTitle(String title) {
        return addItemEditText.getText().toString();
    }

    @Override
    public void addItem(EssentialDataBean.DescribeBean bean) {
        dataBean.add(bean);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshData(RefreshLayout refreshLayout) {


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            if (deleteChanegeView.getText().toString().contains("删")) {
                deleteChanegeView.setText("编辑");
                checks = 0;
                adapter.notifyDataSetChanged();
                fabView.show();
                return true;
            } else if (addCardview.getVisibility() == View.VISIBLE) {
                addItemEditText.setText("");
                fabView.show();
                addCardview.setVisibility(View.GONE);
                return true;
            }
        return super.onKeyDown(keyCode, event);

    }

    private void autoScrollView() {
        relativeLayout.getViewTreeObserver()
                .addOnGlobalLayoutListener(
                        new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                computePosition();
                            }
                        });
    }

    private void computePosition() {
        Rect rect = new Rect();
        //获取root在窗体的可视区域
        relativeLayout.getWindowVisibleDisplayFrame(rect);
        //获取root在窗体的不可视区域高度(被遮挡的高度)
        int rootInvisibleHeight = relativeLayout.getRootView().getHeight() - rect.bottom;
        //若不可视区域高度大于150，则键盘显示
        if (rootInvisibleHeight > 150) {
            //获取scrollToView在窗体的坐标,location[0]为x坐标,location[1]为y坐标
            int[] location = new int[2];
            addRelativeLayout.getLocationInWindow(location);
            //计算root滚动高度,使scrollToView在可见区域的底部
            int scrollHeight = (location[1] + addRelativeLayout.getHeight()) - rect.bottom;
            //注意:scrollHeight是相对移动距离,而scrollToPosition是绝对移动距离
            scrollToPosition += scrollHeight;
        } else {
            //否则键盘隐藏
            scrollToPosition = 0;
        }
        relativeLayout.scrollTo(0, scrollToPosition);
    }
}


