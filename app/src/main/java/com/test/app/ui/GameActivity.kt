package com.test.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.test.app.ui.viewmodel.GameViewModel
import com.test.mygame.R
import com.test.mygame.databinding.ActivityGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {
    private lateinit var viewModel: GameViewModel
    private lateinit var activityGameBinding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
    }

    private fun initDataBinding() {
        activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        viewModel.init()

        activityGameBinding.gameViewModel = viewModel
        activityGameBinding.lifecycleOwner = this
    }
}