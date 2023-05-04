package com.example.autoaid

import android.app.Notification.Action
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_menu.*
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.example.autoaid.LoginActivity


class MenuActivity : AppCompatActivity() {

    lateinit var  toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl

            // Check if user's email is verified
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }

        button2.setOnClickListener {
            // Navigate to Symptoms page
            startActivity(Intent(this@MenuActivity,SymptomsActivity::class.java))
        }

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.nav_home -> startActivity(Intent(this@MenuActivity,HomePage::class.java))
                R.id.nav_view -> Toast.makeText(applicationContext, "Click view", Toast.LENGTH_SHORT).show()
                R.id.nav_videos -> startActivity(Intent(this@MenuActivity,YouTubeActivity::class.java))
                R.id.nav_local -> startActivity(Intent(this@MenuActivity,GoogleMapsActivity::class.java))
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