package com.example.appgeniushub

import Data.UserData
import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val button: Button = findViewById(R.id.buttonContinue)
        val name: EditText = findViewById(R.id.RegName)
        val email: EditText = findViewById(R.id.RegEmail)
        val password: EditText = findViewById(R.id.RegPassword)

        button.setOnClickListener {
            val emailText = email.text.toString().trim()

            if (name.text.toString().isNotEmpty() &&
                emailText.isNotEmpty() &&
                password.text.toString().isNotEmpty() &&
                (emailText.endsWith("@gmail.com") ||
                        emailText.endsWith("@mail.ru") ||
                        emailText.endsWith("@internet.ru") ||
                        emailText.endsWith("@bk.ru") ||
                        emailText.endsWith("@inbox.ru") ||
                        emailText.endsWith("@list.ru")
                        )
            ) {

                val dbHelper = DatabaseHelper(this)
                val emailText = email.text.toString()
                val nameText = name.text.toString()
                val passwordText = password.text.toString()

                // Проверка уникальности email и name перед вставкой
                if (!dbHelper.isEmailExists(emailText) && !dbHelper.isNameExists(nameText)) {
                    val userId = dbHelper.insertUser(nameText, emailText, passwordText)
                    var id: Int = userId.toInt()
                    if (userId != -1L && userId != -2L) {

                        val userData = UserData(nameText, emailText, passwordText, id)

                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("userData", userData)
                        startActivity(intent)
                        finish()
                    } else if (userId == -1L) {
                        Toast.makeText(this, "Ошибка: дублирование email", Toast.LENGTH_SHORT).show()
                    } else if (userId == -2L) {
                        Toast.makeText(this, "Ошибка: дублирование name", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Ошибка заполнения базы данных", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Ошибка заполнения базы данных: дублирование email или name", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Неверный формат данных", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onTextClick(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
