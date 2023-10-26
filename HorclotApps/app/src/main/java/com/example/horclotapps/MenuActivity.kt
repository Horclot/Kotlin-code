package com.example.horclotapps

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MenuActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val but1: Button = findViewById(R.id.buttonStart)
        val but2: Button = findViewById(R.id.buttonStart2)
        val helloText: TextView = findViewById(R.id.hellotext)
        but1.setOnClickListener {
            val intent = Intent(this, doctors::class.java)
            startActivity(intent)
        }
        but2.setOnClickListener {
            val intent = Intent(this, cheek2_activity::class.java)
            startActivity(intent)
        }
    }
}