package ma.ofppt.m206tp

import android.content.Context
import java.io.File

class FileHelper {
    companion object{

        // TODO : Read from Internal file
        fun readIntarnalFile(context: Context):String{
            val file = File(context.filesDir,"content.txt")
            if(file.exists()){
                return file.readText()
            }else{
                return ""
            }
        }
        // TODO : Write to Internal file
        fun writeInternalFile(context: Context,content : String){
            val file = File(context.filesDir,"content.txt")
            file.writeText(content)
        }

        // TODO : Read from cache file
        fun readCacheFile(context: Context):String{
            val file = File(context.cacheDir,"content.txt")
            if(file.exists()){
                return file.readText()
            }else{
                return ""
            }
        }
        // TODO : Write to cache file
        fun writeCacheFile(context: Context,content : String){
            val file = File(context.cacheDir,"content.txt")
            file.writeText(content)
        }
        // TODO : Read from external file
        fun readExternalFile(context: Context):String{
            val file = File(context.getExternalFilesDir("DEV"),"content.txt")
            if(file.exists()){
                return file.readText()
            }else{
                return ""
            }
        }
        // TODO : Write to external file
        fun writeExternalFile(context: Context,content : String){
            val file = File(context.getExternalFilesDir("DEV"),"content.txt")
            file.writeText(content)
        }
        fun readFileAssets(context: Context, fileName: String): String {
            context.assets.open(fileName).bufferedReader().use { return it.readText() }
        }

        fun readFileResources(context: Context, resId: Int): String {
            context.resources.openRawResource(resId).bufferedReader().use { return it.readText() }
        }
    }
}