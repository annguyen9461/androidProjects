package hu.ait.minesweeper

import kotlin.math.log

object MineSweeperModel {
    public val MINE: Short = 0
    public val EMPTY: Short = 11
    public val CROSS: Short = 12

    private val model = arrayOf(
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)
    )

    private val mine_placement = arrayOf(
        shortArrayOf(0, 0, 0, 1, 0),
        shortArrayOf(0, 0, 2, MINE, 1),
        shortArrayOf(1, 1, MINE, 2, 0),
        shortArrayOf(MINE, 1, 1, 0, 0),
        shortArrayOf(1, 0, 0, 0, 0)
    )

    fun resetModel() {
        for (i in 0..4) {
            for (j in 0..4) {
                model[i][j] = EMPTY
            }
        }
    }

    fun getFieldContent(x: Int, y: Int) = model[x][y]

    fun setFieldContent(x: Int, y: Int, content: Short) {
        model[x][y] = content
    }

    fun getNumMines(x: Int, y: Int) = mine_placement[x][y]


}