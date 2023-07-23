package com.delusional_bear.wanderlustwonders.data

sealed class Destination(val route: String) {
    object Home: Destination("home")
    object Favorites: Destination("favorites")
    object Details: Destination("details/{cityId}") {
        fun createRoute(cityId: Int) = "details/$cityId"
    }
}
