package com.example.autoaid


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewCars: AppCompatActivity() {
    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private lateinit var infoModalArrayList: ArrayList<InfoModel>
    private lateinit var dbHandler: DBHandler
    private lateinit var carRVAdapter: CarRVAdapter
    private lateinit var carsRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_car)

        // initializing our all variables.
        infoModalArrayList = ArrayList()
        dbHandler = DBHandler(this@ViewCars)

        // getting our course array
        // list from db handler class.
        infoModalArrayList = dbHandler.readCar()

        // on below line passing our array list to our adapter class.
        carRVAdapter = CarRVAdapter(infoModalArrayList!!, this)
        carsRV = findViewById(R.id.idRVCars)

        // setting layout manager for our recycler view.
        val linearLayoutManager =
            LinearLayoutManager(this@ViewCars, RecyclerView.VERTICAL, false)
        carsRV.setLayoutManager(linearLayoutManager)

        // setting our adapter to recycler view.
        carsRV.setAdapter(carRVAdapter)


    }
}