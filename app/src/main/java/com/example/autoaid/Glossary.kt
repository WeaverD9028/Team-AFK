package com.example.autoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class Glossary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glossary)


        //aftermarket
        val button1:TextView = findViewById(R.id.textView20)
        val button1Def:TextView = findViewById(R.id.textView21)
        val image1:ImageView = findViewById(R.id.imageView11)
        button1Def.visibility = View.GONE
        image1.visibility = View.GONE

        button1.setOnClickListener() //aftermarket
        {
            if (button1Def.visibility == View.GONE){
                button1Def.visibility = View.VISIBLE
                image1.visibility = View.VISIBLE
            }
            else {
                button1Def.visibility = View.GONE
                image1.visibility = View.GONE

            }
        }

        //antilock
        val button2:TextView = findViewById(R.id.anti)
        val button2Def:TextView = findViewById(R.id.anti1)
        val image2:ImageView = findViewById(R.id.imageView111)
        button2Def.visibility = View.GONE
        image2.visibility = View.GONE

        button2.setOnClickListener() //aftermarket
        {
            if (button2Def.visibility == View.GONE){
                button2Def.visibility = View.VISIBLE
                image2.visibility = View.VISIBLE
            }
            else {
                button2Def.visibility = View.GONE
                image2.visibility = View.GONE

            }
        }

        //break
        val button3:TextView = findViewById(R.id.breakM)
        val button3Def:TextView = findViewById(R.id.breakM1)
        val image3:ImageView = findViewById(R.id.imageView112)
        button3Def.visibility = View.GONE
        image3.visibility = View.GONE

        button3.setOnClickListener() //aftermarket
        {
            if (button3Def.visibility == View.GONE){
                button3Def.visibility = View.VISIBLE
                image3.visibility = View.VISIBLE
            }
            else {
                button3Def.visibility = View.GONE
                image3.visibility = View.GONE

            }
        }

        //carbon
        val button4:TextView = findViewById(R.id.carbon)
        val button4Def:TextView = findViewById(R.id.carbon1)
        val image4:ImageView = findViewById(R.id.imageView113)
        button4Def.visibility = View.GONE
        image4.visibility = View.GONE

        button4.setOnClickListener()
        {
            if (button4Def.visibility == View.GONE){
                button4Def.visibility = View.VISIBLE
                image4.visibility = View.VISIBLE
            }
            else {
                button4Def.visibility = View.GONE
                image4.visibility = View.GONE
            }
        }

        //dead
        val button5:TextView = findViewById(R.id.dead)
        val button5Def:TextView = findViewById(R.id.dead1)
        val image5:ImageView = findViewById(R.id.imageView115)
        button5Def.visibility = View.GONE
        image5.visibility = View.GONE

        button5.setOnClickListener()
        {
            if (button5Def.visibility == View.GONE){
                button5Def.visibility = View.VISIBLE
                image5.visibility = View.VISIBLE
            }
            else {
                button5Def.visibility = View.GONE
                image5.visibility = View.GONE
            }
        }

        //dead
        val button6:TextView = findViewById(R.id.engine)
        val button6Def:TextView = findViewById(R.id.engine1)
        val image6:ImageView = findViewById(R.id.imageView116)
        button6Def.visibility = View.GONE
        image6.visibility = View.GONE

        button6.setOnClickListener()
        {
            if (button6Def.visibility == View.GONE){
                button6Def.visibility = View.VISIBLE
                image6.visibility = View.VISIBLE
            }
            else {
                button6Def.visibility = View.GONE
                image6.visibility = View.GONE
            }
        }

    }
}