package com.example.autoaid

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_menu.*
import android.view.MenuItem


class HomePage : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        //hides navigation bar
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        // go to diagnostics page
        val report: Button = findViewById(R.id.btnReport)
        report.setOnClickListener{
            startActivity(Intent(this@HomePage,VinActivity::class.java))
        }


        //menu code
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.nav_home -> startActivity(Intent(this@HomePage,HomePage::class.java))
                R.id.nav_view -> Toast.makeText(applicationContext, "Click view", Toast.LENGTH_SHORT).show()
                R.id.nav_videos -> startActivity(Intent(this@HomePage,ChrisYoutubeActivity::class.java))
                R.id.nav_local -> startActivity(Intent(this@HomePage,GoogleMapsActivity::class.java))
                R.id.nav_glossary -> startActivity(Intent(this@HomePage,Glossary::class.java))
                R.id.nav_login -> startActivity(Intent(this@HomePage,LoginActivity::class.java))
                R.id.nav_health -> startActivity(Intent(this@HomePage,CarHealthActivity::class.java))
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