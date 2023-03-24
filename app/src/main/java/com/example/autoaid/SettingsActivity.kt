package com.example.autoaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class SettingsActivity : AppCompatActivity() {

    //var for notifications and location switch
    lateinit var notiSwitch: Switch
    lateinit var notiText: TextView

    lateinit var locationSwitch: Switch
    lateinit var locationText: TextView
    lateinit var  toggle : ActionBarDrawerToggle

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
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView : NavigationView = findViewById(R.id.nav_view)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.nav_home -> Toast.makeText(applicationContext, "Click home", Toast.LENGTH_SHORT).show()
                R.id.nav_videos -> startActivity(Intent(this@SettingsActivity,YouTubeActivity::class.java))
                R.id.nav_local -> startActivity(Intent(this@SettingsActivity,GoogleMapsActivity::class.java))
                R.id.nav_share -> Toast.makeText(applicationContext, "Click share", Toast.LENGTH_SHORT).show()
                R.id.nav_review -> Toast.makeText(applicationContext, "Click review", Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> startActivity(Intent(this@SettingsActivity,SettingsActivity::class.java))
                R.id.nav_login -> Toast.makeText(applicationContext, "Click login", Toast.LENGTH_SHORT).show()
            }
            true
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){

            return true
        }
        return super.onOptionsItemSelected(item)

    }

}