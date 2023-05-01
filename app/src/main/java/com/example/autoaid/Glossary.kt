package com.example.autoaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_glossary.*


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


    }
}