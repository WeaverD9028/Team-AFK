package com.app.dylanw_autoaid

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.app.dylanw_autoaid.MainActivity
import android.widget.Toast
import com.app.dylanw_autoaid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //https://www.figma.com/file/vu01H9TWjdx2su8TNu0eGI/AutoAid-Design?node-id=0%3A1
    /*
    Android Studio calls app pages Activities
    Every activity will have
        -an XML file - this is just the layout of where the elements will be
        - a java file - this is the logic, what should happen when buttons are pressed, text is entered, etc

     XML Files are stored in "res"  resources folder



     */
    //This is how we will interact with items that are on the screen
    // binding.*name of XML element* will give us access to that item
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)




        //In order to add items to a spinner we need an ArrayAdapter
        //-This takes text and shows it in some list. It basically maps data to an xml element
        //                                                               the layout to be used
        //Android has a simple built in layout for showing one string on a line and it's under android.R.id.simple
        val adpIssues = ArrayAdapter(this, R.layout.simple_list_item_1, allIssues)
        //Now that we have something that can take Strings and show them in a list we need to set this adapter
        //as the adapter for our spinner
        binding!!.spnIssues.adapter = adpIssues

        //All XML elements can listen for when they are clicked so let's setup the onlick listeners
        binding!!.btnIssuesContinue.setOnClickListener { showMessage("Clicked Continue") }
        binding!!.btnIssuesBack.setOnClickListener { showMessage("Clicked Back") }
        binding!!.txtMoreIssues.setOnClickListener { showMessage("Go to more issues page") }


    }

    private fun showMessage(msg: String) {
        //Android Studio has something called a Toast and that will pop up a short message on the bottom of the screen
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        //In order to use the spinner we need a list of options to display in the drop down
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
}