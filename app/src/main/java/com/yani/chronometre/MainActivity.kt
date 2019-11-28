package com.yani.chronometre

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val receiver = ButtonBroadcastReceiver(this)
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_MEDIA_BUTTON)
        filter.addAction("android.media.VOLUME_CHANGED_ACTION")
        filter.priority = IntentFilter.SYSTEM_HIGH_PRIORITY + 1
        registerReceiver(receiver, filter)
    }
}
