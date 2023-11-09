package com.example.appgeniushub

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button : Button = findViewById(R.id.buttonContinue)
        val textButton : TextView = findViewById(R.id.loginText)
        val email: EditText = findViewById(R.id.LoginEmail)
        val password: EditText = findViewById(R.id.LoginPassword)

        button.setOnClickListener {
            val emailText = email.text.toString().trim()

            if (emailText.isNotEmpty() &&
                password.text.toString().isNotEmpty() &&
                (emailText.endsWith("@gmail.com") ||
                        emailText.endsWith("@mail.ru") ||
                        emailText.endsWith("@internet.ru") ||
                        emailText.endsWith("@bk.ru") ||
                        emailText.endsWith("@inbox.ru") ||
                        emailText.endsWith("@list.ru")
                        )
            ) {
                val userPasswordToCheck = password.text.toString()

                val dbHelper = DatabaseHelper(this)
                val userExists = dbHelper.checkUser(emailText, userPasswordToCheck)

                if (userExists) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Неверный email или пароль", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Неверный формат данных", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onTextClick1(view: View) {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
        finish()
    }
}
