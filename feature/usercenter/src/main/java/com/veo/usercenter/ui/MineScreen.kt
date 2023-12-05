package com.veo.usercenter.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.veo.common.di.ThemeViewModel
import com.veo.usercenter.R
import com.veo.usercenter.model.MineViewModel

@Composable
internal fun MineRoute(
    controller : NavController,
    modifier: Modifier = Modifier,
    viewModel: MineViewModel = hiltViewModel(),
) {
//    val onboardingUiState by viewModel.onboardingUiState.collectAsStateWithLifecycle()
//    val feedState by viewModel.feedState.collectAsStateWithLifecycle()
//    val isSyncing by viewModel.isSyncing.collectAsStateWithLifecycle()
//    val deepLinkedUserNewsResource by viewModel.deepLinkedNewsResource.collectAsStateWithLifecycle()

    MineScreen(
        modifier = modifier,
        controller = controller,
    )
}

@Composable
internal fun MineScreen(
    modifier: Modifier = Modifier,
    controller : NavController
) {

    val themeViewModel: ThemeViewModel = hiltViewModel()
    val themeState by themeViewModel.themeState.collectAsState()

    Scaffold {
        Surface(
            modifier = modifier.padding(it)
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                item {
                    Text(
                        modifier = modifier.padding(start = 5.dp),
                        text = stringResource(R.string.user_center),
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    SettingsSwitchCard(
                        text = stringResource(id = R.string.user_dark_mode),
                        icon = painterResource(id = R.drawable.user_moon_icon),
                        isChecked = themeState.isDarkMode,
                        onCheckedChange = {
                            themeViewModel.toggleTheme()
                        }
                    )
                    Spacer(modifier = modifier.height(6.dp))
                }
            }
        }
    }

}
