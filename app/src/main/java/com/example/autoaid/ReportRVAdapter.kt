package com.example.autoaid

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.util.ArrayList
import java.util.Date

class ReportRVAdapter // constructor
    (// variable for our array list and context
    private val savedreportModalArrayList: ArrayList<SavedReportModel>,
    private val context: Context
) : RecyclerView.Adapter<ReportRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.report_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportRVAdapter.ViewHolder, position: Int) {
        // on below line we are setting data
        // to our views of recycler view item.
        val (date, time, carMake, carModel,carYear, carVin, carDescription, carCode, carCost) = savedreportModalArrayList[position]
        holder.reportDate.setText(date)
        holder.reportTime.setText(time)
        holder.carMakeTV.setText(carMake)
        holder.carModelTV.setText(carModel)
        holder.carYearTV.setText(carYear)
        holder.carRepairTV.setText(carDescription)





        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(View.OnClickListener { // on below line we are calling an intent.
            val i = Intent(context, SavedReports::class.java)

            // below we are passing all our values.
            i.putExtra("Date", date)
            i.putExtra("Time", time)
            i.putExtra("Make", carMake)
            i.putExtra("Model", carModel)
            i.putExtra("Year", carYear)
            i.putExtra("Description", carDescription)
            i.putExtra("Cost", carCost)
            i.putExtra("Code", carCode)
            i.putExtra("Vin", carVin)


            println("$carMake $carModel $carCost $carCode $carDescription")


            // starting our activity.
            context.startActivity(i)
        })
    }

    override fun getItemCount(): Int {
        // returning the size of our array list
        return savedreportModalArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our text views.
        val reportDate : TextView
        val reportTime : TextView
        val carMakeTV : TextView
        val carModelTV : TextView
        val carYearTV : TextView
        val carRepairTV : TextView



        init {
            // initializing our text views
            reportDate = itemView.findViewById(R.id.idTVDate)
            reportTime = itemView.findViewById(R.id.idTVTime)
            carMakeTV = itemView.findViewById(R.id.idTVCarMake)
            carModelTV = itemView.findViewById(R.id.idTVCarModel)
            carYearTV = itemView.findViewById(R.id.idTVCarYear)
            carRepairTV = itemView.findViewById(R.id.idTVCarRepair)


        }
    }
}