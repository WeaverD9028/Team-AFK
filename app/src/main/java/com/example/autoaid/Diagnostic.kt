package com.example.autoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import org.w3c.dom.Text
import android.content.Intent
import java.text.DateFormat
import java.util.Calendar
import kotlin.random.Random


class Diagnostic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnostic)

        // Variable for car type TextView
        val carType: TextView = findViewById(R.id.car)

        // Variable for date TextView
        val dateBox: TextView = findViewById(R.id.date)

        // Variable for issue EditText
        // 1-4 multiline
        val issueList: EditText = findViewById(R.id.issues)

        // Variable for price EditText
        val estPrice: EditText = findViewById(R.id.price)

        // Variable for location services button
        // This button should take you to the location page (Google Map API)
        val location: ImageButton = findViewById(R.id.location)

        // Variable for YT video button
        // This button should take you to a video page (WebView)
        val video: ImageButton = findViewById(R.id.vid)


        // If you click the location button
        location.setOnClickListener {
            // Take to location page
            startActivity(Intent(this@Diagnostic,GoogleMapsActivity::class.java))
        }


        // If you click the video button
        video.setOnClickListener {
            // Take to video page
        }

        // ************** For demo purpose ******************

        // Variable for list of potential cars
        val carList = arrayOf(
            "Dodge Challenger",
            "Corvette Z06",
            "Hyundai Sonata",
            "Range Rover"
        )
        // Variable for list of potential issues
        val demoList = arrayOf(
            "Tire air leak",
            "Coolant levels low",
            "Faulty spark plugs",
            "Brakes need changing",
            "Power steering fluid low"
        )

        // Variable for date
        val calendar = Calendar.getInstance().time
        val dateFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar).plus(" ")
        // Variable for time
        val timeFormat = DateFormat.getTimeInstance().format(calendar)
        // Show the date
        showDate(dateFormat.plus(timeFormat),dateBox)


        // Select a random number between 0-3
        val j: Int = Random.nextInt(0,3)
        showCar(carList[j],carType)

        // Select a random integer between 1-5
        val i: Int = Random.nextInt(0, 4)

        // Print the issue from list at that index into the issue bar
        for (thing in 0..i) {
            showIssue(demoList[thing], issueList)
        }

        // Calculate a price and display
        if(i == 0) {
            val demoPrice: String = (100).toString()
            // Print the price to the screen
            showPrice(demoPrice, estPrice)
        } else if(i == 1){
            val demoPrice: String = (200).toString()
            // Print the price to the screen
            showPrice(demoPrice,estPrice)
        }else{
            val demoPrice: String = (i*100).toString()
            // Print the price to the screen
            showPrice(demoPrice, estPrice)
        }


    } // End of activity instance
    // ***************** Display Functions *****************

        // Function that takes in an issue: String and an EditText
        // Displays the issue in the EditText, then moves to the next line
        private fun showIssue(problem: String, space: EditText) {
            val temp: String = (space.text.toString()).plus(problem)
            space.setText(temp.plus("\n"))
        }


        // Function that takes in a price: string and an EditText
        // Displays the price inside the EditText
        private fun showPrice(cost: String, space: EditText) {
            space.setText("$".plus(cost))


        }

    // Function that takes in a car type: String and a TextView
    // Displays the car to the screen
    private fun showCar(carType:String,space:TextView){
        space.text = "Vehicle: ".plus(carType)

    }

    // Function that takes in a date: String and a TextView
    // Displays the current date to the screen
    private fun showDate(date:String,space:TextView){
        space.text = "Date: ".plus(date)
    }

} // End of class