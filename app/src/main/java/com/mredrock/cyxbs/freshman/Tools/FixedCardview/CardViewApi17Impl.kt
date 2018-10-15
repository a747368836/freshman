package com.mredrock.cyxbs.freshman.Tools.FixedCardview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.mredrock.cyxbs.freshman.Tools.FixedCardview.CardViewBaseImpl
import com.mredrock.cyxbs.freshman.Tools.FixedCardview.CardViewDelegate

internal class CardViewApi17Impl : CardViewBaseImpl() {


    override fun initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = object : RoundRectDrawableWithShadow.RoundRectHelper {
            override fun drawRoundRect(canvas: Canvas, bounds: RectF, cornerRadius: Float, paint: Paint?) {
                canvas.drawRoundRect(bounds, cornerRadius, cornerRadius, paint)
            }


        }

    }

}