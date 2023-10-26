package com.example.horclotapps

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class DBList(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "DoctorAppointments.db"
        const val TABLE_APPOINTMENTS = "appointments"
        const val COLUMN_ID = "_id"
        const val COLUMN_DOCTOR_NAME = "doctor_name"
        const val COLUMN_APPOINTMENT_DATE = "appointment_date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = """
            CREATE TABLE $TABLE_APPOINTMENTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_DOCTOR_NAME TEXT,
                $COLUMN_APPOINTMENT_DATE TEXT
            )
        """
        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Обновление базы данных при необходимости
    }

    fun addDoctorAppointment(doctorName: String, appointmentDate: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_DOCTOR_NAME, doctorName)
            put(COLUMN_APPOINTMENT_DATE, appointmentDate)
        }

        val db = writableDatabase
        return db.insert(TABLE_APPOINTMENTS, null, values)
    }

    fun getAllDoctorAppointments(): List<DoctorAppointment> {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_APPOINTMENTS,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val appointmentList = mutableListOf<DoctorAppointment>()

        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val doctor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOCTOR_NAME))
            val date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APPOINTMENT_DATE))

            val appointment = DoctorAppointment(doctor, date)
            appointmentList.add(appointment)
        }

        cursor.close()
        return appointmentList
    }
}