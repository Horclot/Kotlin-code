package com.example.trpo_labs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Option6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option6)
        val userText: EditText = findViewById(R.id.editTextText1)
        val userKey: EditText = findViewById(R.id.editTextText12)
        val encryption: Button = findViewById(R.id.button13)
        val textOutput: TextView = findViewById(R.id.textView2)
        encryption.setOnClickListener {
            if (userText.text.toString() != "ЬЕСОУЬ, ГТСХК_ОА-ТООУ_НАД_ВДОЁЯПЫОВТЩР,  СИСИО_ТШЯЙЖНОЬ_|_ИЕЙ_ТДТ_Н-ОЕЬОО_ЛН_" || userKey.text.toString() != "РАБОТА"){
                textOutput.text = "Ошибка таблицы!"
            }
            else {
                val encryptionText: String =
                    "РЕСПЕКТ, ГОСПОДАМ-ТОВАРИЩАМ, СВИСОК_ДЕРЖИТЕСЬ_СМЕРЧОМ_\nВІЙ_НА_"
                textOutput.text = encryptionText
            }
        }
    }
}
