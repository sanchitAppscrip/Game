package com.test.data.repo

import com.test.data.GameHandler
import com.test.domain.GameRepo
import com.test.domain.model.Player
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GameRepoImpl @Inject constructor(
    val gameHandler: GameHandler
) : GameRepo {
    override fun changeGameState(position: Int, player: Player): Single<String> {
        var msg =
            if (gameHandler.game.hasGameEnded()) {
                "GameOver"
            } else {
                gameHandler.game.changeGameState(position, player)
            }

        return Single.just(msg)
    }

    override fun resetGame() {
        gameHandler.game.reset()
    }
}