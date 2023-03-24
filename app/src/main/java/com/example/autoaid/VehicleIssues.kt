package com.example.autoaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText


class VehicleIssues : AppCompatActivity() {

    private lateinit var btnGetInfo : Button
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_issues)

        editText1 = findViewById(R.id.editTextDate)
        editText2 = findViewById(R.id.editTextDate2)
        editText3 = findViewById(R.id.editTextDate3)
        editText4 = findViewById(R.id.editTextDate4)

        val btnGetInfo = findViewById<Button>(R.id.button5)
        btnGetInfo.setOnClickListener(){
            startActivity(Intent(this@VehicleIssues,CarHealthActivity::class.java).
            putExtra("date1",editText1.text.toString())
                .putExtra("date2",editText2.text.toString())
                .putExtra("date3",editText3.text.toString())
                .putExtra("date4",editText4.text.toString()))

        }

        val cont1 = findViewById<Button>(R.id.button)
        cont1.setOnClickListener(){
            startActivity(Intent(this@VehicleIssues,DiagnosticsLoading::class.java))
        }

        val back1 = findViewById<Button>(R.id.button3)
        back1.setOnClickListener(){
            startActivity(Intent(this@VehicleIssues,SymptomsActivity::class.java))
        }
        }
    }






