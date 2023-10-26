package com.example.encoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class encryptor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encryptor)

        val userText: EditText = findViewById(R.id.editTextText11)
        val userKey: EditText = findViewById(R.id.editTextText12)
        val encryptorButton: Button = findViewById(R.id.button13)
        val Answer: TextView = findViewById(R.id.textAnswer1)

        encryptorButton.setOnClickListener {
            val inputText = userText.text.toString()
            val key = userKey.text.toString()

            val encryptedText = encrypt(inputText, key)

            // Отображайте зашифрованный текст в Answer
            Answer.text = "($encryptedText)"
        }
    }

    // Функция для шифрования текста методом простой одинарной перестановки
    private fun encrypt(text: String, key: String): String {
        val encryptedText = CharArray(text.length)

        for (i in text.indices) {
            val keyIndex = i % key.length
            val newIndex = (i + key[keyIndex].toInt()) % text.length
            encryptedText[newIndex] = text[i]
        }

        return String(encryptedText)
    }
}