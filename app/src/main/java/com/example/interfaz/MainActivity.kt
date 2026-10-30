package com.example.interfaz

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageButton>(R.id.btnLlamar).setOnClickListener {
            startActivity(Intent(this, CallActivity::class.java))
        }

        findViewById<ImageButton>(R.id.btnWeb).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")))
        }

        findViewById<ImageButton>(R.id.btnMail).setOnClickListener {
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:")))
        }

        findViewById<ImageButton>(R.id.btnMaps).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Google+Maps")))
        }

        findViewById<Button>(R.id.btnConfig).setOnClickListener {
            startActivity(Intent(this, ConfigActivity::class.java))
        }
    }
}