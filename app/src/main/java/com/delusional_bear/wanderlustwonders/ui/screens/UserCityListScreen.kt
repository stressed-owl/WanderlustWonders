package com.delusional_bear.wanderlustwonders.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.delusional_bear.wanderlustwonders.R
import com.delusional_bear.wanderlustwonders.data.City
import com.delusional_bear.wanderlustwonders.ui.elements.cards.UserCityCard

@Composable
fun UserCityListScreen(
    userCityList: List<City>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (userCityList.isEmpty()) {
                item {
                    Text(
                        text = stringResource(id = R.string.no_city_found),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillParentMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            } else {
                items(userCityList) { city ->
                    UserCityCard(city = city)
                }
            }
        }
    }
}