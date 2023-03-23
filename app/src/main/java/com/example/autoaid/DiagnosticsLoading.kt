package com.example.autoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.content.Intent


class DiagnosticsLoading : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnostics_loading)

        val timer = object: CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                startActivity(Intent(this@DiagnosticsLoading,Diagnostic::class.java))
            }
        }
        timer.start()

    }
}