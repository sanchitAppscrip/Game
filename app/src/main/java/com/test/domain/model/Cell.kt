package com.test.domain.model

import com.test.app.utils.StringUtility


class Cell(var player: Player?) {
    val isEmpty: Boolean
        get() = player == null || StringUtility.isNullOrEmpty(player!!.value)
}