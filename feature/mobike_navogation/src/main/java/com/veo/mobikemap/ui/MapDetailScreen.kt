package com.veo.mobikemap.ui

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
import com.veo.mobikemap.viewmodel.MobikeViewModel

@Composable
internal fun MapDetailPaperRoute(
    modifier: Modifier = Modifier,
    viewModel: MobikeViewModel = hiltViewModel(),
) {
//    val onboardingUiState by viewModel.onboardingUiState.collectAsStateWithLifecycle()
//    val feedState by viewModel.feedState.collectAsStateWithLifecycle()
//    val isSyncing by viewModel.isSyncing.collectAsStateWithLifecycle()
//    val deepLinkedUserNewsResource by viewModel.deepLinkedNewsResource.collectAsStateWithLifecycle()

    MapDetailScreen(
//    isSyncing = isSyncing,
//    onTopicCheckedChanged = viewModel::updateTopicSelection,
//    onDeepLinkOpened = viewModel::onDeepLinkOpened,
//    onTopicClick = onTopicClick,
//    saveFollowedTopics = viewModel::dismissOnboarding,
//    onNewsResourcesCheckedChanged = viewModel::updateNewsResourceSaved,
//    onNewsResourceViewed = { viewModel.setNewsResourceViewed(it, true) },
        modifier = modifier,
    )
}

@Composable
internal fun MapDetailScreen(
//    isSyncing: Boolean,
//    onTopicCheckedChanged: (String, Boolean) -> Unit,
//    onTopicClick: (String) -> Unit,
//    onDeepLinkOpened: (String) -> Unit,
//    saveFollowedTopics: () -> Unit,
//    onNewsResourcesCheckedChanged: (String, Boolean) -> Unit,
//    onNewsResourceViewed: (String) -> Unit,
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
            content = { Text("Map navigation") },
        )
    }
}