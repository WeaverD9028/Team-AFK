package com.example.autoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class CarHealthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_health)

        //finding values for dates
        val oilDate = findViewById<EditText>(R.id.oilDateText)
        val coolantDate = findViewById<EditText>(R.id.coolantDateText)
        val rotateDate = findViewById<EditText>(R.id.rotationDateText)
        val pressureDate = findViewById<EditText>(R.id.pressureDateText)

        //create list
        val maintenanceList = listOf(oilDate,coolantDate,rotateDate,pressureDate)

        val listSize = maintenanceList.size

        //for (i in maintenanceList){
          //  println(i)
        //}

    }
}