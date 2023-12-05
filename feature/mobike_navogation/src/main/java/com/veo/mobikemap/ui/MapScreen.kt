package com.veo.mobikemap.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.*
import com.veo.mobikemap.R
import com.veo.mobikemap.ui.dialog.LocationPermissionsAndSettingDialogs
import com.veo.mobikemap.utils.LocationUtils
import com.veo.mobikemap.viewmodel.MobikeViewModel

@Composable
internal fun MapScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: MobikeViewModel = hiltViewModel(),
) {
//    val onboardingUiState by viewModel.onboardingUiState.collectAsStateWithLifecycle()
//    val feedState by viewModel.feedState.collectAsStateWithLifecycle()
//    val isSyncing by viewModel.isSyncing.collectAsStateWithLifecycle()
//    val deepLinkedUserNewsResource by viewModel.deepLinkedNewsResource.collectAsStateWithLifecycle()
    val activity = LocalContext.current as? Activity
   val  fusedLocationProviderClient = activity?.let {
       LocationServices.getFusedLocationProviderClient(
           it
       )
   }

    MapScreen(
        fusedLocationProviderClient,
        modifier
    )
}
@SuppressLint("MissingPermission")
@Composable
fun MapScreen(
    fusedLocationProviderClient: FusedLocationProviderClient?,
    modifier: Modifier = Modifier,
) {

    var currentLocation by remember { mutableStateOf(LocationUtils.getDefaultLocation()) }

    val cameraPositionState = rememberCameraPositionState()
    cameraPositionState.position = CameraPosition.fromLatLngZoom(
        LocationUtils.getPosition(currentLocation), 12f)

    var requestLocationUpdate by remember { mutableStateOf(true)}
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        MyGoogleMap(
            currentLocation,
            cameraPositionState,
            onGpsIconClick = {
                requestLocationUpdate = true
            }
        )
    }

    if(requestLocationUpdate) {
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
    currentLocation: Location,
    cameraPositionState: CameraPositionState,
    onGpsIconClick: () -> Unit) {

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(zoomControlsEnabled = false)
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
    ) {
        Marker(
            state = MarkerState(position = LocationUtils.getPosition(currentLocation)),
            title = "Current Position"
        )
    }

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
        Text(
            text = "Camera is $moving",
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray)
        Text(
            text = "Camera position is ${cameraPositionState.position}",
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray)
    }
}


