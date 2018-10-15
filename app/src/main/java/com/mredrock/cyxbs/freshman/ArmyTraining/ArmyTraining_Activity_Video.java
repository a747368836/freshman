package  com.mredrock.cyxbs.freshman.ArmyTraining;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.VideoView;

import com.mredrock.cyxbs.freshman.R;


/**
 * Created by 郝书逸 on 2018/8/9.
 */

public class ArmyTraining_Activity_Video extends Activity{
    WebView webView;
    String videourl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //安卓版本大于4.4设置状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.freshman_armytraining_video);
        Intent intent=getIntent();
        videourl=intent.getStringExtra("url");
        this.webView = findViewById(R.id.armytraining_video);
        webView.loadUrl(videourl);
    }
}
