package com.example.horclotapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView

class doctors : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctors)

        val doctorNames = arrayOf(
            "Хирург",
            "Стоматолог",
            "Офтальмолог",
            "Терапевт",
            "Педиатр",
            "Гинеколог",
            "Уролог",
            "Дерматолог",
            "Оториноларинголог",
            "Невролог",
            "Ортопед",
            "Кардиолог",
            "Психиатр",
            "Гастроэнтеролог",
            "Эндокринолог",
            "Онколог",
            "Ревматолог",
            "Инфекционист",
            "Акушер-гинеколог",
            "Стоматолог-ортодонт"
        )

        val listDoctor = findViewById<ListView>(R.id.list_doctor)
        val data = mutableListOf<String>()

        for (doctorName in doctorNames) {
            data.add(doctorName)
        }
        val doctorAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        listDoctor.adapter = doctorAdapter
        listDoctor.setOnItemClickListener { parent, view, position, id ->
            val SelectedItem2 = data[position]
            Log.d("Selected Item", SelectedItem2)
            val intent = Intent(this, data_activity::class.java)
            intent.putExtra("doctors", SelectedItem2)
            startActivity(intent)
        }

    }
}