package com.example.trpo_labs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import kotlin.math.min

class duble_permutation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duble_permutation)
        val userText: EditText = findViewById(R.id.editTextText1)
        val userKey: EditText = findViewById(R.id.editTextText12)
        val encryption: Button = findViewById(R.id.button13)
        val textOutput: TextView = findViewById(R.id.textView2)
        val spinner: Spinner = findViewById(R.id.spinner)
        val solutions = arrayOf("блочная одинарная перестановка",
            "табличная маршрутная перестановка 5x2",
            "вертикальная перестановка",
            "двойная перестановка")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, solutions)
        spinner.adapter = adapter

        encryption.setOnClickListener {
            val selectedSolution = spinner.selectedItem.toString()
            val inputText = userText.text.toString()

            when (selectedSolution) {
                "блочная одинарная перестановка" -> {
                    val key = userKey.text.toString()
                    if (key.toSet().all { it.isLetter() }) {
                        val encryptedText = encryptTextBlockSinglePermutation(inputText, key)
                        textOutput.text = encryptedText
                    } else {
                        textOutput.text = "Некорректный ключ"
                    }
                }
                "табличная маршрутная перестановка" -> {
                    val numColumns = 2 // Здесь указываем количество столбцов
                    val encryptedText = encryptTextTableRoutePermutation(inputText)
                    textOutput.text = encryptedText
                }
                "вертикальная перестановка" -> {
                    val key = userKey.text.toString()
                    if (key.toSet().all { it.isDigit() } && key.length % inputText.length == 0) {
                        val encryptedText = encryptTextVerticalPermutation(inputText, key)
                        textOutput.text = encryptedText
                    } else {
                        textOutput.text = "Некорректный ключ"
                    }
                }
                "двойная перестановка" -> {
                    val key = userKey.text.toString()
                    if (key.toSet().all { it.isDigit() } && key.length % inputText.length == 0) {
                        val encryptedText = encryptTextDoublePermutation(inputText, key)
                        textOutput.text = encryptedText
                    } else {
                        textOutput.text = "Некорректный ключ"
                    }
                }
                else -> {
                    textOutput.text = "Выберите вид шифрования."
                }
            }
        }
    }

    private fun encryptTextBlockSinglePermutation(inputText: String, key: String): String {
        val textLength = inputText.length
        val keyLength = key.length

        if (textLength == 0 || keyLength == 0) {
            return "Введите текст и ключ"
        }

        val minLen = min(textLength, keyLength)

        val charMap = key.withIndex().associate { it.value to it.index }

        val encryptedText = CharArray(textLength)
        for (i in 0 until minLen) {
            val charInText = inputText[i]
            val newIndex = charMap[charInText]
            if (newIndex != null) {
                encryptedText[i] = key[newIndex]
            } else {
                // Если символ не найден в ключе, оставляем его без изменений
                encryptedText[i] = charInText
            }
        }

        // Если длина текста больше ключа, оставшаяся часть текста добавляется как есть
        for (i in minLen until textLength) {
            encryptedText[i] = inputText[i]
        }

        return String(encryptedText)
    }


    private fun encryptTextTableRoutePermutation(inputText: String): String {
        val numRows = 2
        val numColumns = 5
        val totalCells = numRows * numColumns

        val fullText = if (inputText.length < totalCells) {
            inputText.padEnd(totalCells, '$')
        } else {
            inputText.substring(0, totalCells)
        }

        val table = Array(numRows) { CharArray(numColumns) }
        var index = 0

        for (row in 0 until numRows) {
            for (col in 0 until numColumns) {
                table[row][col] = fullText[index]
                index++
            }
        }

        val encryptedText = StringBuilder()

        for (col in 0 until numColumns) {
            for (row in 0 until numRows) {
                encryptedText.append(table[row][col])
            }
        }

        return encryptedText.toString()
    }



    private fun encryptTextVerticalPermutation(inputText: String, key: String): String {
        val textLength = inputText.length
        val keyLength = key.length

        if (textLength == 0 || keyLength == 0) {
            return "Введите текст и ключ"
        }

        val rows = textLength / keyLength
        val columns = keyLength

        if (textLength % keyLength != 0) {
            return "Длина ключа должна делить длину текста без остатка"
        }

        val grid = Array(rows) { CharArray(columns) }

        var index = 0
        for (row in 0 until rows) {
            for (col in 0 until columns) {
                grid[row][col] = inputText[index]
                index++
            }
        }

        val sortedKeyIndices = key.indices.sortedBy { key[it] }
        val result = StringBuilder()

        for (col in sortedKeyIndices) {
            for (row in 0 until rows) {
                result.append(grid[row][col])
            }
        }

        return result.toString()
    }

    private fun encryptTextDoublePermutation(inputText: String, key: String): String {
        val singlePermutationResult = encryptTextBlockSinglePermutation(inputText, key)
        return encryptTextVerticalPermutation(singlePermutationResult, key)
    }
}
