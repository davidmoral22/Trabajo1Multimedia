package com.example.interfaz

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class CallActivity : AppCompatActivity() {

    private var phoneNumber: String = "112"

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            makePhoneCall()
        } else {
            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        val btnMakeCall = findViewById<Button>(R.id.btnMakeCall)
        val tvPhoneNumber = findViewById<TextView>(R.id.tvPhoneNumber)

        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        phoneNumber = sharedPreferences.getString("PHONE_KEY", "112") ?: "112"
        tvPhoneNumber.text = "Número a llamar: $phoneNumber"

        btnMakeCall.setOnClickListener {
            checkAndRequestCallPermission()
        }
    }

    private fun checkAndRequestCallPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED -> {
                    makePhoneCall()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE)
                }
            }
        } else {
            makePhoneCall()
        }
    }

    private fun makePhoneCall() {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(intent)
    }
}