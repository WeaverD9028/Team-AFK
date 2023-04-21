package com.example.autoaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //start main activity after 2s
        Handler(Looper.getMainLooper()).postDelayed(2000){
            startActivity(Intent(this@Splash, SignupActivity::class.java))
        }
    }
}