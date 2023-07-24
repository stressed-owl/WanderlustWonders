package com.delusional_bear.wanderlustwonders.ui

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.delusional_bear.wanderlustwonders.R
import com.delusional_bear.wanderlustwonders.data.City
import com.delusional_bear.wanderlustwonders.data.DataSource
import com.delusional_bear.wanderlustwonders.data.Destination
import com.delusional_bear.wanderlustwonders.ui.elements.dialogs.AddingCityDialog
import com.delusional_bear.wanderlustwonders.ui.elements.fabs.AddingCityFAB
import com.delusional_bear.wanderlustwonders.ui.elements.other.WanderlustNavigationBar
import com.delusional_bear.wanderlustwonders.ui.elements.other.WanderlustTopAppBar
import com.delusional_bear.wanderlustwonders.ui.screens.CityDetailsScreen
import com.delusional_bear.wanderlustwonders.ui.screens.CityListScreen
import com.delusional_bear.wanderlustwonders.ui.screens.UserCityListScreen
import com.delusional_bear.wanderlustwonders.ui.viewmodel.WanderlustViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WanderlustWondersApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: WanderlustViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val dataSource = DataSource(context = context)

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = backStackEntry?.destination

    Scaffold(
        topBar = {
            WanderlustTopAppBar(
                onSortByCityClick = { viewModel.setSortByCity() },
                onSortByCountryClick = { viewModel.setSortByCountry() },
                canNavigateBack = backStackEntry?.arguments != null,
                onNavigateBackClick = { navController.navigateUp() },
                onOpenMenuClick = { viewModel.setDropDownMenuState() },
                isDropDownMenuExpanded = uiState.isDropDownMenuOpen,
                onDropdownMenuDismiss = { viewModel.setDropDownMenuState() },
            )
        },
        bottomBar = {
            WanderlustNavigationBar(
                navController = navController,
                navBackStackEntry = backStackEntry,
            )
        },
        modifier = modifier,
        floatingActionButton = {
            if (currentDestination?.route == Destination.Favorites.route) {
                RichTooltipBox(
                    text = {
                        Text(
                            text = stringResource(id = R.string.add_city_desc),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    title = {
                        Text(
                            text = stringResource(id = R.string.add_city),
                            style = MaterialTheme.typography.titleSmall,
                        )
                    },
                    action = {
                        TextButton(onClick = { }) {
                            Text(
                                text = stringResource(id = R.string.learn_more),
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                ) {
                    AddingCityFAB(modifier = Modifier.tooltipAnchor()) { viewModel.openDialog() }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Destination.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Destination.Home.route) {
                CityListScreen(
                    isSortedByCity = uiState.isSortedByCity,
                    isSortedByCountry = uiState.isSortedByCountry,
                    dataSource = dataSource,
                    onCardClick = {
                        val route = Destination.Details.createRoute(it.id)
                        navController.navigate(route = route) {
                            popUpTo(Destination.Home.route)
                        }
                    }
                )
            }
            composable(Destination.Details.route) { entry ->
                val cityId = entry.arguments?.getString("cityId")
                if (cityId == null) {
                    Toast.makeText(context, "City hasn't been found", Toast.LENGTH_LONG).show()
                } else {
                    CityDetailsScreen(cityId = cityId.toInt(), dataSource = dataSource)
                }
            }
            composable(Destination.Favorites.route) {
                UserCityListScreen(userCityList = uiState.usersCityList)
            }
        }
    }
    if (uiState.isDialogOpen) {
        AddingCityDialog(
            cityValue = viewModel.cityValue,
            countryValue = viewModel.countryValue,
            stateValue = viewModel.stateValue,
            onDismissRequest = {
                viewModel.openDialog()
                viewModel.resetFields()
            },
            onConfirmButtonClick = {
                viewModel.addCity(
                    City(
                        name = viewModel.cityValue,
                        country = viewModel.countryValue,
                        state = viewModel.stateValue,
                    )
                )
                viewModel.resetFields()
            },
            onCityValueChange = { viewModel.setCity(it) },
            onCountryValueChange = { viewModel.setCountry(it) },
            onStateValueChange = { viewModel.setState(it) },
            isError = uiState.isError,
        ) {
            viewModel.openDialog()
        }
    }
}
