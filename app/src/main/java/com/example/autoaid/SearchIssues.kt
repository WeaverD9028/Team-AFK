package com.app.dylanw_autoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.dylanw_autoaid.MainActivity
import com.app.dylanw_autoaid.databinding.ActivitySearchIssuesBinding
import java.util.ArrayList

//import allIssues array from the Main Activity page
class SearchIssues : AppCompatActivity() {
    var binding: ActivitySearchIssuesBinding? = null

    //We are going to show a list of issues, and we want to be able to search that list
    //To pull this off we actually need to keep 2 different lists
    //  - ALL POSSIBLE ISSUES
    //  - THE FILTERED RESULTS (when nothing is searched this is all issues)
    //allIssues is defined in the Main Activity
    //use an array list to show the filtered data so that we can clear and add as necessary
    var filteredList: ArrayList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchIssuesBinding.inflate(
            layoutInflater
        )
        val view: View = binding!!.root
        setContentView(view)

        //the filtered list needs to be initialized
        filteredList = ArrayList()
    }

    private fun filterIssues(searchText: String) {
        //clear the filtered list
        filteredList!!.clear()

        //search: EE
        // none
        // steering wheel
        // wheel problem
        //

        //go through all of the issues we have and
        for (i in MainActivity.allIssues.indices) {
            val currentIssue = MainActivity.allIssues[i]
            if (currentIssue.contains(searchText)) {
                //add to the filtered list
                filteredList!!.add(currentIssue)
            }
        }

        //TODO notify datasetchanged
    }
}