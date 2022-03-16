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

    private var gameOver = false

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

//              UNFILLED cells with NO FLAGS
                if (fieldContent == numMines) {
                    val centerX = (i * width / 5 + width / 10).toFloat()
                    val centerY = (j * height / 5 + height / 10).toFloat()
                    canvas?.drawText(numMines.toString(), centerX, centerY, paintText)
                }
//              FLAGGED CELLS
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
            if (tX < 5 && tY < 5 && !gameOver) {
                // FLAGGING NONMINE
                if (flaggingNonMine(tX, tY)) {
                    (context as MainActivity).binding.tvData.text = "You lost! (flagged a non-mine cell)"
                    gameOver = true
                } else if (!gameOver) {
                    // NOT MINE
                    if (fieldContent != MineSweeperModel.MINE) {
                        // FLAGGED
                        if ((context as MainActivity).isFlagModeOn() && fieldContent == MineSweeperModel.EMPTY) {
                            MineSweeperModel.setFieldContent(tX, tY, MineSweeperModel.CROSS)
                        }
                        // NOT FLAGGED
                        else if (fieldContent != MineSweeperModel.CROSS) {
                            MineSweeperModel.setFieldContent(
                                tX,
                                tY,
                                MineSweeperModel.getNumMines(tX, tY)
                            )
                        }
                        invalidate()
                        checkWinning()
                    }
                    //  STEP ON MINE
                    else {
                        (context as MainActivity).binding.tvData.text = "You lost! (stepped on a mine)"
                        gameOver = true

                    }
                }
            }

        }
        return true
    }

    public fun checkWinning() {
        for (i in 1..4) {
            for (j in 1..4) {
                if (MineSweeperModel.getFieldContent(i, j) == MineSweeperModel.EMPTY) {
                    return
                }
            }
        }
        (context as MainActivity).binding.tvData.text = "You won!"
        gameOver = true
    }

    public fun flaggingNonMine(tX: Int, tY: Int): Boolean {
        return (MineSweeperModel.getFieldContent(tX, tY) == MineSweeperModel.CROSS
                && MineSweeperModel.getNumMines(tX, tY) != MineSweeperModel.MINE)
    }

    public fun resetGame() {
        gameOver = false
        (context as MainActivity).binding.tvData.text = ""
        MineSweeperModel.resetModel()
        invalidate()
    }


}
