package com.test.domain

import com.test.domain.model.Player
import io.reactivex.rxjava3.core.Single

interface GameRepo {

    fun changeGameState(position: Int, player: Player): Single<String>
    fun resetGame()

}