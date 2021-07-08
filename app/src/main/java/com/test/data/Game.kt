package com.test.data

import com.test.domain.model.Player


class Game {
    var player1 = Player("Player1", "x")
    var player2 = Player("Player2", "x")
    private var currentPlayer = player1

    private var winPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    /**
     * 0 refers player1 mark
     * 1 refers player2 mark
     * 2 refers empty cell
     */
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)


    fun changeGameState(position: Int, player: Player): String {
        gameState[position] = if (player.value == "x") {
            0
        } else {
            1
        }

        return if (!hasGameEnded()) {
            switchPlayer()
            if (hasGameTied())
                "Game ends in draw"
            else
                "${currentPlayer.name} turn"
        } else {
            "${currentPlayer.name} has won"
        }
    }

    fun hasGameEnded(): Boolean {
        var flag = false
        for (winPosition in winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState.get(
                    winPosition[2]
                ) && gameState[winPosition[0]] != 2
            ) {
                flag = true
            }
        }
        return flag
    }

    private fun hasGameTied(): Boolean {
        var tied = true
        gameState.forEach { cell ->
            if (cell == 2) {
                tied = false
            }
        }
        return tied
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer === player1) player2 else player1
    }

    fun reset() {
        currentPlayer = player1

        for (i in gameState.indices) {
            gameState[i] = 2
        }
    }
}