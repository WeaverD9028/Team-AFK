package com.example.autoaid


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewSymptoms : AppCompatActivity() {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private lateinit var symModalArrayList: ArrayList<RepairModel>
    private lateinit var dbHandler: DBHandler
    private lateinit var symRVAdapter: SymRVAdapter
    private lateinit var symsRV: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_symptoms)

        // initializing our all variables.
        symModalArrayList = ArrayList()
        dbHandler = DBHandler(this@ViewSymptoms)

        // getting our symptoms array
        // list from db handler class.
        symModalArrayList = dbHandler.readSym()

        // on below line passing our array list to our adapter class.
        symRVAdapter = SymRVAdapter(symModalArrayList!!, this)
        symsRV = findViewById(R.id.idRVSymptoms)

        // setting layout manager for our recycler view.
        val linearLayoutManager =
            LinearLayoutManager(this@ViewSymptoms, RecyclerView.VERTICAL, false)
        symsRV.setLayoutManager(linearLayoutManager)

        // setting our adapter to recycler view.
        symsRV.setAdapter(symRVAdapter)

    }
}