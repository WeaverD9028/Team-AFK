package com.example.autoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import kotlin.random.Random


class Diagnostic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnostic)


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
        }


        // If you click the video button
        video.setOnClickListener {
            // Take to video page
        }

        // For demo purpose
        // Variable for list of potential issues
        val demoList = arrayOf(
            "Tire air leak",
            "Coolant levels low",
            "Faulty spark plugs",
            "Brakes need changing",
            "Power steering fluid low"
        )

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

        // Function that takes in an issue: String and an EditText
        // Displays the issue in the EditText, then moves to the next line
        private fun showIssue(problem: String, space: EditText) {
            val temp: String = (space.text.toString()).plus(problem)
            space.setText(temp.plus("\n"))
        }


        // Function that takes in a price: string and an EditText
        // Displays the price inside the EditText
        private fun showPrice(cost: String, space: EditText) {
            val temp = "$"
            space.setText(temp.plus(cost))


        }

} // End of class