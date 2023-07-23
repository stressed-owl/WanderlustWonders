package com.delusional_bear.wanderlustwonders.ui.elements.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailsIconButton(
    @StringRes tooltipText: Int,
    @StringRes tooltipTitle: Int,
    @StringRes toolTipButtonText: Int,
    @DrawableRes buttonIcon: Int,
    modifier: Modifier = Modifier,
    onIconButtonClick: () -> Unit
) {
    RichTooltipBox(
        text = {
            Text(
                text = stringResource(id = tooltipText),
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        title = {
            Text(
                text = stringResource(id = tooltipTitle),
                style = MaterialTheme.typography.titleSmall,
            )
        },
        action = {
            TextButton(onClick = { }) {
                Text(
                    text = stringResource(id = toolTipButtonText),
                    style = MaterialTheme.typography.titleSmall
                )
            }
        },
        modifier = modifier,
    ) {
        IconButton(
            onClick = onIconButtonClick,
            modifier = Modifier.tooltipAnchor(),
        ) {
            Icon(
                painter = painterResource(id = buttonIcon),
                contentDescription = null,
            )
        }
    }
}