package com.example.autoaid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlinx.android.synthetic.main.activity_diagnostic.*

class VinActivity : AppCompatActivity() {
    // creating variables for our edittext, button and dbhandler
    private lateinit var carVinEdt : EditText
    private lateinit var addCarBtn : Button
    private lateinit var readCarbtn : Button
    private lateinit var dbHandler: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vin)

        //hides navigation bar
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        // initializing all our variables.
        carVinEdt = findViewById(R.id.idEdtVin)
        addCarBtn = findViewById(R.id.idBtnAddCar)
        readCarbtn = findViewById(R.id.idBtnReadCars)

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = DBHandler(this@VinActivity)

        // below line is to add on click listener for our add course button.
        addCarBtn.setOnClickListener(View.OnClickListener { // below line is to get data from all edit text fields.
            val carVin  = carVinEdt.getText().toString()


            // validating if the text fields are empty or not.
            if (carVin.isEmpty()) {
                Toast.makeText(this@VinActivity, "Please enter a valid VIN.", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }

            // on below line we are calling a method to add new
            // course to sqlite data and pass all our values to it.
            dbHandler!!.addNewVin(carVin)

            // after adding the data we are displaying a toast message.
            Toast.makeText(this@VinActivity , "Your car has been added!", Toast.LENGTH_SHORT).show()
            carVinEdt.setText("")
        })

        readCarbtn.setOnClickListener { // opening a new activity via a intent.
            val i = Intent(this@VinActivity, ViewVins::class.java)
            startActivity(i)
        }
    }
}