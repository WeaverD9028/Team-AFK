package com.example.autoaid


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.Intent


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.startBtn)
        // When button is clicked -> launch the login page
        button.setOnClickListener()
        {
            Toast.makeText(this@MainActivity,"Going to login page",Toast.LENGTH_LONG).show()

        // Take to the login page
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
    }

}