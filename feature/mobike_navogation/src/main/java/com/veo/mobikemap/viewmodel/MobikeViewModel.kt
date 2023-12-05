package com.veo.mobikemap.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.github.bkhezry.extramaputils.builder.ViewOptionBuilder
import com.github.bkhezry.extramaputils.model.ViewOption
import com.github.bkhezry.extramaputils.utils.MapUtils
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.veo.common.ActivityUtils
import com.veo.common.network.DispatcherProvider
import com.veo.mobikemap.repository.MobikeRepository
import com.veo.mobikemap.utils.getListExtraMarker
import com.veo.mobikemap.utils.getPolygon_1
import com.veo.mobikemap.utils.getPolyline_2
import com.veo.mobikemap.utils.getPolyline_4
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MobikeViewModel@Inject constructor(
    application: Application,
    private val mobikeRepository: MobikeRepository,
    private val dispatcherProvider: DispatcherProvider,
) : AndroidViewModel(application), OnMapReadyCallback {
    override fun onMapReady(googleMap: GoogleMap ) {
      var viewOption=  ViewOptionBuilder()
            .withStyleName(ViewOption.StyleDef.RETRO)
            .withCenterCoordinates(LatLng(35.6892, 51.3890))
            .withMarkers(getListExtraMarker())
            .withPolygons(
                getPolygon_1()
            )
            .withPolylines(
                getPolyline_2(),
                getPolyline_4()
            )
            .withForceCenterMap(false)
            .build()

        MapUtils.showElements(viewOption, googleMap, ActivityUtils.getActivityByContext(ActivityUtils.activity!!));
    }

}
