package com.heaven.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {

    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    private var cX: Float = 0f
    private var cY: Float = 0f

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    init {
        greenPaint.color = Color.GREEN
        blackPaint.color = Color.BLACK
        blackPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w / 2f
        cY = h / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawCircle(cX, cY, 100f, blackPaint)
            drawCircle(cX + xCoord, cY + yCoord, 100f, greenPaint)

            drawLine(cX-20, cY, cX + 20, cY, blackPaint)
            drawLine(cX, cY-20, cX, cY + 20, blackPaint)
        }
    }

    fun onSensorEvent(event: SensorEvent){
        // 화면을 가로 고정했으므로, 가로 세로를 바꿈
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20

        invalidate()
    }
}