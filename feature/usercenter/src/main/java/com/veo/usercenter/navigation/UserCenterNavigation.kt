package com.veo.usercenter.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.veo.usercenter.ui.MineRoute

const val userNavigationRoute = "user_route/mine"

fun NavController.navigateToUserCenter(navOptions: NavOptions? = null) {
    this.navigate(userNavigationRoute, navOptions)
}


fun NavGraphBuilder.forMineScreen(navController: NavHostController) {
    composable(
        route = userNavigationRoute,
    ) {
        MineRoute(navController)
    }
}
