package com.example.autoaid
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class CarRVAdapter  // constructor
    (// variable for our array list and context
    private val infoModalArrayList: ArrayList<InfoModel>,
    private val context: Context) : RecyclerView.Adapter<CarRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarRVAdapter.ViewHolder, position: Int) {
        // on below line we are setting data
        // to our views of recycler view item.
        val (carMake, carModel,carYear, carEngine) = infoModalArrayList[position]
        holder.carMakeTV.setText(carMake)
        holder.carModelTV.setText(carModel)
        holder.carYearTV.setText(carYear)
        holder.carEngineTV.setText(carEngine)




        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(View.OnClickListener { // on below line we are calling an intent.
            val i = Intent(context, UpdateVinActivity::class.java)

            // below we are passing all our values.
            i.putExtra("Make", carMake)
            i.putExtra("Model", carModel)
            i.putExtra("Year", carYear)
            i.putExtra("Engine", carEngine)

            println("$carMake $carModel")


            // starting our activity.
            context.startActivity(i)
        })
    }

    override fun getItemCount(): Int {
        // returning the size of our array list
        return infoModalArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our text views.
        val carMakeTV : TextView
        val carModelTV : TextView
        val carYearTV : TextView
        val carEngineTV : TextView


        init {
            // initializing our text views
            carMakeTV = itemView.findViewById(R.id.idTVCarMake)
            carModelTV = itemView.findViewById(R.id.idTVCarModel)
            carYearTV = itemView.findViewById(R.id.idTVCarYear)
            carEngineTV = itemView.findViewById(R.id.idTVCarEngine)
        }
    }
}