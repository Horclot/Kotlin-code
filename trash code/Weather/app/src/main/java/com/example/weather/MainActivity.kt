package com.example.weather

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    public lateinit var weather: String
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)

        val belarusCities = arrayOf(
            "moscow",
            "Брест",
            "Гомель",
            "Гродно",
            "Могилев"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, belarusCities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinnerCity: Spinner = findViewById(R.id.spinner)
        spinnerCity.adapter = adapter

        val accept: Button = findViewById(R.id.accept)

        accept.setOnClickListener {
            val selectedCityPosition = spinnerCity.selectedItemPosition
            weather = belarusCities[selectedCityPosition]

            // Очистить базу данных и записать новый выбранный город
            dbHelper.clearData()
            dbHelper.addSelectedCity(weather)

            // Перейти на следующую активность (MenuActivity)
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("DataKey", weather)
            startActivity(intent)
        }

        val hintTextView: TextView = findViewById(R.id.hintTextView)
        hintTextView.setOnClickListener { usePreviousParameters() }
    }

    fun usePreviousParameters() {
        // Получите выбранный город из базы данных
        val selectedCity = dbHelper.getSelectedCity()

        if (selectedCity != null && selectedCity.isNotEmpty()) {
            // Город был выбран ранее, перейдите на активность меню с предыдущими параметрами
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("previousCity", selectedCity)
            startActivity(intent)
        }
    }
}


