package com.example.autoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView

class SettingsActivity : AppCompatActivity() {

    //var for notifications and location switch
    lateinit var notiSwitch: Switch
    lateinit var notiText: TextView

    lateinit var locationSwitch: Switch
    lateinit var locationText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //set var for notification and location switch
        notiSwitch = findViewById(R.id.notiToggle)
        notiText = findViewById(R.id.notiBlurb)

        locationSwitch = findViewById(R.id.locationToggle)
        locationText = findViewById(R.id.locationBlurb)

        //checking status of switches individually
        if (notiSwitch.isChecked){ //toglged on
            notiText.text = "Receive reminders for regular car maintenance. (ON)"

        } else { //toggled off
            notiText.text = "Receive reminders for regular car maintenance. (OFF)"
        }

        if (locationSwitch.isChecked){ //toglged on
            locationText.text = "Receive reminders for regular car maintenance. (ON)"

        } else { //toggled off
            locationText.text = "Receive reminders for regular car maintenance. (OFF)"
        }

        //adding check change listener for switch
        notiSwitch.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked){
                notiText.text = "Use your location to find autoshops near you. (ON)"
            } else {
                notiText.text = "Use your location to find autoshops near you. (OFF)"
            }
        }

        locationSwitch.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked){
                locationText.text = "Use your location to find autoshops near you. (ON)"
            } else {
                locationText.text = "Use your location to find autoshops near you. (OFF)"
            }
        }

    }
}