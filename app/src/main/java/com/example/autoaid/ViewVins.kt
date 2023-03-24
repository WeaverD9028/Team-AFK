package com.example.autoaid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewVins : AppCompatActivity() {
    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private lateinit var carModalArrayList: ArrayList<CarModel>
    private lateinit var dbHandler: DBHandler
    private lateinit var vinRVAdapter: VinRVAdapter
    private lateinit var vinsRV: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_vin)

        // initializing our all variables.
        carModalArrayList = ArrayList()
        dbHandler = DBHandler(this@ViewVins)

        // getting our course array
        // list from db handler class.
        carModalArrayList = dbHandler.readVin()

        // on below line passing our array list to our adapter class.
        vinRVAdapter = VinRVAdapter(carModalArrayList!!, this)
        vinsRV = findViewById(R.id.idRVVins)

        // setting layout manager for our recycler view.
        val linearLayoutManager =
            LinearLayoutManager(this@ViewVins, RecyclerView.VERTICAL, false)
        vinsRV.setLayoutManager(linearLayoutManager)

        // setting our adapter to recycler view.
        vinsRV.setAdapter(vinRVAdapter)


    }
}