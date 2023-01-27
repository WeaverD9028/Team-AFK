package com.example.autoaid


import android.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.dylanw_autoaid.ActivitySymptomsBinding
import com.example.autoaid.databinding.ActivityLoginBinding.inflate
import com.example.autoaid.databinding.ActivityMainBinding.inflate
import com.example.autoaid.databinding.ActivitySymptomsBinding.inflate


class SymptomsActivity: AppCompatActivity() {



        private lateinit var binding: ActivitySymptomsBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            //setContentView(R.layout.activity_symptoms)
            //binding?.let { setContentView(it.root) }





            val adpIssues = ArrayAdapter(this, R.layout.simple_list_item_1, allIssues)
            //  set this adapter
            //as the adapter for the spinner
            //binding.spnIssues.adapter = adpIssues

            //Onclick Listener for when buttons are pressed
            binding.btnIssuesContinue.setOnClickListener { showMessage("Clicked Continue") }
            binding.btnIssuesBack.setOnClickListener { showMessage("Clicked Back") }
            binding.txtMoreIssues.setOnClickListener { showMessage("Go to more issues page") }


        }

        private fun showMessage(msg: String) {
            // pop up a short message on the bottom of the screen
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        companion object {
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
    }

fun Any.setOnClickListener(function: () -> Unit) {

}


import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class SymptomsActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptoms)
    }
}
