package com.example.threatawarenessmobile

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import java.io.ByteArrayInputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vibratePhone()
        val btnSafe : Button = findViewById(R.id.btnSafe)
        btnSafe.setOnClickListener {
            clickSafeButton()
        }

        val btnActivate : Button = findViewById(R.id.btnActivate)
        btnActivate.setOnClickListener {
            clickActivateButton()
        }
    }

    private fun clickSafeButton(){
        val circle : ImageView = findViewById(R.id.imageView)
        val textView : TextView = findViewById(R.id.textView)
        circle.setImageResource(R.drawable.green_circle)
        textView.text = "SAFE"
        vibratePhone()
    }

    private fun clickActivateButton(){
        dataService.getWarning().enqueue(object : Callback<WarningResponse> {
            override fun onResponse(
                call: Call<WarningResponse>,
                response: retrofit2.Response<WarningResponse>
            ) {
                if (response.isSuccessful) {
                    val dataResponse = response.body()
                    // update UI
                    val textView : TextView = findViewById(R.id.textView)
                    val circle : ImageView = findViewById(R.id.imageView)
                    val imageView: ImageView = findViewById(R.id.imageIntersection)

                    circle.setImageResource(R.drawable.green_circle)
                    textView.text = dataResponse?.text_data
                    Glide.with(this@MainActivity)
                        .load("https://threatawareness.pythonanywhere.com/image").into(imageView)

                    //Log.d("Image url: ",  "${dataResponse?.image_path}")
                    //Log.d("Text: ","${dataResponse.toString()}")
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<WarningResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
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