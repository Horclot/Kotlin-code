package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileNotFoundException
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    val shift = 3 // Значение сдвига для шифра Цезаря
    val passwordPattern = Pattern.compile("^[a-zA-Z0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?]+\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        val buttonPass: Button = findViewById(R.id.button)
        val buttonNewPass: Button = findViewById(R.id.button2)
        val buttonViewPasswords: Button = findViewById(R.id.button3)
        val password: EditText = findViewById(R.id.editTextText)
        val accept: TextView = findViewById(R.id.textView2)

        buttonPass.setOnClickListener {
            val passwordToCheck = password.text.toString()

            val fileName = "passLab3.txt"
            val directory = File(context.filesDir, "LabsPass")
            val file = File(directory, fileName)

            if (file.exists()) {
                // Чтение из файла
                val fileContents = try {
                    file.readText()
                } catch (e: FileNotFoundException) {
                    ""
                }

                val passwords = caesarDecrypt(fileContents, shift).split("\n")

                // Проверка на наличие пароля
                if (passwordToCheck in passwords) {
                    accept.text = "Успешно!"
                } else {
                    accept.text = "Пароль не найден!"
                }
            } else {
                accept.text = "Пароль не найден!"
            }
        }

        buttonNewPass.setOnClickListener {
            val passwordToSave = password.text.toString()

            if (!passwordPattern.matcher(passwordToSave).matches()) {
                accept.text = "Недопустимый пароль!"
                return@setOnClickListener
            }

            val fileName = "passLab3.txt"
            val directory = File(context.filesDir, "LabsPass")
            val file = File(directory, fileName)

            if (!directory.exists()) {
                directory.mkdirs()
            }

            if (file.exists()) {
                // Чтение из файла
                val fileContents = try {
                    file.readText()
                } catch (e: FileNotFoundException) {
                    ""
                }

                val passwords = caesarDecrypt(fileContents, shift).split("\n")

                // Проверка на наличие пароля
                if (passwordToSave in passwords) {
                    accept.text = "Пароль уже существует!"
                } else {
                    // Добавление пароля и запись в файл
                    val encryptedPassword = caesarEncrypt(passwordToSave, shift)
                    val updatedContents = fileContents + encryptedPassword + "\n"
                    file.writeText(updatedContents)
                    accept.text = "Пароль успешно зарегистрирован!"
                }
            } else {
                // Файл не существует, записываем новый пароль
                val encryptedPassword = caesarEncrypt(passwordToSave, shift)
                file.writeText(encryptedPassword + "\n")
                accept.text = "Пароль успешно зарегистрирован!"
            }
        }

        buttonViewPasswords.setOnClickListener {
            val fileName = "passLab3.txt"
            val directory = File(context.filesDir, "LabsPass")
            val file = File(directory, fileName)

            if (file.exists()) {
                val fileUri = FileProvider.getUriForFile(context, context.packageName + ".provider", file)

                val viewIntent = Intent(Intent.ACTION_VIEW)
                viewIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                viewIntent.setDataAndType(fileUri, "text/plain")

                try {
                    startActivity(viewIntent)
                } catch (e: ActivityNotFoundException) {
                    accept.text = "Нет приложения для просмотра файла."
                }
            } else {
                accept.text = "Файл не найден."
            }
        }
    }

    private fun caesarEncrypt(text: String, shift: Int): String {
        val result = StringBuilder()
        for (char in text) {
            if (char.isLetter()) {
                val start = if (char.isLowerCase()) 'a' else 'A'
                result.append((start + (char - start + shift) % 26).toChar())
            } else {
                result.append(char)
            }
        }
        return result.toString()
    }

    private fun caesarDecrypt(text: String, shift: Int): String {
        return caesarEncrypt(text, 26 - shift)
    }
}
