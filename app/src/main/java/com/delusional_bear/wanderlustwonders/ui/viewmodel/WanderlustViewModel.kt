package com.delusional_bear.wanderlustwonders.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.delusional_bear.wanderlustwonders.data.City
import com.delusional_bear.wanderlustwonders.ui.state.WanderlustUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WanderlustViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WanderlustUiState())
    val uiState: StateFlow<WanderlustUiState> = _uiState.asStateFlow()

    private val usersCityList = mutableStateListOf<City>()

    var cityValue by mutableStateOf("")
    var countryValue by mutableStateOf("")
    var stateValue by mutableStateOf("")

    fun addCity(city: City) {
        if (isError()) {
            _uiState.update { currentState ->
                currentState.copy(
                    isError = true
                )
            }
        } else {
            if (!usersCityList.contains(city)) {
                usersCityList.add(city)
                _uiState.update { currentState ->
                    currentState.copy(
                        usersCityList = usersCityList,
                        isError = false,
                        isDialogOpen = false
                    )
                }
            }
        }
    }

    fun setCity(city: String) {
        cityValue = city
    }

    fun setCountry(country: String) {
        countryValue = country
    }

    fun setState(state: String) {
        stateValue = state
    }

    fun setDropDownMenuState() {
        _uiState.update { currentState ->
            currentState.copy(
                isDropDownMenuOpen = !currentState.isDropDownMenuOpen
            )
        }
    }

    fun setSortByCity() {
        _uiState.update { currentState ->
            currentState.copy(
                isSortedByCity = true,
                isSortedByCountry = false,
            )
        }
    }

    fun setSortByCountry() {
        _uiState.update { currentState ->
            currentState.copy(
                isSortedByCountry = true,
                isSortedByCity = false,
            )
        }
    }

    fun resetFields() {
        cityValue = ""
        countryValue = ""
        stateValue = ""
    }

    fun openDialog() {
        _uiState.update { currentState ->
            currentState.copy(
                isDialogOpen = !currentState.isDialogOpen,
                isError = false
            )
        }
    }

    private fun isError(): Boolean {
        if (cityValue.isEmpty() || countryValue.isEmpty()) return true
        return false
    }
}