package com.example.autoaid

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.autoaid.SymptomsActivity.Companion.allIssues
import com.example.autoaid.databinding.ActivitySearchIssuesBinding

class SearchIssues : AppCompatActivity() {
    var binding: ActivitySearchIssuesBinding? = null
    var filteredList: ArrayList<String>? = null
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_issues)
        val searchView: SearchView = findViewById(R.id.searchid);
        //val listView: ListView = findViewById(R.id.listView)var adapter: ArrayAdapter<*>



        val new = findViewById<Button>(R.id.issuecont)
        new.setOnClickListener() {
            val Intent = Intent(this,VehicleIssues::class.java)
            startActivity(Intent)
        }
        filteredList = ArrayList()
    }


    private fun filterIssues(searchText: String) {
        //clear the filtered list
        filteredList?.clear()

        //search: EE
        // none
        // steering wheel
        // wheel problem
        //

        //go through all of the issues we have and
        for (i in 0 until 5) {
            val currentIssue: String = allIssues.get(i)
            if (currentIssue.contains(searchText)) {
                //add to the filtered list
                filteredList!!.add(currentIssue)
                /*adapter = new ArrayAdapter<String>?(this, android.R.layout.simple_list_item_1,
                    allIssues);
                listView.setAdapter(adapter);*/

        }


        //TODO notify datasetchanged
    }

    }
}
