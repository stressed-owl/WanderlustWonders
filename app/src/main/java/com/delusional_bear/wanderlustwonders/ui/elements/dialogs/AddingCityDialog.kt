package com.delusional_bear.wanderlustwonders.ui.elements.dialogs

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.delusional_bear.wanderlustwonders.R

@Composable
fun AddingCityDialog(
    cityValue: String,
    countryValue: String,
    stateValue: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onCityValueChange: (String) -> Unit,
    onCountryValueChange: (String) -> Unit,
    onStateValueChange: (String) -> Unit,
    onConfirmButtonClick: () -> Unit,
    onDismissButtonClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        text = {
            Column {
                Text(
                    text = stringResource(id = R.string.add_city_alert_text),
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(16.dp))
                CityInfoTextField(
                    value = cityValue,
                    onValueChange = onCityValueChange,
                    placeholder = R.string.kyiv,
                    isError = isError,
                )
                Spacer(modifier = Modifier.height(8.dp))
                CityInfoTextField(
                    value = countryValue,
                    onValueChange = onCountryValueChange,
                    placeholder = R.string.ukraine,
                    isError = isError,
                )
                Spacer(modifier = Modifier.height(8.dp))
                CityInfoTextField(
                    value = stateValue,
                    onValueChange = onStateValueChange,
                    placeholder = R.string.arizona,
                    isError = false,
                )
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.add_circle),
                contentDescription = null,
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirmButtonClick,
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.add),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onDismissButtonClick,
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun CityInfoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    @StringRes placeholder: Int,
    isError: Boolean,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = stringResource(id = placeholder),
                style = MaterialTheme.typography.titleSmall,
            )
        },
        modifier = modifier,
        textStyle = MaterialTheme.typography.titleSmall,
        supportingText = {
            if (isError) {
                Text(
                    text = stringResource(id = R.string.field_label),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        isError = isError
    )
}