package com.example.andrew.slices

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.*
import androidx.slice.builders.ListBuilder.LARGE_IMAGE
import androidx.slice.builders.ListBuilder.SMALL_IMAGE

/**
 * Created by Andrew on 28.07.2018
 */

class MySliceProvider : SliceProvider() {

    private lateinit var prefManager: PrefManager

    override fun onCreateSliceProvider(): Boolean {
        prefManager = PrefManager(context)
        return true
    }

    override fun onBindSlice(sliceUri: Uri?): Slice? =
            sliceUri?.path?.let {
                return when (it) {
                    "/example1" -> createSliceForExample1(sliceUri)
                    "/example2" -> createSliceForExample2(sliceUri)
                    "/example3" -> createSliceForExample3(sliceUri)
                    else -> null
                }
            }

    private fun createSliceForExample1(sliceUri: Uri): Slice =
            ListBuilder(context, sliceUri)
                    .addRow {
                        with(it) {
                            title = prefManager.getString(KEY_EXAMPLE_1)
                            subtitle = "Subtitle"
                            primaryAction = createOpenAppAction(sliceUri)
                        }
                    }.build()

    private fun createOpenAppAction(sliceUri: Uri): SliceAction {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, sliceUri.hashCode(), intent, 0)
        return SliceAction(pendingIntent,
                IconCompat.createWithResource(context, R.drawable.ic_assignment_ind).toIcon(),
                "Slice to App")
    }

    private fun createSliceForExample2(sliceUri: Uri): Slice =
            ListBuilder(context, sliceUri)
                    .addRow {
                        with(it) {
                            title = prefManager.getInt(KEY_EXAMPLE_2).toString()
                            addEndItem(SliceAction(createUpdateIntent(MyBroadcastReceiver.ACTION_INC),
                                    IconCompat.createWithResource(context, R.drawable.ic_inc).toIcon(),
                                    "Slice 2 - inc"))
                            addEndItem(SliceAction(createUpdateIntent(MyBroadcastReceiver.ACTION_DEC),
                                    IconCompat.createWithResource(context, R.drawable.ic_dec).toIcon(),
                                    "Slice 2 - dec"))
                            primaryAction = createOpenAppAction(sliceUri)
                        }
                    }.build()

    private fun createUpdateIntent(action: String): PendingIntent =
            Intent(action)
                    .apply { setClass(context, MyBroadcastReceiver::class.java) }
                    .let {
                        PendingIntent.getBroadcast(context, 0, it,
                                PendingIntent.FLAG_UPDATE_CURRENT)
                    }

    private fun createSliceForExample3(sliceUri: Uri): Slice =
            list(context, sliceUri, ListBuilder.INFINITY) {
                header {
                    title = "Social Networks"
                    primaryAction = createOpenAppAction(sliceUri)
                }
                gridRow {
                    cell {
                        addImage(IconCompat.createWithResource(context, R.drawable.logo_facebook), SMALL_IMAGE)
                        addTitleText("Facebook")
                        addText("fb")
                    }
                    cell {
                        addImage(IconCompat.createWithResource(context, R.drawable.logo_vk), SMALL_IMAGE)
                        addTitleText("VKontakte")
                        addText("vk")
                    }
                    cell {
                        addImage(IconCompat.createWithResource(context, R.drawable.logo_instagram), SMALL_IMAGE)
                        addTitleText("Instagram")
                        addText("ig")
                    }
                    cell {
                        addImage(IconCompat.createWithResource(context, R.drawable.logo_twitter), SMALL_IMAGE)
                        addTitleText("Twitter")
                        addText("tw")
                    }
                }
            }
}