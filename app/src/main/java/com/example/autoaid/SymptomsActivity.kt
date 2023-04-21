package com.example.autoaid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_symptoms.*

class SymptomsActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptoms)



        // Create button variables linked to xml
        val cont: Button = findViewById(R.id.btnCont)
        val back: Button = findViewById(R.id.btnBack)
        val more = findViewById<TextView>(R.id.btnUnfold)



        val spinner: Spinner = findViewById(R.id.spinner2)
        ArrayAdapter.createFromResource(
            this,
            R.array.Symptoms,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }


        /*cont.setOnClickListener{
          showMessage("Clicked continue")
        }*/

        /*back.setOnClickListener{
            showMessage("Clicked back")
        }*/



    cont.setOnClickListener(){
        startActivity(Intent(this@SymptomsActivity,VehicleIssues::class.java))
    }
        back.setOnClickListener(){
            startActivity(Intent(this@SymptomsActivity,MenuActivity::class.java))
        }
    more.setOnClickListener(){
        startActivity(Intent(this@SymptomsActivity,SearchIssues::class.java))
    }

    button4.setOnClickListener(){
        startActivity(Intent(this@SymptomsActivity,SavedReports::class.java))
    }

    }
    private fun showMessage(msg: String) {
        // pop up a short message on the bottom of the screen
        Toast.makeText(this@SymptomsActivity, msg, Toast.LENGTH_SHORT).show()
    }

    /*companion object {
        //In order to use the spinner we need a list of options to display in the drop down
        val allIssues = arrayOf("NONE", "FLAT TIRES", "SQUEAKY OR GRINDY BREAKS", "CAR WILL NOT START", "SHAKY STEERING WHEEL",
            "NOISY ENGINE", "A/C ISSUES", "WARNING LIGHT(S)")
    }*/

}

