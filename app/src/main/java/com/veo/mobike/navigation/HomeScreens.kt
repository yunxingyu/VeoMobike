package com.veo.mobike.navigation

import com.veo.mobikemap.navigation.mapDetailRoute
import com.veo.mobikemap.navigation.mapScreenRoute
import com.veo.usercenter.navigation.userNavigationRoute

sealed class HomeScreens(val name: String) {
    data object MapScreen : HomeScreens(mapScreenRoute)
    data object MapDetail : HomeScreens(mapDetailRoute)
    data object PreExam : HomeScreens(userNavigationRoute)
}
