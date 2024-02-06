package ma.ofppt.m206tp

import android.app.Application
import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FileHelper2 {
    companion object {
        fun getFileInternal(context: Context, fileName: String): String {
            return context.openFileInput(fileName).bufferedReader().use {
                it.readText()
            }

        }

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
            //file.writeText(content, Charsets.UTF_8)
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
            return FileInputStream(file).bufferedReader().use {
                it.readText()
            }
        }

        fun readFileAssets(context: Context, fileName: String): String {
            context.assets.open(fileName).bufferedReader().use { return it.readText() }
        }

        fun readFileResources(context: Context, resId: Int): String {
            context.resources.openRawResource(resId).bufferedReader().use { return it.readText() }
        }

        fun saveFile(app: Application, content: String) {
            val file = File(app.filesDir, "ficonfig.txt")
            file.writeText(content)
        }

        fun readFile(app: Application): String {
            val file = File(app.filesDir, "file1.txt")
            return if(file.exists()) file.readText() else ""
         }

        fun saveCacheFile(app: Application, content: String) {
            val file = File(app.cacheDir, "file1.txt")
            file.writeText(content)
        }

        fun readCacheFile(app: Application): String {
            val file = File(app.cacheDir, "file1.txt")
            return if(file.exists()) file.readText() else ""
        }
        fun saveExternalFile(app: Application, content: String) {
            val file = File(app.getExternalFilesDir("Dossierjui"), "file1.txt")
            file.writeText(content)
        }
        fun readExternal(app: Application):String{
            val file = File(app.getExternalFilesDir("Dossier"),"file1")
            return if(file.exists()) file.readText() else ""
        }

    }
}


