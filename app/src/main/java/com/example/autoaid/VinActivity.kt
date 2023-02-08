package com.example.autoaid

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class VinActivity : AppCompatActivity() {

    lateinit var textinput : EditText
    lateinit var btnout : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vin)

        textinput = findViewById(R.id.textInput)
        btnout = findViewById(R.id.btnOut)

        btnout.setOnClickListener{
            buttonClick().start()
        }
        // fetchCarData().start()

        // findViewById<TextView>(R.id.txtbox).text






    }

    fun buttonClick(): Thread{
        println(textinput.text)
        // 1GNALDEK9FZ108495

        return Thread {

            val url = "https://api.carmd.com/v3.0/decode?vin=" + textinput.text
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

            println(myResponse.toString())
            println("result after Reading JSON Response")
            println(myResponse.getString("data"))
        }
    }
}