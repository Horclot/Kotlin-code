package com.example.encoder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1: Button = findViewById(R.id.button)  //расшифратор
        val button2: Button = findViewById(R.id.button2) //шифратор
    button1.setOnClickListener {
        val intent = Intent(this, decryptor::class.java)
        startActivity(intent) }
    button2.setOnClickListener {
        val intent = Intent(this, encryptor::class.java)
        startActivity(intent)}
    }
}