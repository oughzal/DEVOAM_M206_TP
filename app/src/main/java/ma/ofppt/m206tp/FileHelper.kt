package ma.ofppt.m206tp

import android.app.Application
import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FileHelper {
    companion object {
        fun writeFileInternal(context: Context, fileName: String, content: String) {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(content.toByteArray())
            }
        }

        fun readFileInternal(context: Context, fileName: String): String {
            return context.openFileInput(fileName).bufferedReader().use { it.readText() }
        }

        fun writeFileExternal(context: Context, fileName: String, content: String) {
            val file = File(context.getExternalFilesDir(null), fileName)
            FileOutputStream(file).use {
                it.write(content.toByteArray())
            }
        }

        fun readFileExternal(context: Context, fileName: String): String {
            val file = File(context.getExternalFilesDir(null), fileName)
            return FileInputStream(file).bufferedReader().use { it.readText() }
        }

        fun writeFileCache(context: Context, fileName: String, content: String) {
            val file = File(context.cacheDir, fileName)
            FileOutputStream(file).use {
                it.write(content.toByteArray())
            }
        }

        fun readFileCache(context: Context, fileName: String): String {
            val file = File(context.cacheDir, fileName)
            return FileInputStream(file).bufferedReader().use { it.readText() }
        }

        fun readFileAssets(context: Context, fileName: String): String {
            context.assets.open(fileName).bufferedReader().use { return it.readText() }
        }

        fun readFileResources(context: Context, resId: Int): String {
            context.resources.openRawResource(resId).bufferedReader().use { return it.readText() }
        }

    }
}


