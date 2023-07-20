package com.delusional_bear.wanderlustwonders.ui.elements.other

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.delusional_bear.wanderlustwonders.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WanderlustTopAppBar(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    onNavigateBackClick: () -> Unit,
    onSortByCityClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
            )
        },
        actions = {
            if (!canNavigateBack) {
                PlainTooltipBox(
                    tooltip = {
                        Text(
                            text = stringResource(id = R.string.action_sort_cities_desc),
                            style = MaterialTheme.typography.titleSmall,
                        )
                    },
                ) {
                    IconButton(
                        onClick = onSortByCityClick,
                        modifier = Modifier.tooltipAnchor()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.sort_icon),
                            contentDescription = null,
                        )
                    }
                }
            }
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        }
    )
}