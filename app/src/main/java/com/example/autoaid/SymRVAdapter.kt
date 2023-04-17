package com.example.autoaid

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList

class SymRVAdapter // constructor
    (// variable for our array list and context
    private val symModalArrayList: ArrayList<RepairModel>,
    private val context: Context,
    ) : RecyclerView.Adapter<SymRVAdapter.ViewHolder>() {

    private lateinit var dbHandler : DBHandler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.symptom_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SymRVAdapter.ViewHolder, position: Int) {
        // on below line we are setting data
        // to our views of recycler view item.
        val (carVin,symDescription, symCode) = symModalArrayList[position]
        holder.carVinTV.setText(carVin)
        holder.symDescriptionTV.setText(symDescription)
        holder.symCodeTV.setText(symCode)





        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(View.OnClickListener { // on below line we are calling an intent.
            val i = Intent(context, DiagnosticActivity::class.java)


            // below we are passing all our values.
            i.putExtra("Vin", carVin)
            i.putExtra("Description", symDescription)
            i.putExtra("Code", symCode)


            // starting our activity.
            context.startActivity(i)
        })
    }

    override fun getItemCount(): Int {
        // returning the size of our array list
        return symModalArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our text views.
        val carVinTV : TextView
        val symDescriptionTV : TextView
        val symCodeTV : TextView


        init {
            // initializing our text views
            carVinTV = itemView.findViewById(R.id.idTVCarVin)
            symDescriptionTV = itemView.findViewById(R.id.idTVCarSymptoms)
            symCodeTV = itemView.findViewById(R.id.idTVSymptonCode)

        }
    }
    }
