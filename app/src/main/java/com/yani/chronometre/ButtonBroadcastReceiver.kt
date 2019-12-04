package com.yani.chronometre

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import kotlinx.android.synthetic.main.activity_main.*

class ButtonBroadcastReceiver(mainActivity: MainActivity) : BroadcastReceiver() {
    private val activity = mainActivity
    // init countdown object
    private var countdown: CountDownTimer? = null
    // init tone generator => beep sound
    private val toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
    // init vibrator variable
    private lateinit var vibrator: Vibrator

    override fun onReceive(context: Context?, intent: Intent?) {
        // init vibrator variable
        vibrator = context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        activity.textChronometer.text = context.getString(R.string.init_chronometer) // init 1 minute
        if(countdown != null) countdown!!.cancel()
        initCountdown()
        countdown!!.start()
    }


    private fun initCountdown(){
        countdown = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                activity.textChronometer.text = "00:" + millisUntilFinished / 1000
            }

            override fun onFinish() {
                activity.textChronometer.text = "done!"
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_P, 1000)
                vibrator.vibrate(VibrationEffect.createOneShot(2000, 255)) // 255 = Max amplitude
            }
        }
    }
}