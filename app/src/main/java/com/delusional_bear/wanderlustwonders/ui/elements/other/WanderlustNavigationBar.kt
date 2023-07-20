package com.delusional_bear.wanderlustwonders.ui.elements.other

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.delusional_bear.wanderlustwonders.R
import com.delusional_bear.wanderlustwonders.data.Destination

@Composable
fun WanderlustNavigationBar(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry?,
    modifier: Modifier = Modifier,
) {
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(tonalElevation = 10.dp, modifier = modifier) {
        NavigationBarItem(
            selected = currentDestination?.route == Destination.Home.route,
            onClick = { navController.navigateUp() },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.home),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        )
        NavigationBarItem(
            selected = currentDestination?.route == Destination.Favorites.route,
            onClick = {
                navController.navigate(Destination.Favorites.route) {
                    popUpTo(Destination.Home.route)
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.favorites),
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        )
    }
}