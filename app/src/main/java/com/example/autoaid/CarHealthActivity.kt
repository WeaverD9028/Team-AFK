package com.example.autoaid

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.widget.TextView

class CarHealthActivity : AppCompatActivity() {
    lateinit var  toggle : ActionBarDrawerToggle

    //private lateinit var viewdate : TextView
    private lateinit var oilDate : TextView
    private lateinit var coolantDate : TextView
    private lateinit var rotateDate : TextView
    private lateinit var pressureDate : TextView

    @SuppressLint("MissingInflatedId")
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
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView : NavigationView = findViewById(R.id.nav_view)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.nav_home -> Toast.makeText(applicationContext, "Click home", Toast.LENGTH_SHORT).show()
                R.id.nav_view -> Toast.makeText(applicationContext, "Click view", Toast.LENGTH_SHORT).show()
                R.id.nav_videos -> startActivity(Intent(this@CarHealthActivity,YouTubeActivity::class.java))
                R.id.nav_local -> startActivity(Intent(this@CarHealthActivity,GoogleMapsActivity::class.java))
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