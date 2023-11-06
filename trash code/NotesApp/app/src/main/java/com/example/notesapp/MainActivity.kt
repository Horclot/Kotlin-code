package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textView:TextView = findViewById(R.id.textView)
        val button:Button = findViewById(R.id.button)
        val listView:ListView = findViewById(R.id.List)
        button.setOnClickListener {
            val intent = Intent(this, Notes::class.java)
            startActivity(intent)
        }

        //val data = mutableListOf<String>()
        //for (item1 in item1) {
        //    data.add(item1)
        //}

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Notes::class.java)
            startActivity(intent)
        }


    }
}