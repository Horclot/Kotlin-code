package com.example.horclotapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class data_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        val text: TextView = findViewById(R.id.hellotext4)
        val selectedDoctor = intent.getStringExtra("doctors")
        text.text = selectedDoctor

        val ListOne = findViewById<ListView>(R.id.ListOne)
        val doctorTime = arrayOf(
            "12:00\n 12.09.2023",
            "14:30\n 15.09.2023",
            "10:15\n 18.09.2023",
            "16:45\n 21.09.2023",
            "09:30\n 25.09.2023",
            "11:20\n 02.10.2023",
            "13:55\n 06.10.2023",
            "17:10\n 11.10.2023",
            "15:40\n 14.10.2023",
            "08:45\n 20.10.2023"
        )
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, doctorTime)
        ListOne.adapter = adapter

        ListOne.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = doctorTime[position]
            Log.d("Selected Item", selectedItem)
            val intent = Intent(this, cheek_activity::class.java)
            intent.putExtra("data_base", selectedItem)
            intent.putExtra("doctors", selectedDoctor)
            startActivity(intent)
        }
    }
}