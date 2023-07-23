package com.delusional_bear.wanderlustwonders.ui.elements.fabs

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddingCityFAB(modifier: Modifier = Modifier, onFABClick: () -> Unit) {
    LargeFloatingActionButton(
        onClick = onFABClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
            modifier = Modifier.size(36.dp),
        )
    }
}