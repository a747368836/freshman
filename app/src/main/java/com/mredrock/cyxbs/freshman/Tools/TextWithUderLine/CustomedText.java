package com.mredrock.cyxbs.freshman.Tools.TextWithUderLine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.mredrock.cyxbs.freshman.R;
import com.mredrock.cyxbs.freshman.Tools.DisplayUtils;

@SuppressLint("AppCompatCustomView")
public class CustomedText extends TextView {



    private Paint mUnderLinePaint;

    private float mDashGap;

    private float mDashWidth;

    private float mUnderLineHeight;

    private int mUnderLineColor;

    public CustomedText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initVariable();
    }
    public CustomedText(Context context){
        super(context);
        initVariable();
    }


    private void initAttrs(Context context,AttributeSet attrs){
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SayTextStyle, 0, 0);
          mUnderLineColor = typeArray.getColor(R.styleable.SayTextStyle_underLineColor,getResources().getColor(R.color.wantToSayUnderLineColor));
          mDashGap = typeArray.getDimension(R.styleable.SayTextStyle_dashGap, DisplayUtils.dp2px(context,2));
          mDashWidth = typeArray.getDimension(R.styleable.SayTextStyle_dashWidth,DisplayUtils.dp2px(context,5));
          mUnderLineHeight = typeArray.getDimension(R.styleable.SayTextStyle_underLineHeight,DisplayUtils.dp2px(context
            ,2));
    }

    private void initVariable(){


        mUnderLinePaint = new Paint();
        mUnderLinePaint.setAntiAlias(true);
        mUnderLinePaint.setStyle(Paint.Style.FILL);
        mUnderLinePaint.setColor(mUnderLineColor);
        mUnderLinePaint.setStrokeWidth(1);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        float length = mDashGap + mDashWidth;
        int num = (int)(width/length);
        /*for (int i = 0; i < num; i++) {
            canvas.drawLine(i*length,height,i*length+mDashWidth,height,mUnderLinePaint);
        }*/

        /*canvas.drawRect(0, getHeight()-mUnderLineHeight, getWidth(), getHeight(), mUnderLinePaint);
*/

        super.onDraw(canvas);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom + (int)mUnderLineHeight);
    }


    public void setmUnderLinePaint(Paint mUnderLinePaint) {
        this.mUnderLinePaint = mUnderLinePaint;
    }

    public void setmDashGap(float mDashGap) {
        this.mDashGap = mDashGap;
    }

    public void setmDashWidth(float mDashWidth) {
        this.mDashWidth = mDashWidth;
    }

    public void setmUnderLineHeight(float mUnderLineHeight) {
        this.mUnderLineHeight = mUnderLineHeight;
    }

    public void setmUnderLineColor(int mUnderLineColor) {
        this.mUnderLineColor = mUnderLineColor;
    }
}
