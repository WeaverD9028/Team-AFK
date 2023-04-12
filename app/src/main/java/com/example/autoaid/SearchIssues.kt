package com.example.autoaid

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
//import com.example.autoaid.SymptomsActivity.Companion.allIssues
import com.example.autoaid.databinding.ActivitySearchIssuesBinding

class SearchIssues : AppCompatActivity() {
    var binding: ActivitySearchIssuesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_issues)
        val searchView: SearchView = findViewById(R.id.searchid);
        var filteredList: ArrayList<String> = ArrayList()
        filteredList.add("Warning Lights")
        filteredList.add("A/C Issues")
        filteredList.add("Noisy Engine")
        filteredList.add("Squeaky or Grindy Brakes")
        filteredList.add("Car will not start")

        lateinit var carSymptomsLV: ListView
        lateinit var listadapter: ArrayAdapter<String>

        carSymptomsLV = findViewById(R.id.carSymptoms)

        listadapter = ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1,
            filteredList
            )

        carSymptomsLV.adapter = listadapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (filteredList.contains(query)) {
                    listadapter.filter.filter(query)
                } else {
                    Toast.makeText(this@SearchIssues, "No Symptoms found ..", Toast.LENGTH_LONG)
                        .show()
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
            // if query text is change in that case we
            // are filtering our adapter with
            // new text on below line.
            listadapter.filter.filter(newText)
            return false
        }
        })

        //val listView: ListView = findViewById(R.id.listView)var adapter: ArrayAdapter<*>



        val new = findViewById<Button>(R.id.issuecont)
        new.setOnClickListener() {
            val Intent = Intent(this,VehicleIssues::class.java)
            startActivity(Intent)
        }

     /*   filteredList = ArrayList()
    }


    private fun filterIssues(searchText: String): ArrayList<String> {
        //clear the filtered list
        filteredList?.clear()

        //search: EE
        // none
        // steering wheel
        // wheel problem
        //

        //go through all of the issues we have and
        for (i in allIssues.indices) {
            val currentIssue: String = allIssues.get(i)
            if (currentIssue.contains(searchText)) {
                //add to the filtered list
                filteredList!!.add(currentIssue)

            }
            //TODO notify datasetchanged
        }
        return filteredList
            }
            //TODO notify datasetchanged
        }*/
    }
}



