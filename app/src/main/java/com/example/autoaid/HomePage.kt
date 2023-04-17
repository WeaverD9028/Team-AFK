package com.example.autoaid



import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val report: Button = findViewById(R.id.btnReport)
        report.setOnClickListener{
            startActivity(Intent(this@HomePage,Diagnostic::class.java))
        }

        val sound: Button  = findViewById(R.id.btnsound)
        sound.setOnClickListener() {
            val music: MediaPlayer = MediaPlayer.create(this@HomePage, R.raw.music)
                music.isLooping = true
                music.start()
        }

    }
}