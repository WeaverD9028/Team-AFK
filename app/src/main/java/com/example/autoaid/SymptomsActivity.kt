package com.example.autoaid

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class SymptomsActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptoms)

        // Create button variables linked to xml
        val cont: Button = findViewById(R.id.btnCont)
        val back: Button = findViewById(R.id.btnBack)

        // Create companion object for spinner
        object {
            //List of options for the spinner
            val allIssues = arrayOf(
                "NONE",
                "FLAT TIRES",
                "SQUEAKY OR GRINDY BREAKS",
                "CAR WILL NOT START",
                "SHAKY STEERING WHEEL",
                "NOISY ENGINE",
                "A/C ISSUES",
                "WARNING LIGHT(S)"
            )
        }

        cont.setOnClickListener{
            showMessage("Clicked continue")
            // Navigate to Diagnostics page
            startActivity(Intent(this@SymptomsActivity, Diagnostic::class.java))

        }

        back.setOnClickListener{
            showMessage("Clicked back")
            // Navigate back to menu page
            startActivity(Intent(this@SymptomsActivity,MenuActivity::class.java))
        }



        }
    private fun showMessage(msg: String) {
        // pop up a short message on the bottom of the screen
        Toast.makeText(this@SymptomsActivity, msg, Toast.LENGTH_SHORT).show()
    }

}

