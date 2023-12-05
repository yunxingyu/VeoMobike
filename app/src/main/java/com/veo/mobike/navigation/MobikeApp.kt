package com.veo.mobike.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.veo.common.data.util.NetworkMonitor
import com.veo.mobikemap.navigation.forMapDetailScreen
import com.veo.mobikemap.navigation.forMapScreenRoute
import com.veo.mobikemap.navigation.mapScreenRoute
import com.veo.mobike.R
import com.veo.mobike.ui.BackPressHandler
import com.veo.usercenter.navigation.forMineScreen


@Composable
fun MobikeApp(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    appState: HomeState = rememberHomeState(
        networkMonitor = networkMonitor,
        windowSizeClass = windowSizeClass,
    ),
) {
    val bottomNavItem = getBottomNavItems()
    val screensWithShowNavBar = listOf(
        HomeScreens.MapScreen.name,
        HomeScreens.MapDetail.name,
        HomeScreens.PreExam.name,
    )
    val backStackEntry = appState.navController.currentBackStackEntryAsState()

    var shouldHideBottomBar: Boolean by remember {
        mutableStateOf(true)
    }
    val snackbarHostState = remember { SnackbarHostState() }

    var isInSelectionMode by remember {
        mutableStateOf(false)
    }
    val resetSelectionMode = {
        isInSelectionMode = false
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = shouldHideBottomBar,
                enter = fadeIn(animationSpec = tween(delayMillis = 500, easing = LinearOutSlowInEasing)),
                exit = fadeOut(animationSpec = tween(delayMillis = 500, easing = LinearOutSlowInEasing)),
            ) {
                BottomNavigationBar(
                    backStackEntry,
                    bottomNavItem,
                    screensWithShowNavBar,
                    appState.navController,
                )
            }
        },
    ) { _ ->
        NavHost(
            navController = appState.navController,
            modifier = Modifier,
            startDestination = mapScreenRoute,
        ) {
            forMapScreenRoute()
            forMapDetailScreen()
            forMineScreen(appState.navController)
        }
    }

    BackPressHandler(isInSelectionMode, resetSelectionMode)
}

@Composable
fun BottomNavigationBar(
    backStackEntry: State<NavBackStackEntry?>,
    bottomNavItem: List<BottomNavItem>,
    screensWithShowNavBar: List<String>,
    navController: NavHostController,
) {
    if (backStackEntry.value?.destination?.route in screensWithShowNavBar) {
        NavigationBar(containerColor = Color.Transparent, modifier = Modifier.height(125.dp)) {
            bottomNavItem.forEach { item ->
                NavigationBarItem(
                    alwaysShowLabel = true,
                    icon = {
                        Image(
                            painter = painterResource(
                                id = if (backStackEntry.value?.destination?.route == item.route) {
                                    item.icon
                                } else {
                                    item.unSelecticon
                                },
                            ),
                            contentDescription = item.name,
                            alpha = 1f,
                        )
                    },
                    label = {
                        Text(
                            text = item.name,
                            color = if (backStackEntry.value?.destination?.route == item.route) {
                                MaterialTheme.colorScheme.onSurface
                            } else {
                                MaterialTheme.colorScheme.secondary
                            },
                            fontWeight = if (backStackEntry.value?.destination?.route == item.route) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            },
                        )
                    },
                    selected = backStackEntry.value?.destination?.route == item.route,
                    onClick = {
                        val currentDestination = navController.currentBackStackEntry?.destination?.route
                        if (item.route != currentDestination) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    },
                )
            }
        }
    }
}

fun getBottomNavItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem(
            name = "Map interface",
            route = HomeScreens.MapScreen.name,
            icon = R.drawable.app_jiaocai_select,
            unSelecticon = R.drawable.app_jiaocai_unselect,
        ),
        BottomNavItem(
            name = "Map navigation",
            route = HomeScreens.MapDetail.name,
            icon = R.drawable.app_error_select,
            unSelecticon = R.drawable.app_error_unselect,
        ),
        BottomNavItem(
            name = "Mine",
            route = HomeScreens.PreExam.name,
            icon = R.drawable.app_kaoqian_select,
            unSelecticon = R.drawable.app_kaoqian_unselect,
        ),
    )
}
