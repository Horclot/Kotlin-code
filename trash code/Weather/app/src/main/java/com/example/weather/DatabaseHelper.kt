import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "AuthDatabase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_SELECTED_CITY = "selected_city"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USERNAME TEXT,
                $COLUMN_SELECTED_CITY TEXT
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Метод для добавления выбранного города в базу данных
    fun addSelectedCity(city: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_SELECTED_CITY, city)
        db.insert(TABLE_NAME, null, contentValues)
    }
    fun clearData() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
    }

    // Метод для получения выбранного города из базы данных
    fun getSelectedCity(): String? {
        val db = this.readableDatabase
        val query = "SELECT $COLUMN_SELECTED_CITY FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        // Проверьте, существует ли столбец COLUMN_SELECTED_CITY в результирующем наборе
        if (cursor != null && cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(COLUMN_SELECTED_CITY)
            if (columnIndex != -1) {
                return cursor.getString(columnIndex)
            }
        }

        return null
    }
}