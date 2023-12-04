package com.veo.common.extension

import android.graphics.Bitmap
import android.graphics.Matrix

fun Bitmap.resetSize(newWidth: Float, newHeight: Float): Bitmap {
    // 计算缩放比例
    val scaleWidth = newWidth / width
    val scaleHeight = newHeight / height
    // 取得想要缩放的matrix参数
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    // 得到新的图片
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

/**
 * 获取Bitmap RGB array
 */
fun Bitmap.toPixelsArray(): IntArray {
    val mWidth: Int = width
    val mHeight: Int = height
    val mIntArray = IntArray(mWidth * mHeight)
    // Copy pixel data from the Bitmap into the 'intArray' array
    getPixels(mIntArray, 0, mWidth, 0, 0, mWidth, mHeight)
    return mIntArray
}