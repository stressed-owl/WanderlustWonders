package com.delusional_bear.wanderlustwonders.ui.elements.fabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.delusional_bear.wanderlustwonders.R

@Composable
fun AddingCityFAB(modifier: Modifier = Modifier, onFABClick: () -> Unit) {
    FloatingActionButton(
        onClick = onFABClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
        )
    }
}