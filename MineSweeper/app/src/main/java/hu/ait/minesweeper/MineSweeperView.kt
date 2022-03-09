package hu.ait.minesweeper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MineSweeperView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var paintBackgorund: Paint
    private lateinit var paintLine: Paint

    init {
        paintBackgorund = Paint()
        paintBackgorund.color = Color.BLACK
        paintBackgorund.style = Paint.Style.FILL

        paintLine = Paint()
        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(
            0f, 0f, width.toFloat(),
            height.toFloat(), paintBackgorund
        )

        drawGameArea(canvas!!)
        drawPlayers(canvas!!)
    }

    private fun drawGameArea(canvas: Canvas) {
        // border
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine)

        // four horizontal lines
        for (i in 1..4) {
            canvas.drawLine(
                0f, (i * height / 5).toFloat(), width.toFloat(), (i * height / 5).toFloat(),
                paintLine
            )
        }

        // four vertical lines
        for (i in 1..4) {
            canvas.drawLine(
                (i * width / 5).toFloat(), 0f, (i * width / 5).toFloat(), height.toFloat(),
                paintLine
            )
        }

    }

    private fun drawPlayers(canvas: Canvas) {
        for (i in 0..4) {
            for (j in 0..4) {
                if (MineSweeperModel.getFieldContent(i, j) == MineSweeperModel.CIRCLE) {
                    val centerX = (i * width / 5 + width / 15).toFloat()
                    val centerY = (j * height / 5 + height / 15).toFloat()
                    val radius = height / 15 - 2

                    canvas.drawCircle(centerX, centerY, radius.toFloat(), paintLine)
                } else if (MineSweeperModel.getFieldContent(i, j) == MineSweeperModel.CROSS) {
                    canvas.drawLine((i * width / 5).toFloat(), (j * height / 5).toFloat(),
                        ((i + 1) * width / 5).toFloat(),
                        ((j + 1) * height / 5).toFloat(), paintLine)

                    canvas.drawLine(((i + 1) * width / 5).toFloat(), (j * height / 5).toFloat(),
                        (i * width / 5).toFloat(), ((j + 1) * height / 5).toFloat(), paintLine)
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {

            val tX = event.x.toInt() / (width / 5)
            val tY = event.y.toInt() / (height / 5)

            if (tX < 3 && tY < 3 && MineSweeperModel.getFieldContent(tX, tY) ==
                MineSweeperModel.EMPTY) {
                MineSweeperModel.setFieldContent(tX, tY, MineSweeperModel.getNextPlayer())
                MineSweeperModel.changeNextPlayer()
                invalidate()
            }

        }

        return true
    }

    public fun resetGame() {
        MineSweeperModel.resetModel()
        invalidate()
    }

}