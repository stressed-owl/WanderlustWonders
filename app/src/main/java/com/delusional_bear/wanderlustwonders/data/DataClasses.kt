package com.delusional_bear.wanderlustwonders.data

import androidx.annotation.StringRes

data class City(
    val id: Int = 2,
    val name: String,
    val country: String,
    val state: String?,
    val imageURL: String = "",
    @StringRes val description: Int = 0,
)