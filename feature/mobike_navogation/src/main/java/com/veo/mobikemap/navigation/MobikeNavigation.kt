package com.veo.mobikemap.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.veo.mobikemap.ui.MapDetailPaperRoute
import com.veo.mobikemap.ui.MapScreenRoute


const val mapDetailRoute = "map_route/map_detail"
const val mapScreenRoute = "map_route/map_screen"


fun NavController.navigateToMapDetail(navOptions: NavOptions? = null) {
    this.navigate(mapDetailRoute, navOptions)
}



fun NavGraphBuilder.forMapDetailScreen() {
    composable(
        route = mapDetailRoute,
    ) {
        MapDetailPaperRoute()
    }
}
fun NavGraphBuilder.forMapScreenRoute() {
    composable(
        route = mapScreenRoute,
    ) {
        MapScreenRoute()
    }
}