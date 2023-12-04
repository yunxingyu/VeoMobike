package com.veo.usercenter.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.veo.usercenter.model.MineViewModel

@Composable
internal fun MineRoute(
    modifier: Modifier = Modifier,
    viewModel: MineViewModel = hiltViewModel(),
) {
//    val onboardingUiState by viewModel.onboardingUiState.collectAsStateWithLifecycle()
//    val feedState by viewModel.feedState.collectAsStateWithLifecycle()
//    val isSyncing by viewModel.isSyncing.collectAsStateWithLifecycle()
//    val deepLinkedUserNewsResource by viewModel.deepLinkedNewsResource.collectAsStateWithLifecycle()

    MineScreen(
        modifier = modifier,
    )
}

@Composable
internal fun MineScreen(
    modifier: Modifier = Modifier,
) {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        Button(
            onClick = {
            },
            modifier = modifier,
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
            ),
            contentPadding = PaddingValues(
                start = 24.dp,
                top = 4.dp,
                end = 24.dp,
                bottom = 4.dp,
            ),
            content = { Text("个人中心") },
        )
    }
}
