package ma.ofppt.m206tp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDB(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "UsersDatabase.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "User"
        const val COLUMN_ID = "id"
        const val COLUMN_FIRSTNAME = "firstname"
        const val COLUMN_LASTNAME = "lastname"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val createTableStatement = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_FIRSTNAME TEXT,
                $COLUMN_LASTNAME TEXT
            )
            """.trimIndent()
        db.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Mettre à jour la base de données avec une nouvelle version
    }
    fun addUser(firstname: String, lastname: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_FIRSTNAME, firstname)
        cv.put(COLUMN_LASTNAME, lastname)

        val insert = db.insert(TABLE_NAME, null, cv)
        db.close()
        return insert != -1L
    }

    fun getAllUsers(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun updateUser(id: Int, firstname: String, lastname: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_FIRSTNAME, firstname)
        cv.put(COLUMN_LASTNAME, lastname)

        val update = db.update(TABLE_NAME, cv, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
        return update > 0
    }

    fun deleteUser(id: Int): Boolean {
        val db = this.writableDatabase
        val delete = db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
        return delete > 0
    }
}