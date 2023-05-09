package com.example.autoaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.TextView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hides navigation bar
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        // Create variable for login button
        val button: Button = findViewById(R.id.loginBtn)
        // Create variable for 'not a member' button
        val button2:TextView = findViewById(R.id.notMember)
        // Grab current state of authentication database
        val firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()


        // Set on click listener for login button
        button.setOnClickListener()
        {
            // Grab the current text inside the username
            val user: EditText = findViewById(R.id.username)
            // Grab the current text inside the password
            val pass: EditText = findViewById(R.id.password)

            // Make sure all fields are full
            if((user.text.toString().isBlank()) || (pass.text.toString().isBlank())){
                Toast.makeText(this@LoginActivity,"Fill in all required fields",Toast.LENGTH_LONG).show()
            }else{
                // Attempt to sign in with the given credentials
                firebaseAuth.signInWithEmailAndPassword(user.text.toString(),pass.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this@LoginActivity,"Login successful",Toast.LENGTH_LONG).show()
                        // Navigate to the next activity
                        startActivity(Intent(this@LoginActivity,HomePage::class.java))
                    }else{
                        Toast.makeText(this@LoginActivity,"Incorrect username and password. Try again",Toast.LENGTH_LONG).show()
                    }
                }

            }
        }

        // Set on click listener for 'not a member button'
        button2.setOnClickListener{
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        }


    }






}