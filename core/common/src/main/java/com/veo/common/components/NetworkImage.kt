package com.veo.common.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.veo.common.R

private const val TAG = "NetworkImage"

@Composable
fun NetworkImage(imageUrl: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true)
            .build(),
        contentDescription = "compose",
        placeholder = painterResource(id = R.drawable.comm_place_holder),
        error = painterResource(id = R.drawable.comm_place_holder),
        onSuccess = { Log.d("Htm", "success") },
        onError = { error -> Log.d(TAG, "error") },
        onLoading = { loading -> Log.d(TAG, "loading") },
    )
}
