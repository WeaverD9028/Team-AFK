package com.example.autoaid

import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ReportActivity() : AppCompatActivity(){

    private lateinit var edMake : EditText
    private lateinit var edModel : EditText
    private lateinit var edYear : EditText
    private lateinit var btnAdd : Button
    private lateinit var btnView: Button
    private lateinit var btnUpdate: Button

    private lateinit var reportDataBase: ReportDataBase
    private lateinit var recyclerView:RecyclerView
    private var adapter:VehicleAdapter? = null
    private var veh:VehModelClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        initView()
        initRecyclerView()
        reportDataBase = ReportDataBase(this)

        btnAdd.setOnClickListener{ addVehicle() }
        btnView.setOnClickListener{ getVehicles() }
        btnUpdate.setOnClickListener{ updateVehicle() }
        // Ok Now we need to delete record.

        adapter?.setOnClickItem {
            Toast.makeText(this, it.model, Toast.LENGTH_SHORT).show()
            // Ok now we need to update record.
            edMake.setText(it.make)
            edModel.setText(it.model)
            edYear.setText(it.year)
            veh = it

        }
        adapter?.setOnClickDeleteItem {
            deleteVehicle(it.id)
        }
    }

    private fun getVehicles() {
        val vehList = reportDataBase.viewVehical()
        Log.e("pppp", "${vehList.size}")

        // Now we need to display data in RecyclerView
        adapter?.addItems(vehList)

    }

    private fun addVehicle(){
        val make = edMake.text.toString()
        val model = edModel.text.toString()
        val year = edYear.text.toString()
        val id = "nfsadf34"

        if(make.isEmpty() || model.isEmpty() || year.isEmpty()){
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        }else{
            val veh = VehModelClass(id = id, make = make, model = model, year = year)
            val status = reportDataBase.addVehicle(veh)
            // Check insert success or not
            if(status > -1){
                Toast.makeText(this, "Vehical Added..", Toast.LENGTH_SHORT).show()
                clearEditText()
                getVehicles()
            }else{
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun updateVehicle(){
        val make = edMake.text.toString()
        val model = edModel.text.toString()
        val year = edYear.text.toString()

        //CHeck record not change
        if(make == veh?.make && model == veh?.model && year == veh?.year){
            Toast.makeText(this, "Record not changed...", Toast.LENGTH_SHORT).show()
            return
        }

        if(veh == null)return

        val veh = VehModelClass(id = veh!!.id, make = make, model = model, year = year)
        val status = reportDataBase.updateVehicle(veh)
        if(status >- 1){
            clearEditText()
            getVehicles()
        }else{
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show()
        }


    }

    private fun deleteVehicle(id:String){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete item?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){dialog, _ ->
            reportDataBase.deleteVehicle(id)
            getVehicles()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){dialog, _ ->

            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()

    }

    private fun clearEditText(){
        edMake.setText("")
        edModel.setText("")
        edMake.requestFocus()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = VehicleAdapter()
        recyclerView.adapter
    }

    private fun initView(){
        edMake = findViewById(R.id.edCarMake)
        edModel = findViewById(R.id.edCarModel)
        edYear = findViewById(R.id.edCarYear)
        btnAdd = findViewById(R.id.btn_Add)
        btnView = findViewById(R.id.btn_View)
        btnUpdate= findViewById(R.id.btn_Update)
        recyclerView = findViewById(R.id.recyclerView)
    }

}