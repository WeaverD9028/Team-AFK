package com.example.autoaid

import android.annotation.SuppressLint
import android.app.Notification.Action
import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_menu.*
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MenuActivity : AppCompatActivity() {

    private lateinit var  toggle : ActionBarDrawerToggle

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        button2.setOnClickListener {
            Intent(this, SymptomsActivity::class.java).also {
                startActivity(it)

            }
        }


        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.nav_home -> Toast.makeText(applicationContext, "Click home", Toast.LENGTH_SHORT).show()
                R.id.nav_view -> Toast.makeText(applicationContext, "Click view", Toast.LENGTH_SHORT).show()
                R.id.nav_share -> Toast.makeText(applicationContext, "Click share", Toast.LENGTH_SHORT).show()
                R.id.nav_review -> Toast.makeText(applicationContext, "Click review", Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> Toast.makeText(applicationContext, "Click setting", Toast.LENGTH_SHORT).show()
                R.id.nav_login -> Toast.makeText(applicationContext, "Click login", Toast.LENGTH_SHORT).show()
                R.id.nav_videos -> Toast.makeText(applicationContext, "Loading DIY Videos", Toast.LENGTH_SHORT).show()
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