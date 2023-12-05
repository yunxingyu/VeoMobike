package com.veo.mobikemap.utils

import android.graphics.Color
import androidx.annotation.IdRes
import com.github.bkhezry.extramaputils.builder.ExtraMarkerBuilder
import com.github.bkhezry.extramaputils.builder.ExtraPolygonBuilder
import com.github.bkhezry.extramaputils.builder.ExtraPolylineBuilder
import com.github.bkhezry.extramaputils.model.ExtraMarker
import com.github.bkhezry.extramaputils.model.ExtraPolygon
import com.github.bkhezry.extramaputils.model.ExtraPolyline
import com.github.bkhezry.extramaputils.model.UiOptions
import com.google.android.gms.maps.model.LatLng
import com.veo.mobikemap.R

private val latLngs_1 = arrayOf(
    LatLng(35.751221, 51.348371),
    LatLng(35.749257, 51.371679),
    LatLng(35.740067, 51.370048),
    LatLng(35.740812, 51.346795)
)
private val latLngs_2 = arrayOf(
    LatLng(35.735607, 51.383739),
    LatLng(35.735842, 51.386496),
    LatLng(35.723379, 51.388689),
    LatLng(35.724067, 51.384462)
)
private val latLngs_3 = arrayOf(
    LatLng(35.70059, 51.37799),
    LatLng(35.70139, 51.4052),
    LatLng(35.69568, 51.40417),
    LatLng(35.6962, 51.39171),
    LatLng(35.68874, 51.39297),
    LatLng(35.6881, 51.39343),
    LatLng(35.6871, 51.40271),
    LatLng(35.67984, 51.40129)
)
private val latLngs_4 = arrayOf(
    LatLng(35.70026, 51.34731),
    LatLng(35.70239, 51.34707),
    LatLng(35.70403, 51.34773),
    LatLng(35.70675, 51.34782),
    LatLng(35.70685, 51.34101),
    LatLng(35.70643, 51.34019),
    LatLng(35.7066, 51.33965),
    LatLng(35.70629, 51.33763),
    LatLng(35.70206, 51.33782),
    LatLng(35.70179, 51.33928),
    LatLng(35.70132, 51.34008),
    LatLng(35.70054, 51.34067),
    LatLng(35.69935, 51.34089),
    LatLng(35.69829, 51.34018),
    LatLng(35.69772, 51.33879),
    LatLng(35.69768, 51.33715),
    LatLng(35.69787, 51.3358),
    LatLng(35.69845, 51.33512),
    LatLng(35.69954, 51.3345),
    LatLng(35.69958, 51.3258),
    LatLng(35.69944, 51.3159),
    LatLng(35.70612, 51.31573),
    LatLng(35.70615, 51.31872)
)
private val markerNames = arrayOf(
    "Marker_1",
    "Marker_2",
    "Marker_3",
    "Marker_4"
)

@IdRes
private val icons_1 = intArrayOf(
    R.drawable.ic_person_pin_circle_black_24dp,
    R.drawable.ic_explore_orange_a700_24dp,
    R.drawable.ic_hotel_indigo_a400_24dp,
    R.drawable.ic_store_yellow_a400_24dp
)

@IdRes
private val icons_2 = intArrayOf(
    R.drawable.ic_directions_bike_red_500_24dp,
    R.drawable.ic_directions_bike_red_500_24dp
)
fun getListExtraMarker(): List<ExtraMarker>? {
    val extraMarkers: MutableList<ExtraMarker> = ArrayList()
    for (i in 0 until markerNames.size) {
        extraMarkers.add(
            ExtraMarkerBuilder()
                .setName(markerNames.get(i))
                .setCenter(latLngs_1.get(i))
                .setIcon(icons_1.get(i))
                .build()
        )
    }
    return extraMarkers
}

fun getListMarker(): List<ExtraMarker>? {
    val extraMarkers: MutableList<ExtraMarker> = ArrayList()
    extraMarkers.add(
        ExtraMarkerBuilder()
            .setName("Start")
            .setCenter(latLngs_3.get(0))
            .setIcon(icons_2.get(0))
            .build()
    )
    extraMarkers.add(
        ExtraMarkerBuilder()
            .setName("End")
            .setCenter(latLngs_3.get(latLngs_3.size - 1))
            .setIcon(icons_2.get(1))
            .build()
    )
    return extraMarkers
}

fun getListExtraMarker_2(): List<ExtraMarker>? {
    val extraMarkers: MutableList<ExtraMarker> = ArrayList()
    for (i in 0 until markerNames.size) {
        extraMarkers.add(
            ExtraMarkerBuilder().setName(markerNames.get(i))
                .setCenter(latLngs_2.get(i))
                .setIcon(icons_1.get(i))
                .build()
        )
    }
    return extraMarkers
}

fun getPolygon_1(): ExtraPolygon? {
    return ExtraPolygonBuilder()
        .setPoints(latLngs_1)
        .setzIndex(0f)
        .setStrokeWidth(10)
        .setStrokeColor(Color.argb(100, 0, 0, 0))
        .setFillColor(Color.argb(100, 200, 200, 200))
        .build()
}

fun getPolygon_2(): ExtraPolygon? {
    return ExtraPolygonBuilder()
        .setPoints(latLngs_2)
        .setzIndex(0f)
        .setStrokeWidth(5)
        .setStrokeColor(Color.argb(100, 0, 0, 0))
        .setFillColor(Color.argb(100, 0, 100, 100))
        .build()
}

fun getPolyline_1(): ExtraPolyline? {
    return ExtraPolylineBuilder()
        .setPoints(latLngs_1)
        .setzIndex(0f)
        .setStrokeWidth(5)
        .setStrokeColor(Color.argb(100, 0, 0, 0))
        .build()
}

fun getPolyline_2(): ExtraPolyline? {
    return ExtraPolylineBuilder()
        .setPoints(latLngs_2)
        .setzIndex(0f)
        .setStrokeWidth(10)
        .setStrokeColor(Color.argb(100, 255, 0, 0))
        .build()
}

fun getPolyline_3(): ExtraPolyline? {
    return ExtraPolylineBuilder()
        .setPoints(latLngs_3)
        .setzIndex(0f)
        .setStrokeWidth(10)
        .setStrokeColor(Color.argb(100, 255, 255, 0))
        .build()
}

fun getPolyline_4(): ExtraPolyline? {
    return ExtraPolylineBuilder()
        .setPoints(latLngs_4)
        .setzIndex(0f)
        .setStrokeWidth(10)
        .setStrokeColor(Color.argb(100, 0, 255, 0))
        .setStrokePattern(UiOptions.StrokePatternDef.MIXED)
        .build()
}
