package com.example.threatawarenessmobile

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vibratePhone()
        val btnSafe : Button = findViewById(R.id.btnSafe)
        btnSafe.setOnClickListener {
            clickSafeButton()
        }
    }

    private fun clickSafeButton(){
        val circle : ImageView = findViewById(R.id.imageView)
        val textView : TextView = findViewById(R.id.textView)
        circle.setImageResource(R.drawable.green_circle)
        textView.text = "SAFE"
        vibratePhone()
    }

    private fun vibratePhone() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            // Deprecated in API 26
            vibrator.vibrate(500)
        }
    }
}