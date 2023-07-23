package com.delusional_bear.wanderlustwonders.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delusional_bear.wanderlustwonders.data.City
import com.delusional_bear.wanderlustwonders.data.DataSource
import com.delusional_bear.wanderlustwonders.ui.elements.cards.CityCard

@Composable
fun CityListScreen(
    dataSource: DataSource,
    modifier: Modifier = Modifier,
    isSortedByCity: Boolean,
    onCardClick: (City) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                if (isSortedByCity) dataSource.getSortedByCityList()
                else dataSource.getCitiesList()
            ) { city ->
                CityCard(
                    city = city,
                    onCardClick = { onCardClick(city) }
                )
            }
        }
    }
}