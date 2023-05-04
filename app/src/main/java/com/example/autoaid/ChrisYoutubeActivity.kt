package com.example.autoaid
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ChrisYoutubeActivity : AppCompatActivity() {

    lateinit var  toggle : ActionBarDrawerToggle
    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_tube)

        val webViewBuild = findViewById<WebView>(R.id.web_view)
        val vin = intent.getStringExtra("Vin")
        val description = intent.getStringExtra("Description")
        val make = intent.getStringExtra("Make")
        val model = intent.getStringExtra("Model")
        val year = intent.getStringExtra("Year")

        println("$vin $description")

        webViewBuild.webViewClient = WebViewClient()
        webViewBuild.apply {
            loadUrl("https://www.youtube.com/@chrisfix")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
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
                R.id.nav_view -> Toast.makeText(applicationContext, "Click view", Toast.LENGTH_SHORT).show()
                R.id.nav_videos -> startActivity(Intent(this@ChrisYoutubeActivity,ChrisYoutubeActivity::class.java))
                R.id.nav_local -> startActivity(Intent(this@ChrisYoutubeActivity,GoogleMapsActivity::class.java))
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
