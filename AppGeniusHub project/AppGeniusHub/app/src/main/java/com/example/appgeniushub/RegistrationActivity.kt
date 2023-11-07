package com.example.appgeniushub

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi


class RegistrationActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
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
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
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
