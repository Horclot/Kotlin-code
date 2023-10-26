package com.example.horclotapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.ArrayAdapter

class cheek2_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheek2)
        val Lisst: ListView = findViewById(R.id.Lissst)
        val but: Button = findViewById(R.id.button_reg4)

        // Получение списка записей к врачу
        val dbHelper = DBList(this)
        val appointmentList = dbHelper.getAllDoctorAppointments()

        // Отобразите appointmentList в вашем ListView с использованием собственного адаптера
        val adapter = DoctorAppointmentAdapter(this, R.layout.list_item_layout, appointmentList)
        Lisst.adapter = adapter

        but.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}