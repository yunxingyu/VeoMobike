package com.veo.mobikemap.utils


import android.R
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
import com.google.android.gms.maps.model.LatLngBounds


fun List<LatLng>.getCenterOfPolygon(): LatLngBounds {
    val centerBuilder: LatLngBounds.Builder = LatLngBounds.builder()
    forEach { centerBuilder.include(LatLng(it.latitude, it.longitude)) }
    return centerBuilder.build()
}

private data class CameraViewCoord(
    val yMax: Double,
    val yMin: Double,
    val xMax: Double,
    val xMin: Double
)

fun List<LatLng>.calculateCameraViewPoints(pctView: Double = .25): List<LatLng> {
    val coordMax = findMaxMins()
    val dy = coordMax.yMax - coordMax.yMin
    val dx = coordMax.xMax - coordMax.xMin
    val yT = (dy * pctView) + coordMax.yMax
    val yB = coordMax.yMin - (dy * pctView)
    val xR = (dx * pctView) + coordMax.xMax
    val xL = coordMax.xMin - (dx * pctView)
    return listOf(
        LatLng(coordMax.xMax, yT),
        LatLng(coordMax.xMin, yB),
        LatLng(xR, coordMax.yMax),
        LatLng(xL, coordMax.yMin)
    )
}

private fun List<LatLng>.findMaxMins(): CameraViewCoord {
    check(size > 0) { "Cannot calculate the view coordinates of nothing." }
    var viewCoord: CameraViewCoord? = null
    for(point in this) {
        viewCoord = CameraViewCoord(
            yMax = viewCoord?.yMax?.let { yMax ->
                if (point.longitude > yMax) {
                    point.longitude
                } else {
                    yMax
                }
            } ?: point.longitude,
            yMin = viewCoord?.yMin?.let { yMin->
                if (point.longitude < yMin) {
                    point.longitude
                } else {
                    yMin
                }
            } ?: point.longitude,
            xMax = viewCoord?.xMax?.let { xMax->
                if (point.latitude > xMax) {
                    point.latitude
                } else {
                    xMax
                }
            } ?: point.latitude,
            xMin = viewCoord?.xMin?.let { xMin->
                if (point.latitude < xMin) {
                    point.latitude
                } else {
                    xMin
                }
            } ?: point.latitude,
        )
    }
    return viewCoord ?: throw IllegalStateException("viewCoord cannot be null.")
}
