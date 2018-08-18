package com.mredrock.cyxbs.freshman.Tools.GrowImage;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.mredrock.cyxbs.freshman.R;


public class GrowImage extends View {
    private Paint mPaint;
    private float mXPosition;
    private float mYPosition;
    private Paint mTextPaint;
    private int mStartColor;
    private int mEndColor;
    private int mTotalProgress = 100;
    private int mProgress = 0;
    private static float mOriginX;
    private static float mOriginY;
    private String text = "";


    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;

    public GrowImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initVariable();
        System.out.println("初始XY变更");
        mOriginX = getWidth();
        mOriginY = getHeight();
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.GrowImageStyle, 0, 0);
        mStartColor = typeArray.getColor(R.styleable.GrowImageStyle_startColor, 0);
        mEndColor = typeArray.getColor(R.styleable.GrowImageStyle_endColor, 0);

    }

    private void initVariable() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mStartColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3.0f);


        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(getResources().getColor(R.color.dataTextColor));
        mTextPaint.setTextSize(40);
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        mXPosition = getWidth();
        mYPosition = getHeight();
        if (mProgress > 0) {
            RectF rectF = new RectF();
            rectF.left = mOriginX;
            rectF.top = mYPosition-  (mProgress/(mTotalProgress+0.0f))*mYPosition+11;
            rectF.right = (float) mXPosition;
            rectF.bottom = (float) mYPosition;

            mPaint.setShader(new LinearGradient(rectF.left,rectF.top,rectF.right,rectF.bottom,
                    mStartColor,mEndColor,LinearGradient.TileMode.REPEAT));
            canvas.drawRoundRect(rectF, 10, 10, mPaint);

            mTxtWidth = mTextPaint.measureText(text, 0, text.length());
            canvas.drawText(text, rectF.right-rectF.left-mTxtWidth, rectF.top-20, mTextPaint);


        }
    }

    public void setProgress(int progress,String text) {
        this.mProgress = progress;
        this.text = text;
        invalidate();
        postInvalidate();
    }

    public int getProgress() {
        return mProgress;
    }

    public void setTotalProgress(int progress){
        this.mTotalProgress = progress;
    }



    public int getTotalProgress(int progress){
        return this.mTotalProgress;

    }
}
