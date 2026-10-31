package com.example.interfaz

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        val etPhoneConfig = findViewById<EditText>(R.id.etPhoneConfig)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnReturn = findViewById<Button>(R.id.btnReturn)

        val sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        etPhoneConfig.setText(sharedPreferences.getString("PHONE_KEY", "112"))

        btnSave.setOnClickListener {
            with(sharedPreferences.edit()) {
                putString("PHONE_KEY", etPhoneConfig.text.toString())
                apply()
            }
            Toast.makeText(this, "Guardado con éxito", Toast.LENGTH_SHORT).show()
        }

        btnReturn.setOnClickListener {
            finish()
        }
    }
}