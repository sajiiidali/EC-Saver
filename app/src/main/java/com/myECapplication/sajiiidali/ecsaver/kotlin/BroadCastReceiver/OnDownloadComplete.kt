package com.myECapplication.sajiiidali.ecsaver.kotlin.BroadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri


class OnDownloadComplete : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(
                context,
                notification
            )
            r.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}