package com.maxidev.mtghorizon.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.maxidev.mtghorizon.presentation.detail.CardDataScreen
import com.maxidev.mtghorizon.presentation.home.HomeScreen
import com.maxidev.mtghorizon.presentation.search.SearchScreen
import kotlinx.serialization.Serializable

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: NavDestinations = NavDestinations.HomeView
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavBarDestinations.routeScreens.forEach { screen ->
                    val selected = currentRoute?.hierarchy?.any {
                        it.route?.equals(screen.route) == true } == true

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            val popUp = navController.graph.findStartDestination().id

                            navController.navigate(screen.route) {
                                popUpTo(popUp) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        // TODO: Change icon if selected.
                        icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                        label = { Text(text = screen.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination
        ) {
            composable<NavDestinations.HomeView> {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(route = NavDestinations.CardDataView(id))
                    }
                )
            }
            composable<NavDestinations.SearchView> {
                SearchScreen(
                    navigateToDetail = { id ->
                        navController.navigate(route = NavDestinations.CardDataView(id))
                    }
                )
            }
            composable<NavDestinations.MyCardsView> { TODO() }
            composable<NavDestinations.CounterView> { TODO() }
            composable<NavDestinations.CardDataView> { backStackEntry ->
                val args = backStackEntry.toRoute<NavDestinations.CardDataView>()

                CardDataScreen(id = args.id)
            }
        }
    }
}

// TODO: Encapsulate the navigation code

sealed interface NavDestinations {
    @Serializable data object HomeView: NavDestinations
    @Serializable data object SearchView: NavDestinations
    @Serializable data object MyCardsView: NavDestinations
    @Serializable data object CounterView: NavDestinations
    @Serializable data object SettingsView: NavDestinations
    @Serializable data class CardDataView(val id: String): NavDestinations
}

sealed class NavBarDestinations(
    val route: NavDestinations,
    val icon: ImageVector,
    val title: String
) {
    data object Home: NavBarDestinations(
        route = NavDestinations.HomeView,
        icon = Icons.Filled.Home,
        title = "Home"
    )
    data object Search: NavBarDestinations(
        route = NavDestinations.SearchView,
        icon = Icons.Filled.Search,
        title = "Search"
    )
    data object MyCards: NavBarDestinations(
        route = NavDestinations.MyCardsView,
        icon = Icons.Filled.Bookmark, // TODO: Change icon
        title = "My Cards"
    )
    data object Counter: NavBarDestinations(
        route = NavDestinations.CounterView,
        icon = Icons.Filled.Timer, // TODO: Change icon
        title = "Counter"
    )
    data object Settings: NavBarDestinations(
        route = NavDestinations.SettingsView,
        icon = Icons.Filled.Settings,
        title = "Settings"
    )

    companion object {
        val routeScreens = listOf(
            Home,
            Search,
            MyCards,
            Counter,
            Settings
        )
    }
}