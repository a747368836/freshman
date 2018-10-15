package com.mredrock.cyxbs.freshman.Tools.CircleView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.mredrock.cyxbs.freshman.R;


//数学真菜。。。
//Math.sin cos  里面传的是 弧度 弧度 弧度 ！！！！！！！！！！！！！！！
//被这玩意坑了几个小时，差点怀疑人生
//里面那个圆没注释掉了，感觉 视觉图，应该就是没有里面的那个。。那何必浪费性能。。  以后要用的话改改还能当个显示进度的东西

public class CircleView extends View {


    // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画圆环的画笔背景色
    private Paint mRingPaintBg;
    // 画字体的画笔
    private Paint mTextPaint;
    //画圆边框的画笔
    private Paint mSidePaint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    // 圆环背景颜色
    private int mRingBgColor;
    //描边颜色
    private int mSlideColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress = 100;
    // 当前进度
    private int mProgress;

    private float mTxtSize;

    private boolean isFinished = false;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        initAttrs(context, attrs);
        initVariable();
    }

    //属性
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.BGPercentView, 0, 0);
        mRadius = typeArray.getDimension(R.styleable.BGPercentView_radius, 80);
        mStrokeWidth = typeArray.getDimension(R.styleable.BGPercentView_strokeWidth, 30);
        mCircleColor = typeArray.getColor(R.styleable.BGPercentView_circleColor, 0xFFFFFFFF);
        mRingColor = typeArray.getColor(R.styleable.BGPercentView_ringColor, 0xFFFFFFFF);
        mRingBgColor = typeArray.getColor(R.styleable.BGPercentView_ringBgColor, 0xFFFFFFFF);
        mTxtSize = typeArray.getDimension(R.styleable.BGPercentView_textSize, 75);
        mSlideColor = typeArray.getColor(R.styleable.BGPercentView_slideColor, getResources().getColor(R.color.tianyiSeColor));
        mRingRadius = mRadius + mStrokeWidth / 2;
    }

    //初始化画笔
    private void initVariable() {
        //内圆
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        //外圆弧背景
        mRingPaintBg = new Paint();
        mRingPaintBg.setAntiAlias(true);
        mRingPaintBg.setColor(mRingBgColor);
        mRingPaintBg.setStyle(Paint.Style.STROKE);
        mRingPaintBg.setStrokeWidth(mStrokeWidth);


        //外圆弧
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);
        mRingPaint.setStrokeCap(Paint.Cap.ROUND);//设置线冒样式，有圆 有方

        //描边
        mSidePaint = new Paint();
        mSidePaint.setAntiAlias(true);
        mSidePaint.setColor(mSlideColor);
        mSidePaint.setStyle(Paint.Style.STROKE);
        mSidePaint.setStrokeWidth(4);
        mSidePaint.setStrokeCap(Paint.Cap.ROUND);

        //中间字
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mRingColor);
        mTextPaint.setTextSize(60);

        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);
    }

    //画图
    @Override
    protected void onDraw(Canvas canvas) {
        mXCenter = getWidth() / 2;
        mYCenter = getHeight() / 2;

        //内圆
        /* canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);*/

        //外圆弧背景
        RectF oval1 = new RectF();
        oval1.left = (mXCenter - mRingRadius);
        oval1.top = (mYCenter - mRingRadius);
        oval1.right = mRingRadius * 2 + (mXCenter - mRingRadius);
        oval1.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
        canvas.drawArc(oval1, 0, 360, false, mRingPaintBg); //圆弧所在的椭圆对象、圆弧的起始角度、圆弧的角度、是否显示半径连线
        //外圆弧
        if (mProgress > 0) {
            RectF oval = new RectF();
            //主要的大圆弧
            oval.left = (mXCenter - mRingRadius);
            oval.top = (mYCenter - mRingRadius);
            oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
            oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
            canvas.drawArc(oval, -90, ((float) mProgress / mTotalProgress) * 360, false, mRingPaint);

            //内边
            oval.left = (mXCenter - mRingRadius + 30);
            oval.top = (mYCenter - mRingRadius + 30);
            oval.right = mRingRadius + mXCenter - 30;
            oval.bottom = mRingRadius + mYCenter - 30;
            canvas.drawArc(oval, -90, ((float) mProgress / mTotalProgress) * 360, false, mSidePaint);

            //外边
            oval.left = (mXCenter - mRingRadius - 30);
            oval.top = (mYCenter - mRingRadius - 30);
            oval.right = mRingRadius + mXCenter + 30;
            oval.bottom = mRingRadius + mYCenter + 30;
            canvas.drawArc(oval, -90, ((float) mProgress / mTotalProgress) * 360, false, mSidePaint);

            //补最上面第一个圆弧 起始角度和结束角度都多加了5度，要不看起来有点奇怪、
            RectF oval2 = new RectF();
            double rad = 2 * Math.PI / 360.0;
            double angle = (double) (mProgress / (mTotalProgress + 0.0) * 360.0) * rad;
            oval2.left = (float) (mXCenter - 30);
            oval2.top = (mYCenter - mRingRadius - 30);
            oval2.right = (float) (mXCenter + 30);
            oval2.bottom = (mYCenter - mRingRadius + 30);
            canvas.drawArc(oval2, 85, 185, false, mSidePaint);
            //加载完到结尾补最后一个圆弧
            if (isFinished) {
                if (angle > 90 && angle <= 180 * rad) {//
                    oval2.left = (float) (mXCenter + mRingRadius * Math.cos(angle - 90 * rad) - 30);
                    oval2.top = (float) (mYCenter + mRingRadius * Math.sin(angle - 90 * rad) - 30);
                    oval2.right = (float) (mXCenter + mRingRadius * Math.cos(angle - 90 * rad) + 30);
                    oval2.bottom = (float) (mYCenter + mRingRadius * Math.sin(angle - 90 * rad) + 30);
                } else if (angle > 180 * rad && angle <= 270 * rad) {//
                    oval2.left = (float) (mXCenter - mRingRadius * Math.sin(angle - 180 * rad) - 30);
                    oval2.top = (float) (mYCenter + mRingRadius * Math.cos(angle - 180 * rad) - 30);
                    oval2.right = (float) (mXCenter - mRingRadius * Math.sin(angle - 180 * rad) + 30);
                    oval2.bottom = (float) (mYCenter + mRingRadius * Math.cos(angle - 180 * rad) + 30);
                } else if (angle > 270 * rad && angle < 360) {
                    oval2.left = (float) (mXCenter - mRingRadius * Math.cos(angle - 270 * rad) - 30);
                    oval2.top = (float) (mYCenter - mRingRadius * Math.sin(angle - 270 * rad) - 30);
                    oval2.right = (float) (mXCenter - mRingRadius * Math.cos(angle - 270 * rad) + 30);
                    oval2.bottom = (float) (mYCenter - mRingRadius * Math.sin(angle - 270 * rad) + 30);
                } else if (angle > 0 && angle < 90) {//
                    oval2.left = (float) (mXCenter + mRingRadius * Math.sin(angle) - 30);
                    oval2.top = (float) (mYCenter - mRingRadius * Math.cos(angle) - 30);
                    oval2.right = (float) (mXCenter + mRingRadius * Math.sin(angle) + 30);
                    oval2.bottom = (float) (mYCenter - mRingRadius * Math.cos(angle) + 30);
                }
                System.out.println("我画了，我不背锅");

                canvas.drawArc(oval2, (float) (angle / rad - 95), 185, false, mSidePaint);
                /*canvas.drawCircle(oval2.left, oval2.top,10,mSidePaint);*/

            }


        }


        //字体
        //String txt = mProgress + "分";
        String txt = mProgress + "%";
        mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
        canvas.drawText(txt, mXCenter - mTxtWidth - 40, mYCenter - mRingRadius + 26, mTextPaint);

    }

    //设置进度
    public void setProgress(int progress) {
        mProgress = progress;
        postInvalidate();//重绘
    }

    public int getProgress() {
        return mProgress;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
