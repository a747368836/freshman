package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.BGPercent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEApiService;
import com.mredrock.cyxbs.freshman.CampusStrategy.BBE.BBEBean;
import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.Tools.CircleView.CircleView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mredrock.cyxbs.freshman.FreshmanMainActivity.BASEURL;

@SuppressLint("ValidFragment")
public class BGPercentFragment extends Fragment implements IBGView {
    View view;
    private int mTotalProgress = 100;
    private int mCurrentProgress = 0;
    //进度条
    private CircleView boyCircle;
    private CircleView girlCircle;
    private TextView nameView;
    private int boys = 27;
    private int girls = 73;
    private String name = "";

    @SuppressLint("ValidFragment")
    public BGPercentFragment(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.freshman_campus_data_bgpercent_fragment, container, false);

        initView();


        BGPresenter presenter = new BGPresenter(this);
        presenter.loadList(BASEURL, name);


        return view;

    }

    private void initView() {
        boyCircle = view.findViewById(R.id.freshman_campus_data_bgpercent_boycircle);
        girlCircle = view.findViewById(R.id.freshman_campus_data_bgpercent_girlcircle);
        nameView = view.findViewById(R.id.freshman_campus_data_bgpercent_name_textview);
        nameView.setText(name + "男女比例");
    }


    @Override
    public void showList(BGBean bean) {
        boys = (int) (100 * bean.getMale_amount() / ((bean.getFemale_amount() + bean.getMale_amount() + 0.0)));
        girls = (int) (100 * bean.getFemale_amount() / (bean.getFemale_amount() + bean.getMale_amount() + 0.0));

        System.out.println("男生：" + boys + "女生" + girls);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progressBoy = boyCircle.getProgress();
                int progressGirl = girlCircle.getProgress();
                boolean flag1 = true;
                boolean flag2 = true;
                while (flag1 || flag2) {
                    if (boyCircle.getProgress() < boys || girlCircle.getProgress() <girls+1) {
                        try {
                            Thread.sleep(25);
                            if (flag1){
                                boyCircle.setProgress(++progressBoy);
                            }
                            if (flag2){
                                girlCircle.setProgress(++progressGirl);
                            }
                            if (progressBoy == boys) {
                                boyCircle.setFinished(true);
                                flag1 = false;
                            }
                            if (progressGirl == girls + 1) {
                                girlCircle.setFinished(true);
                                flag2 = false;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }



                }

            }
        }).start();


       /* new Thread(new Runnable() {
            @Override
            public void run() {
                int progressBoy = boyCircle.getProgress();
                while (boyCircle.getProgress() < boys) {
                    try {
                        if (boys > girls) {
                            Thread.sleep(50 * girls / boys);
                        } else {
                            Thread.sleep(50);
                        }
                        boyCircle.setProgress(++progressBoy);
                        if (progressBoy == boys) {
                            boyCircle.setFinished(true);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progressGirl = girlCircle.getProgress();
                while (progressGirl < girls + 1) {
                    System.out.println("女生圈内" + girlCircle.getProgress() + "女生" + girls + "  ");
                    try {
                        if (boys < girls) {
                            Thread.sleep(50 * boys / girls);
                        } else {
                            Thread.sleep(50);
                        }
                        girlCircle.setProgress(++progressGirl);
                        if (progressGirl == girls + 1) {
                            girlCircle.setFinished(true);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();*/

    }

    @Override
    public void showError(String errorMessage) {

    }
}
