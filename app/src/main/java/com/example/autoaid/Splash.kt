package com.example.autoaid

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed


class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // hides status and navigational bar
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        // start main activity after 2s
        Handler(Looper.getMainLooper()).postDelayed(2500){
            startActivity(Intent(this@Splash, LoginActivity::class.java))

            // kill activity aka disable ability to return to splash
            finish()
        }

    }
}