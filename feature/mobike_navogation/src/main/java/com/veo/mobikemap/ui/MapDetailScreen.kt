package com.veo.mobikemap.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.location.Location
import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.github.bkhezry.extramaputils.builder.ViewOptionBuilder
import com.github.bkhezry.extramaputils.model.ViewOption
import com.github.bkhezry.extramaputils.utils.MapUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import com.veo.mobikemap.R
import com.veo.mobikemap.ui.dialog.LocationPermissionsAndSettingDialogs
import com.veo.mobikemap.utils.LocationUtils
import com.veo.mobikemap.utils.getListExtraMarker
import com.veo.mobikemap.utils.getPolygon_1
import com.veo.mobikemap.utils.getPolyline_2
import com.veo.mobikemap.utils.getPolyline_4
import com.veo.mobikemap.viewmodel.MobikeViewModel


@Composable
internal fun MapDetailPaperRoute(
    modifier: Modifier = Modifier,
    viewModel: MobikeViewModel = hiltViewModel(),
) {

    val activity = LocalContext.current as? Activity
    val fusedLocationProviderClient = activity?.let {
        LocationServices.getFusedLocationProviderClient(
            it
        )
    }

    MapNavigationScreen(
        fusedLocationProviderClient,
        modifier,
                activity
    )
}

@SuppressLint("MissingPermission")
@Composable
fun MapNavigationScreen(
    fusedLocationProviderClient: FusedLocationProviderClient?,
    modifier: Modifier = Modifier,
    activity:Activity?
) {

    var currentLocation by remember { mutableStateOf(LocationUtils.getDefaultLocation()) }

    val cameraPositionState = rememberCameraPositionState()
    cameraPositionState.position = CameraPosition.fromLatLngZoom(
        LocationUtils.getPosition(currentLocation), 12f
    )

    var requestLocationUpdate by remember { mutableStateOf(true) }
    Scaffold {
        Surface(
            modifier = modifier.padding(it)
        ) {
            Column(modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    MyGoogleMap(
                        modifier,
                        currentLocation,
                        cameraPositionState,
                        onGpsIconClick = {
                            requestLocationUpdate = true
                        }
                    )
                })
        }
    }

    if (requestLocationUpdate) {
        LocationPermissionsAndSettingDialogs(
            updateCurrentLocation = {
                requestLocationUpdate = false
                fusedLocationProviderClient?.let {
                    LocationUtils.requestLocationResultCallback(it) { locationResult ->

                        locationResult.lastLocation?.let { location ->
                            currentLocation = location
                        }

                    }
                }
            }
        )
    }
}

@Composable
private fun MyGoogleMap(
    modifier: Modifier = Modifier,
    currentLocation: Location,
    cameraPositionState: CameraPositionState,
    onGpsIconClick: () -> Unit
) {
    if (LocalInspectionMode.current) {
        Box(modifier = modifier)
        return
    }
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    AndroidView(modifier = modifier, factory = { mapView })
    MapLifecycle(mapView)

    GpsIconButton(onIconClick = onGpsIconClick)

    DebugOverlay(cameraPositionState)
}

@Composable
private fun GpsIconButton(onIconClick: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            IconButton(onClick = onIconClick) {
                Icon(
                    modifier = Modifier.padding(bottom = 100.dp, end = 20.dp),
                    painter = painterResource(id = R.drawable.mn_gps_fixed),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun DebugOverlay(
    cameraPositionState: CameraPositionState,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        val moving =
            if (cameraPositionState.isMoving) "moving" else "not moving"
        androidx.compose.material.Text(
            text = "Camera is $moving",
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        androidx.compose.material.Text(
            text = "Camera position is ${cameraPositionState.position}",
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
    }
}

    @Composable
    private fun MapLifecycle(mapView: MapView) {
        val context = LocalContext.current
        val lifecycle = LocalLifecycleOwner.current.lifecycle
        val previousState = remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
        DisposableEffect(context, lifecycle, mapView) {
            val mapLifecycleObserver = mapView.lifecycleObserver(previousState)
            val callbacks = mapView.componentCallbacks()

            lifecycle.addObserver(mapLifecycleObserver)
            context.registerComponentCallbacks(callbacks)

            onDispose {
                lifecycle.removeObserver(mapLifecycleObserver)
                context.unregisterComponentCallbacks(callbacks)
            }
        }
        DisposableEffect(mapView) {
            onDispose {
                mapView.onDestroy()
                mapView.removeAllViews()
            }
        }
    }


private fun MapView.lifecycleObserver(previousState: MutableState<Lifecycle.Event>): LifecycleEventObserver =
    LifecycleEventObserver { _, event ->
        event.targetState
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                // Skip calling mapView.onCreate if the lifecycle did not go through onDestroy - in
                // this case the GoogleMap composable also doesn't leave the composition. So,
                // recreating the map does not restore state properly which must be avoided.
                if (previousState.value != Lifecycle.Event.ON_STOP) {
                    this.onCreate(Bundle())
                }
            }

            Lifecycle.Event.ON_START -> this.onStart()
            Lifecycle.Event.ON_RESUME -> this.onResume()
            Lifecycle.Event.ON_PAUSE -> this.onPause()
            Lifecycle.Event.ON_STOP -> this.onStop()
            Lifecycle.Event.ON_DESTROY -> {
                //handled in onDispose
            }

            else -> throw IllegalStateException()
        }
        previousState.value = event
    }

private fun MapView.componentCallbacks(): ComponentCallbacks =
    object : ComponentCallbacks {
        override fun onConfigurationChanged(config: Configuration) {

        }

        override fun onLowMemory() {
            this@componentCallbacks.onLowMemory()
        }
    }
