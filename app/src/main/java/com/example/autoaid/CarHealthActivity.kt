package com.example.autoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class CarHealthActivity : AppCompatActivity() {

    //private lateinit var viewdate : TextView
    private lateinit var oilDate : TextView
    private lateinit var coolantDate : TextView
    private lateinit var rotateDate : TextView
    private lateinit var pressureDate : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_health)

        //finding values for dates
       // viewdate = findViewById(R.id.textViewdate)
        oilDate = findViewById(R.id.txtdate1)
        coolantDate = findViewById(R.id.txtdate2)
        rotateDate = findViewById(R.id.txtdate3)
        pressureDate = findViewById(R.id.txtdate4)

        val oilcheck = intent.getStringExtra("date1")
        val coolantcheck = intent.getStringExtra("date2")
        val rotatecheck = intent.getStringExtra("date3")
        val pressurecheck = intent.getStringExtra("date4")

        oilDate.text = oilcheck
        coolantDate.text = coolantcheck
        rotateDate.text = rotatecheck
        pressureDate.text = pressurecheck

        //create list
        //val maintenanceList = listOf(oilDate,coolantDate,rotateDate,pressureDate)

        //val listSize = maintenanceList.size

        //for (i in maintenanceList){
          //  println(i)
        //}

    }
}