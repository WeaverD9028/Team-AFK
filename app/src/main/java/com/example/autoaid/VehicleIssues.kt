package com.example.autoaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class VehicleIssues : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_issues)


        val back1 = findViewById<Button>(R.id.button3)
        back1.setOnClickListener() {
            val Intent = Intent(this,SymptomsActivity::class.java)
            startActivity(Intent)
        }
    }
}





