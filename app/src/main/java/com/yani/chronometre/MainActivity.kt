package com.yani.chronometre

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.media.session.MediaSession
import android.media.session.PlaybackState
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val receiver = ButtonBroadcastReceiver(this)
    private val filter = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        filter.addAction(Intent.ACTION_MEDIA_BUTTON)
        filter.addAction("android.media.VOLUME_CHANGED_ACTION")
        filter.priority = IntentFilter.SYSTEM_HIGH_PRIORITY + 1
        startChronometer.setOnClickListener{
            val intent = Intent("media_action")
            sendBroadcast(intent)
            filter.addAction("media_action")
            registerReceiver(receiver, filter)
        }
    }

    override fun onStop() {
        super.onStop()
        // In the onStop method you should unregister the receiver
        unregisterReceiver(receiver)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val intent = Intent("media_action").apply { putExtra("KEY_CODE", keyCode) }
        sendBroadcast(intent)
        filter.addAction("media_action")
        registerReceiver(receiver, filter)
        return super.onKeyDown(keyCode, event)
    }

}
