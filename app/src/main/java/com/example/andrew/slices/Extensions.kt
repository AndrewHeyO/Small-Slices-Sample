package com.example.andrew.slices

import android.content.ContentResolver
import android.content.Context
import android.net.Uri

/**
 * Created by Andrew on 28.07.2018
 */

fun Context.updateSlice(path: String) {
    Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT)
            .authority(packageName)
            .appendPath(path)
            .build()
            .apply {
                contentResolver.notifyChange(this, null)
            }
}