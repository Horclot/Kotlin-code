package com.example.horclotapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val userLogin: EditText = findViewById(R.id.user_login_auth)
        val userPass: EditText = findViewById(R.id.user_password_auth)
        val button: Button = findViewById(R.id.button_auth)
        val dad: EditText = findViewById(R.id.dad)
        val linkToReg: TextView = findViewById(R.id.link_to_reg)
        val email2 = dad.text.toString().trim()

        linkToReg.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val login1 = userLogin.text.toString().trim()
            val pass1 = userPass.text.toString().trim()

            if (login1 == "" ||
                pass1 == ""){
                // Вывод сообщения об ошибке
                Toast.makeText(this, "Пожалуйста, проверьте введенные данные", Toast.LENGTH_LONG).show()
            } else {
                val db = DbHelper(this, null)
                val isAuth = db.getUser(login1, pass1)

                if (isAuth) {
                    Toast.makeText(this, "Пользователь $login1 авторизован", Toast.LENGTH_LONG).show()
                    userLogin.text.clear()
                    userPass.text.clear()
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Пользователь $login1 не авторизован", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}