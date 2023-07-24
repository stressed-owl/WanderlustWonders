package com.delusional_bear.wanderlustwonders.ui.elements.other

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.delusional_bear.wanderlustwonders.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WanderlustTopAppBar(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    isDropDownMenuExpanded: Boolean,
    onDropdownMenuDismiss: () -> Unit,
    onOpenMenuClick: () -> Unit,
    onNavigateBackClick: () -> Unit,
    onSortByCityClick: () -> Unit,
    onSortByCountryClick: () -> Unit,
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
                IconButton(onClick = onOpenMenuClick) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
                }
                DropdownMenu(
                    expanded = isDropDownMenuExpanded,
                    onDismissRequest = onDropdownMenuDismiss,
                ) {
                    DropdownMenuItem(
                        text = {
                            DropDownMenuItemText(text = R.string.sort_by_city)
                        },
                        onClick = onSortByCityClick,
                    )
                    DropdownMenuItem(
                        text = {
                            DropDownMenuItemText(text = R.string.sort_by_country)
                        },
                        onClick = onSortByCountryClick,
                    )
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

@Composable
private fun DropDownMenuItemText(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(id = text),
        modifier = modifier,
        style = MaterialTheme.typography.titleSmall,
    )
}