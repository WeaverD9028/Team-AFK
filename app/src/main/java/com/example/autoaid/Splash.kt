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

        //for hiding all bars
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())

        //start main activity after 3s
        Handler(Looper.getMainLooper()).postDelayed(3000){
            startActivity(Intent(this@Splash, MainActivity::class.java))
        }

    }
}