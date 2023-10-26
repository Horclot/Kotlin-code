package com.example.encoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class decryptor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decryptor)

        val encryptedText: EditText = findViewById(R.id.editTextText11)
        val userKey: EditText = findViewById(R.id.editTextText12)
        val decryptorButton: Button = findViewById(R.id.button13)
        val DecryptedText: TextView = findViewById(R.id.textAnswer1)

        decryptorButton.setOnClickListener {
            val inputText = encryptedText.text.toString()
            val key = userKey.text.toString()

            val decryptedText = decrypt(inputText, key)

            // Отображайте расшифрованный текст в DecryptedText
            DecryptedText.text = "($decryptedText)"
        }
    }
//оиД иаянув мясо
    // Функция для расшифровки текста методом простой одиночной перестановки
    private fun decrypt(text: String, key: String): String {
        val decryptedText = CharArray(text.length)

        for (i in text.indices) {
            val keyIndex = i % key.length
            val newIndex = (i + key.length - keyIndex) % text.length
            decryptedText[newIndex] = text[i]
        }

        return String(decryptedText)
    }
}
