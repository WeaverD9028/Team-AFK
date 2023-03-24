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
    private val context: Context
) : RecyclerView.Adapter<SymRVAdapter.ViewHolder>() {
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

            println(symModalArrayList[position].descriptionCode)
            reportinfo(position).start()


            // starting our activity.
            // context.startActivity(i)
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

    fun reportinfo(position: Int): Thread{
        println(symModalArrayList[position].descriptionCode)

        return Thread {

            val url = "http://api.carmd.com/v3.0/repairinfo?repair_code=" + symModalArrayList[position].descriptionCode
            val obj = URL(url)
            val con = obj.openConnection() as HttpURLConnection
            // optional default is GET
            con.requestMethod = "GET"
            //add request header
            con.setRequestProperty("Content-Type", "application/json")
            con.setRequestProperty("Authorization", "Basic ZjhjODkwN2MtOWRkMy00ZjkzLWIxM2MtZGIyZTM2NTlhMzFk")
            con.setRequestProperty("partner-token", "db6ef5ce8fd14fcd808057ae6f4c001e")
            val responseCode = con.responseCode
            println("\nSending 'GET' request to URL : $url")
            println("Response Code : $responseCode")
            val `in` = BufferedReader(
                InputStreamReader(con.inputStream)
            )
            var inputLine: String?
            val response = StringBuffer()
            while (`in`.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            `in`.close()
            //print in String
            //System.out.println(response.toString());
            //Read JSON response and print
            val myResponse = JSONObject(response.toString())

            // println(myResponse.toString())
            println("result after Reading JSON Response")
            //println(myResponse.getString("data"))
            //println(myResponse.getString("data"))
            var data = myResponse.getString("data")
            println(data)



        }

    }

}