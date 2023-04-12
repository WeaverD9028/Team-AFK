package com.example.autoaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Button

class Loading : AppCompatActivity() {

    // Declaring and initializing elements from layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val loadingText = findViewById<TextView>(R.id.loadingBlurb)
        val continueButton = findViewById<Button>(R.id.continueButton)

        loadingText.text = "Creating your diagnosis..."
        continueButton.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            continueButton.isEnabled = true //enable button

            loadingText.text = "Your diagnosis is complete!"
            }, 5000) //5 second delay

        //click button moves on to Diagnosis page
        continueButton.setOnClickListener() {
            val intent = Intent(this,Diagnostic::class.java)
            startActivity(intent)
        }
    }
}



