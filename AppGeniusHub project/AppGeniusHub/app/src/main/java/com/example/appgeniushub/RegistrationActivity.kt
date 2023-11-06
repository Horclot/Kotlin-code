package com.example.appgeniushub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var button : Button = findViewById(R.id.buttonContinue)
        var textbutton : TextView = findViewById(R.id.loginText)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Закрыть активити регистрации
        }


        fun onTextClick(view: View)
        {
            Toast.makeText(this, "Текст был нажат", Toast.LENGTH_SHORT).show()
        }

    }
}