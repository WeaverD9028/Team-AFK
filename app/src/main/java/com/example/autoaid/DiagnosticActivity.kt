package com.example.autoaid


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlinx.android.synthetic.main.activity_diagnostic.*
import kotlinx.coroutines.delay

import kotlinx.coroutines.runBlocking
import org.joda.time.DateTime
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList
import kotlin.reflect.typeOf

class DiagnosticActivity : AppCompatActivity() {
    private lateinit var dbHandler: DBHandler
    private lateinit var descriptionTV : TextView
    private lateinit var carvinTV : TextView
    private lateinit var carmakeTV : TextView
    private lateinit var carmodelTV : TextView
    private lateinit var caryearTV : TextView
    private lateinit var carcodeTV : TextView
    private lateinit var priceTV : TextView
    private lateinit var  btndiy : ImageButton
    private lateinit var btnlocation : ImageButton
    private lateinit var btnsave : Button
    private lateinit var btnHome : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        dbHandler = DBHandler(this@DiagnosticActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnostic)


        descriptionTV = findViewById(R.id.issues)
        carvinTV = findViewById(R.id.carVinTV)
        carcodeTV = findViewById(R.id.carCodeTV)
        carmakeTV = findViewById(R.id.carMakeTV)
        carmodelTV = findViewById(R.id.carModelTV)
        caryearTV = findViewById(R.id.carYearTV)
        priceTV = findViewById(R.id.priceTV)
        btndiy = findViewById(R.id.vid)
        btnlocation = findViewById(R.id.location)
        btnsave = findViewById(R.id.btnSave)
        btnHome = findViewById(R.id.btnHome)



        val vin = intent.getStringExtra("Vin")
        val description = intent.getStringExtra("Description")
        val code = intent.getStringExtra("Code")

        carvinTV.setText(vin)
        descriptionTV.setText(description)
        carcodeTV.setText(code)
        reportinfo().start()
        carInformation().start()
        givenAsyncCoroutine_whenStartIt_thenShouldExecuteItInTheAsyncWay()
        val l1 = stringToList(dbHandler!!.readSpecficDia(code).toString())
        // val l2 = pickCost(l1)

        //priceTV.setText(l1.toString())

        priceTV.setText("$100.00")

        //hides navigation bar
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.navigationBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        btnlocation.setOnClickListener {
            val i = Intent(this@DiagnosticActivity, GoogleMapsActivity::class.java)
            startActivity(i)
        }

        // go to home page
        btnHome.setOnClickListener{
            startActivity(Intent(this@DiagnosticActivity,HomePage::class.java))
        }

        btndiy.setOnClickListener {
            val i = Intent(this@DiagnosticActivity, YouTubeActivity::class.java)
            i.putExtra("Vin", carvinTV.text.toString())
            i.putExtra("Description", descriptionTV.text.toString())
            i.putExtra("Make", carmakeTV.text.toString())
            i.putExtra("Model", carmodelTV.text.toString())
            i.putExtra("Year", caryearTV.text.toString())
            startActivity(i)

        }

        btnsave.setOnClickListener{
            println(carmakeTV.text)
            println(carmodelTV.text)
            println(caryearTV.text)
            println(carvinTV.text)
            println(descriptionTV.text)
            println(carcodeTV.text)
            println(priceTV.text)
            val date = DateTime().toLocalDate().toString()
            val time = DateTime().toLocalTime().toString()
            dbHandler!!.addSavedReport(date,
                time,
                carmakeTV.text.toString(),
                carmodelTV.text.toString(),
                caryearTV.text.toString(),
                carvinTV.text.toString(),
                descriptionTV.text.toString(),
                carcodeTV.text.toString(),
                priceTV.text.toString())
            Toast.makeText(applicationContext, "Report Saved", Toast.LENGTH_SHORT).show()
        }





    }

    fun reportinfo(): Thread{

        return Thread {

            val url = "http://api.carmd.com/v3.0/repairinfo?repair_code=" + carCodeTV.text
            carVin.text
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
            val l1 = stringToList(data)

            println(l1)
            val l2 = pickList(l1)
            println(l2)
            val l3 = cleanList(l2)

            println("${carvinTV.text}, ${descriptionTV.text}, ${carcodeTV.text}")
            dbHandler!!.addNewDiagnotic(carvinTV.text.toString(), descriptionTV.text.toString(), carcodeTV.text.toString(), l3[2])
            println(l3)

        }
    }

    fun carInformation(): Thread{
        println(carvinTV.text)
        // 1GNALDEK9FZ108495

        return Thread {

            val url = "https://api.carmd.com/v3.0/decode?vin=" + carVinTV.text
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
            // println(data)
            var l1 = stringToList(data)
            var l2 = carInfoList(l1)
            println(l2.get(0))
            println(l2.get(1))
            println(l2.get(2))
            println(l2.get(3))

            println(l2[0])
            println(l2[1])
            println(l2[2])

            carmakeTV.setText("Make: " + l2[0])
            carmodelTV.setText("Model: " + l2[1])
            caryearTV.setText("Year: " + l2[2])

        }

    }

    private fun carInfoList(l1: MutableList<String>): MutableList<String> {
        val l2 = ArrayList<String>()
        val year = l1.get(0).removeRange(0, 7).trim('"')
        val make = l1.get(1).removeRange(0, 7).trim('"')
        val model = l1.get(2).removeRange(0, 8).trim('"')
        val engine = l1.get(4).removeRange(0, 9).trim('"') + "," + l1.get(5).trim('"')

        l2.add(0,make)
        l2.add(1,model)
        l2.add(2,year)
        l2.add(3,engine)

        return l2
    }


    private fun stringToList(mnemonic: String): MutableList<String> {
        val words = ArrayList<String>()
        for (w in mnemonic.trim('{').trim('}').split(",")) {
            if (w.isNotEmpty()) {
                words.add(w)
            }
        }
        return words
    }

    private fun pickList(l1: MutableList<String>): MutableList<String>{
        val l2 = ArrayList<String>()
        for (i in l1){
            if (i.contains("total_cost") || (i.contains("desc")) || (i.contains("repair_code"))){
                l2.add(i)
            }
        }
        return l2
    }

    private fun cleanList(l1: MutableList<String>): MutableList<String>{
        val l2 = ArrayList<String>()
        val l3 = ArrayList<String>()
        for (i in l1){
            l2.add(i.trim('[').trim(']').trim('"').trim('"').trim(':').trim('{').trim('}'))
        }
        l3.add(l2[0].removeRange(0,15))
        l3.add(l2[1].removeRange(0,7))
        l3.add(l2[2].removeRange(0,12))
        return l3
    }

    private fun pickCost(l1: MutableList<String>): String{
        val l2 = ArrayList<String>()
        for (i in l1){
            if (i.contains("carCost")){
                l2.add(i)
            }
        }
        val cost = l2[0].removeRange(0,9).trim(')')

        return cost
    }

    suspend fun pause(){
        delay(2000)
        println("We pause here for the database to catch up!!!!!!!!!!!!!")
    }

    fun givenAsyncCoroutine_whenStartIt_thenShouldExecuteItInTheAsyncWay(){
        // Start loading screen activity here
        runBlocking { pause() }
    }


}