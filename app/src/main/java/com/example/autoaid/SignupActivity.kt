package com.example.autoaid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class SignupActivity: AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Create button variable for 'sign up button'
        val button:Button = findViewById(R.id.signUpBtn)
        // Create variable for 'Already a member' TextView
        val button2:TextView = findViewById(R.id.prompt)
        // Grab current state of authentication database
        val firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()


        // Set listener for 'sign up' button
        button.setOnClickListener()
        {
            // Grab the current text inside the username
            val user:EditText = findViewById(R.id.info1)
            // Grab the current text inside the password
            val pass:EditText = findViewById(R.id.info2)
            // Grab the current text inside the confirm password
            val confirmPass:EditText = findViewById(R.id.info3)

            // Make sure all fields are full
            if(user.text.toString().isBlank() || pass.text.toString().isBlank() || confirmPass.text.toString().isBlank()){
                Toast.makeText(this@SignupActivity,"Fill in all required fields",Toast.LENGTH_LONG).show()
            // Make sure the password and confirmed password match
            }else if(pass.text.toString() == confirmPass.text.toString()){
                // Attempt to create a new user with given information
                firebaseAuth.createUserWithEmailAndPassword(user.text.toString(),pass.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this@SignupActivity, "Account successfully created", Toast.LENGTH_LONG).show()
                        // Navigate to the login screen
                        startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                    }else{
                        Toast.makeText(this@SignupActivity,"Account creation failed. Make sure valid email address provided",Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this@SignupActivity,"Account creation failed. Make sure both passwords match!",Toast.LENGTH_LONG).show()
            }
        }

        // Set click listener for 'Already a member' button
        button2.setOnClickListener(){
            // Navigate back to login page
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
        }
    }
}