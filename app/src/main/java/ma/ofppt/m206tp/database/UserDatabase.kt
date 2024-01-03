package ma.ofppt.m206tp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ma.ofppt.m206tp.model.User

class UserDatabase(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        const val DATABASE_NAME = "Usersdb.db" // nom du fichier
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "utilisateur"
        const val COLUMN_ID = "id"
        const val COLUMN_FIRSTNAME = "firstname"
        const val COLUMN_LASTNAME = "lastName"

    }

    override fun onCreate(db: SQLiteDatabase) {
        // TODO : creation de la base de données
        val sql = """
            CREATE TABLE $TABLE_NAME(
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_FIRSTNAME TEXT,
                $COLUMN_LASTNAME TEXT
            );
        """.trimIndent()
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // TODO : mise à jour de la base de données

    }

    fun addUser(user : User) : Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_ID,user.id)
        cv.put(COLUMN_FIRSTNAME,user.fisrtName)
        cv.put(COLUMN_LASTNAME,user.lastName)
        val result = db.insert(TABLE_NAME,null,cv)
        return result != -1L
    }
    fun editeUser(user: User):Boolean{
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_FIRSTNAME,user.fisrtName)
        cv.put(COLUMN_LASTNAME,user.lastName)
        val result = db.update(TABLE_NAME,cv, "$COLUMN_ID = ?", arrayOf(user.id.toString()))
        return result > 0
    }

    fun removeUser(user: User):Boolean{
        // DELETE FROM user where id = $user.id
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(user.id.toString()))
        return result > 0
    }
    fun getAllUser():Cursor{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
      fun getUser(id : Int):Cursor{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME where $COLUMN_ID=$id", null)
    }



}