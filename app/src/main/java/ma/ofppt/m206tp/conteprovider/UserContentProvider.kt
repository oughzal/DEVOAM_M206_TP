package ma.ofppt.m206tp.conteprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import ma.ofppt.m206tp.database.UserDB


class UserContentProvider : ContentProvider() {
    private lateinit var dbHelper: UserDB

    companion object {
        const val AUTHORITY = "ma.ofppt.m206tp.provider"
        const val PATH_USERS = "users"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH_USERS")
    }
    override fun onCreate(): Boolean {
        dbHelper = UserDB(context!!)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return dbHelper.readableDatabase.query(
            UserDB.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = dbHelper.writableDatabase.insert(UserDB.TABLE_NAME, null, values)
        return Uri.withAppendedPath(CONTENT_URI, id.toString())
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return dbHelper.writableDatabase.update(
            UserDB.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return dbHelper.writableDatabase.delete(
            UserDB.TABLE_NAME,
            selection,
            selectionArgs
        )
    }

    override fun getType(uri: Uri): String? {
        // Implementez selon vos besoins
        return null
    }
}