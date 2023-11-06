package com.example.trpo_labs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonDoublePermutation: Button = findViewById(R.id.button)
        val buttonOption : Button = findViewById(R.id.button2)
        buttonDoublePermutation.setOnClickListener {
            val intent = Intent(this, duble_permutation::class.java)
            startActivity(intent)
        }
        buttonOption.setOnClickListener {
            val intent = Intent(this, Option6::class.java)
            startActivity(intent)
        }
    }
}