package com.example.autoaid


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class UpdateVinActivity : AppCompatActivity() {
    // variables for our edit text, button, strings and dbhandler class.
    private lateinit var carVinEdt: EditText
    private lateinit var updateCarVinBtn: Button
    private lateinit var informationCarVinBtn : Button
    private lateinit var readCarVinBtn : Button
    private lateinit var readCarSymptomsBtn : Button
    private lateinit var viewCarSymptomsBtn : Button
    private lateinit var dbHandler: DBHandler
    var carVin: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_vin)

        // initializing all our variables.
        carVinEdt = findViewById(R.id.idEdtCarVin)
        updateCarVinBtn = findViewById(R.id.idBtnUpdateVin)
        informationCarVinBtn = findViewById(R.id.idBtnInfoVin)
        readCarVinBtn = findViewById(R.id.idBtnReadVin)
        readCarSymptomsBtn = findViewById(R.id.idBtnReadSymptoms)
        viewCarSymptomsBtn = findViewById(R.id.idBtnViewSymptoms)

        // on below line we are initializing our dbhandler class.
        dbHandler = DBHandler(this@UpdateVinActivity)

        // on below lines we are getting data which
        // we passed in our adapter class.
        carVin = intent.getStringExtra("Vin")


        // setting data to edit text
        // of our update activity.
        carVinEdt.setText(carVin)


        // adding on click listener to our update course button.
        updateCarVinBtn.setOnClickListener(View.OnClickListener {// inside this method we are calling an update course
            // method and passing all our edit text values.
            dbHandler!!.updateVin(
                carVin!!,
                carVinEdt.getText().toString(),

                )

            // displaying a toast message that our course has been updated.
            Toast.makeText(this@UpdateVinActivity, "Vin Updated..", Toast.LENGTH_SHORT).show()

            // launching our main activity.
            val i = Intent(this@UpdateVinActivity, MainActivity::class.java)
            startActivity(i)

            println("UPDATED")


        })

        // adding on click listener to read our car button.
        readCarVinBtn.setOnClickListener(View.OnClickListener {// open a new activity
            val i = Intent(this, ViewCars::class.java)
            startActivity(i)

        })

        readCarSymptomsBtn.setOnClickListener {
            println("SYMPTOMS")
            repairCode().start()
        }


        // adding on click listener to our information vin button.
        informationCarVinBtn.setOnClickListener(View.OnClickListener {// inside this method we are calling to get information on the vin using the api
            buttonClick().start()


        })

        // Viewing the symptoms of the car the user has inputted
        viewCarSymptomsBtn.setOnClickListener {
            val i = Intent(this, ViewSymptoms::class.java)
            startActivity(i)
        }


    }
    fun buttonClick(): Thread{
        println(carVinEdt.text)
        // 1GNALDEK9FZ108495

        return Thread {

            val url = "https://api.carmd.com/v3.0/decode?vin=" + carVinEdt.text
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
            dbHandler!!.addNewCar(
                l2.get(0),
                l2.get(1),
                l2.get(2),
                l2.get(3)
            )

        }

    }

    fun repairCode(): Thread{
        println(carVinEdt.text)
        // 1GNALDEK9FZ108495

        return Thread {

            val url = "http://api.carmd.com/v3.0/repairlist?vin=" + carVinEdt.text
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

            var repairList = stringToList(data)

            var cleanRepairList = RepairInfoList(repairList)

            var officialRepairList = CleanRepairList(cleanRepairList)

            println(officialRepairList.size)
            for(x in 0 until officialRepairList.size){
                if (x.mod(2) == 0){
                    dbHandler.addNewSym(carVin, officialRepairList[x], officialRepairList[x+1])
                    println(officialRepairList[x])
                    println( officialRepairList[x+1])
                }
            }
        }

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

    private fun RepairInfoList(l1: MutableList<String>): MutableList<String> {
        val l2 = ArrayList<String>()
        for (num in 0 until l1.size) {
            if ((l1[num].contains("item")) or (l1[num].contains("repair_code"))) {
                l2.add(l1[num])
            }
        }
        return l2
    }

    private fun CleanRepairList(l1: MutableList<String>): MutableList<String>{
        val l2 = ArrayList<String>()
        var scrub = String()
        var secondScrub = String()
        var lastScrub = String()
        for (num in 0 until l1.size){
            if(l1[num].contains("item")){
                scrub = l1[num].trim('[').trim('{').trim('"').trim(':').trim('"')
                l2.add(scrub.removeRange(0,7))
            }
            if(l1[num].contains("repair")){
                scrub = l1[num].trim('"').trim(':').trim('}')
                secondScrub = scrub.removeRange(0,13).trim('"').trim('"')
                lastScrub = secondScrub.trim('"').trim('}').trim(']')
                l2.add(lastScrub)
            }
        }
        return l2
    }


}
