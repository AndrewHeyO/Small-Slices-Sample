package com.example.andrew.slices

import android.content.BroadcastReceiver
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * Created by Andrew on 28.07.2018
 */

class MyBroadcastReceiver : BroadcastReceiver() {

    lateinit var prefManager: PrefManager

    companion object {
        const val ACTION_INC = "ACTION_INC"
        const val ACTION_DEC = "ACTION_DEC"
    }

    override fun onReceive(context: Context, intent: Intent) {
        prefManager = PrefManager(context)
        intent.action?.let {
            when (it) {
                ACTION_INC -> {
                    prefManager.saveInt(KEY_EXAMPLE_2, prefManager.getInt(KEY_EXAMPLE_2) + 1)
                    context.updateSlice("example2")
                }
                ACTION_DEC -> {
                    prefManager.saveInt(KEY_EXAMPLE_2, prefManager.getInt(KEY_EXAMPLE_2) - 1)
                    context.updateSlice("example2")
                }
            }
        }
    }
}