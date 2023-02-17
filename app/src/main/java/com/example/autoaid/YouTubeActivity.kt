package com.example.autoaid

import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class YouTubeActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_tube)

        val webViewBuild = findViewById<WebView>(R.id.web_view)

        webViewBuild.webViewClient = WebViewClient()
        webViewBuild.apply {
            loadUrl("https://www.youtube.com/@chrisfix/videos")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }

}
