package hu.ait.minesweeper

object MineSweeperModel {
    public val EMPTY: Short = 0
    public val CIRCLE: Short = 1
    public val CROSS: Short = 2
    public val MINE: Short = 1

    private val model = arrayOf(
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)
    )

    private val mine_placement = arrayOf(
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, MINE, EMPTY),
        shortArrayOf(EMPTY, EMPTY, MINE, EMPTY, EMPTY),
        shortArrayOf(MINE, EMPTY, EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY)
    )

    private var nextPlayer = CIRCLE.toShort()

    fun resetModel() {
        for (i in 0..4) {
            for (j in 0..4) {
                model[i][j] = EMPTY
            }
        }
        nextPlayer = CIRCLE
    }

    fun getFieldContent(x: Int, y: Int) = model[x][y]

    fun setFieldContent(x: Int, y: Int, content: Short) {
        model[x][y] = content
    }

    fun getNextPlayer() = nextPlayer

    fun changeNextPlayer() {
        nextPlayer = if (nextPlayer == CIRCLE) CROSS else CIRCLE
    }
}