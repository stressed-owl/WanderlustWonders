package com.delusional_bear.wanderlustwonders.ui.state

import com.delusional_bear.wanderlustwonders.data.City

data class WanderlustUiState(
    val usersCityList: MutableList<City> = mutableListOf(),
    val isError: Boolean = false,
    val isDialogOpen: Boolean = false,
    val isDropDownMenuOpen: Boolean = false,
    val isSortedByCity: Boolean = false,
    val isSortedByCountry: Boolean = false,
)
