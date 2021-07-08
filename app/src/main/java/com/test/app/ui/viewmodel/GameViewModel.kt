package com.test.app.ui.viewmodel

import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.data.repo.GameRepoImpl
import com.test.domain.model.Player
import com.test.data.model.Response
import com.test.app.utils.StringUtility.stringFromNumbers
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepo: GameRepoImpl
) : ViewModel() {
    private val disposables = CompositeDisposable()

    var cells: ObservableArrayMap<String, String>? = null

    private lateinit var currentPlayer: Player
    private var player1 = Player("Player1", "x")
    private var player2 = Player("Player2", "o")

    private var gameReset = true

    val response: MutableLiveData<Response> = MutableLiveData<Response>()


    fun init() {
        cells = ObservableArrayMap()
        currentPlayer = player1
        response.value = Response.success("Player1 turn")
    }

    fun onClickedCellAt(row: Int, column: Int, cellNo: Int) {
        val key = stringFromNumbers(row, column)
        if (cells?.containsKey(key) == false) {
            if (!gameReset) {
                currentPlayer = if (currentPlayer.value == "x")
                    player2
                else
                    player1
            }
            gameReset = false

            disposables.add(gameRepo.changeGameState(cellNo, currentPlayer)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { response.setValue(Response.loading()) }
                .subscribe(
                    { gameStatus ->
                        response.value = Response.success(gameStatus)
                        if (!gameStatus.equals("GameOver"))
                            cells?.put(stringFromNumbers(row, column), currentPlayer.value)
                    }
                ) { throwable -> response.setValue(Response.error(throwable)) }
            )
        }
    }

    fun reset() {
        cells?.forEach { (key, _) ->
            cells?.put(key, "")
        }
        init()
        gameReset = true
        disposables.clear()
        gameRepo.resetGame()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}