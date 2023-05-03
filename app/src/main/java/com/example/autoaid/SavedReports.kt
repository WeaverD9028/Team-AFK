package com.example.autoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import android.widget.Button
import android.widget.ImageButton

class SavedReports : AppCompatActivity() {

    private lateinit var carmake : TextView
    private lateinit var carmodel : TextView
    private lateinit var caryear : TextView
    private lateinit var carvin : TextView
    private lateinit var carcode : TextView
    private lateinit var carsym : TextView
    private lateinit var carcost : TextView
    private lateinit var  btndiy : ImageButton
    private lateinit var btnlocation : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_reports)

        val make = intent.getStringExtra("Make")
        val model = intent.getStringExtra("Model")
        val year = intent.getStringExtra("Year")
        val vin = intent.getStringExtra("Vin")
        val code = intent.getStringExtra("Code")
        val sym = intent.getStringExtra("Description")
        val cost = intent.getStringExtra("Cost")
        btndiy = findViewById(R.id.vid)
        btnlocation = findViewById(R.id.location)

        carmake = findViewById(R.id.carMakeTV)
        carmodel = findViewById(R.id.carModelTV)
        caryear = findViewById(R.id.carYearTV)
        carvin = findViewById(R.id.carVinTV)
        carcode = findViewById(R.id.carCodeTV)
        carsym = findViewById(R.id.descriptionTV)
        carcost = findViewById(R.id.priceTV)

        carmake.setText(make)
        carmodel.setText(model)
        caryear.setText(year)
        carvin.setText(vin)
        carcode.setText(code)
        carsym.setText(sym)
        carcost.setText(cost)

        btnlocation.setOnClickListener {
            val i = Intent(this@SavedReports, GoogleMapsActivity::class.java)
            startActivity(i)
        }


    }

}