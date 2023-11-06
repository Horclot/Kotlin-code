package com.example.horclotapps

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.provider.MediaStore
import android.content.ContentValues
import android.content.Intent
import android.provider.MediaStore.Images
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class cheek_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheek)

        val screenshotButton = findViewById<Button>(R.id.button_reg2)
        screenshotButton.isEnabled = true


        var dataBase = intent.getStringExtra("data_base")
        var doctors = intent.getStringExtra("doctors")
        var dataBase1: String = dataBase.toString()
        var doctors1 = doctors.toString()

        var cheekText: TextView = findViewById(R.id.chek)
        var TextNul: TextView = findViewById(R.id.nul)
        ("------------------------------------------------\n" +
                "Регистрация прошла успешно!\n" +
                "------------------------------------------------\n" +
                "Номер чека: 195412\n" +
                "Дата и время записи: $dataBase1\n" +
                "Врач: $doctors1\n" +
                "Номер чека: 123456789\n" +
                "------------------------------------------------\n" +
                "Детали услуги\n" +
                "------------------------------------------------\n" +
                "1. Прием врача: 45 BYN\n" +
                "2. Лечение: 70 BYN\n" +
                "3. Предварительные анализы: 30 BYN\n" +
                "------------------------------------------------\n" +
                "Итоговая сумма: 145 BYN\n" +
                "------------------------------------------------\n" +
                "Спасибо за посещение нашей поликлиники!").also {
            cheekText.text = it
            TextNul.text = ""
            val TCheeek: TextView = findViewById(R.id.textCheeek)
            TCheeek.text = "Чек"

            screenshotButton.setOnClickListener {
                val intent = Intent(this, cheek2_activity::class.java)
                intent.putExtra("cheek1", dataBase)
                intent.putExtra("cheek2", doctors1)
                screenshotButton.isEnabled = false
                takeScreenshot()

                val dbHelper = DBList(this)

// Получение базы данных для записи
                val db = dbHelper.writableDatabase

// Данные для новой записи
                val doctorName = intent.getStringExtra("cheek2") // Извлекаем имя врача из Intent
                val appointmentDate = intent.getStringExtra("cheek1") // Извлекаем дату записи из Intent

// Создание объекта ContentValues и добавление данных
                val values = ContentValues().apply {
                    put(DBList.COLUMN_DOCTOR_NAME, doctorName)
                    put(DBList.COLUMN_APPOINTMENT_DATE, appointmentDate)
                }

// Вставка новой записи в таблицу
                val newRowId = db.insert(DBList.TABLE_APPOINTMENTS, null, values)
            }
        }
    }

    private fun takeScreenshot() {
        val rootView = window.decorView.findViewById<View>(android.R.id.content)
        val screenshot = getScreenShot(rootView)

        if (screenshot != null) {
            // Сохраняем скриншот в галерее устройства
            saveScreenshotToGallery(screenshot)

            // Выводим Toast об успешном сохранении скриншота
            Toast.makeText(this, "Скриншот добавлен в галерею", Toast.LENGTH_LONG).show()

            // Запускаем сопрограмму для перехода через 3 секунды
            GlobalScope.launch(Dispatchers.Main) {
                delay(3000) // Задержка в 3 секунды
                navigateToMainActivity()
            }
        } else {
            // В случае ошибки выводим сообщение
            Toast.makeText(this, "Ошибка при создании скриншота", Toast.LENGTH_LONG).show()
        }
    }

    private fun getScreenShot(view: View): Bitmap? {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun saveScreenshotToGallery(bitmap: Bitmap) {
        val imageFileName = "Screenshot_" + System.currentTimeMillis() + ".png"
        val contentValues = ContentValues().apply {
            put(Images.Media.DISPLAY_NAME, imageFileName)
            put(Images.Media.MIME_TYPE, "image/png")
        }

        val resolver = contentResolver
        val imageUri = resolver.insert(Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        imageUri?.let {
            resolver.openOutputStream(it)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@cheek_activity, MenuActivity::class.java)
        startActivity(intent)
        finish() // Закрываем текущую активность
    }
}
