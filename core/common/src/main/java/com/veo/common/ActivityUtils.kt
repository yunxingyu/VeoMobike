package com.veo.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

object ActivityUtils {
    fun getActivityByContext(context: Context): Activity? {
        var context = context
        if (context is Activity) return context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }
}