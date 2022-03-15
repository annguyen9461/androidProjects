package hu.ait.minesweeper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MineSweeperView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var paintBackground: Paint
    private lateinit var paintLine: Paint
    private lateinit var paintText: Paint


    init {
        paintBackground = Paint()
        paintBackground.color = Color.BLACK
        paintBackground.style = Paint.Style.FILL

        paintLine = Paint()
        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f

        paintText = Paint()
        paintText.textSize = 70f
        paintText.color = Color.GREEN
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(
            0f, 0f, width.toFloat(),
            height.toFloat(), paintBackground
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
                var fieldContent = MineSweeperModel.getFieldContent(i, j)
                var numMines = MineSweeperModel.getNumMines(i, j)
                if (fieldContent == numMines) {
                    val centerX = (i * width / 5 + width / 10).toFloat()
                    val centerY = (j * height / 5 + height / 10).toFloat()
                    canvas?.drawText(numMines.toString(), centerX, centerY, paintText)
                }
//                if (fieldContent == MineSweeperModel.CIRCLE) {
//                    val centerX = (i * width / 5 + width / 10).toFloat()
//                    val centerY = (j * height / 5 + height / 10).toFloat()
//                    val radius = height / 10
//
//                    canvas.drawCircle(centerX, centerY, radius.toFloat(), paintLine)
//                }
                else if (fieldContent == MineSweeperModel.CROSS) {
                    canvas.drawLine(
                        (i * width / 5).toFloat(), (j * height / 5).toFloat(),
                        ((i + 1) * width / 5).toFloat(),
                        ((j + 1) * height / 5).toFloat(), paintLine
                    )

                    canvas.drawLine(
                        ((i + 1) * width / 5).toFloat(), (j * height / 5).toFloat(),
                        (i * width / 5).toFloat(), ((j + 1) * height / 5).toFloat(), paintLine
                    )
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {

            val tX = event.x.toInt() / (width / 5)
            val tY = event.y.toInt() / (height / 5)

            var fieldContent = MineSweeperModel.getFieldContent(tX, tY)
            if (tX < 5 && tY < 5
                && fieldContent != MineSweeperModel.MINE
            ) {
                if ((context as MainActivity).isFlagModeOn()) {
                    MineSweeperModel.setFieldContent(tX, tY, MineSweeperModel.CROSS)
                } else if (fieldContent != MineSweeperModel.CROSS) {
//                    MineSweeperModel.setFieldContent(tX, tY, MineSweeperModel.CIRCLE)
                    MineSweeperModel.setFieldContent(tX, tY, MineSweeperModel.getNumMines(tX, tY))
                }
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
