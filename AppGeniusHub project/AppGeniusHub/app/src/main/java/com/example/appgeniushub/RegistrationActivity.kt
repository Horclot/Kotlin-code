package com.example.appgeniushub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val button : Button = findViewById(R.id.buttonContinue)
        val textButton : TextView = findViewById(R.id.loginText)
        val name: EditText = findViewById(R.id.RegName)
        val email: EditText = findViewById(R.id.RegEmail)
        val password: EditText = findViewById(R.id.RegPassword)

        button.setOnClickListener {

            val emailText = email.text.toString().trim() // Удалить начальные и конечные пробелы

            if (name.text.toString().isNotEmpty() &&
                emailText.isNotEmpty() &&
                password.text.toString().isNotEmpty() &&
                (emailText.endsWith("@gmail.com") ||
                        emailText.endsWith("@mail.ru") ||
                        emailText.endsWith("@internet.ru") ||
                        emailText.endsWith("@bk.ru") ||
                        emailText.endsWith("@inbox.ru") ||
                        emailText.endsWith("@list.ru"))) {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Закрыть активити регистрации

            } else {
                Toast.makeText(this, "Неверный формат данных", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun onTextClick()
    {
        val intent = Intent(this, MainActivity::class.java) //переход на окно навигации(ИСПРАВИТЬ!!!!)
        startActivity(intent)
        finish() // Закрыть активити регистрации
    }
}