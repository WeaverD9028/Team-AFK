package com.example.autoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import android.widget.Button

class SavedReports : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_reports)


        // Create variables to access TextView for individual report
        val report1: TextView = findViewById(R.id.report1)
        val report2: TextView = findViewById(R.id.report2)
        val report3: TextView = findViewById(R.id.report3)
        val back: Button = findViewById(R.id.button4)

        // TextView Clickers
        report1.setOnClickListener(){
            showReport()
        }

        report2.setOnClickListener(){
            showReport()
        }

        report3.setOnClickListener(){
            showReport()
        }

        back.setOnClickListener(){
            startActivity(Intent(this@SavedReports,SymptomsActivity::class.java))
        }
    }


    // Opens the Diagnostic Report Page (for now)
    private fun showReport(){
        startActivity(Intent(this@SavedReports,Diagnostic::class.java))
    }
}