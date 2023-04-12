package com.example.autoaid

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class VinRVAdapter     // constructor
    (// variable for our array list and context
    private val carModalArrayList: ArrayList<CarModel>,
    private val context: Context) : RecyclerView.Adapter<VinRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vin_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: VinRVAdapter.ViewHolder, position: Int) {
        // on below line we are setting data
        // to our views of recycler view item.
        val (carVin) = carModalArrayList[position]
        holder.carVinTV.setText(carVin)


        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(View.OnClickListener { // on below line we are calling an intent.
            val i = Intent(context, UpdateVinActivity::class.java)

            // below we are passing all our values.
            i.putExtra("Vin", carVin)


            // starting our activity.
            context.startActivity(i)
        })
    }

    override fun getItemCount(): Int {
        // returning the size of our array list
        return carModalArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our text views.
        val carVinTV: TextView


        init {
            // initializing our text views
            carVinTV = itemView.findViewById(R.id.idTVCarVin)
        }
    }
}