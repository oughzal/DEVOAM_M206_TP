package ma.ofppt.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ma.ofppt.roomdb.dao.UserDAO
import ma.ofppt.roomdb.model.User
import kotlin.concurrent.Volatile

@Database(entities = [User::class], version = 1)
abstract class UserDB : RoomDatabase() {
    abstract  fun userDao(): UserDAO
     companion object{
         @Volatile
         var INSTANCE : UserDB? = null
         fun getInstance(context: Context): UserDB? {
             if(INSTANCE != null) return INSTANCE as UserDB
             synchronized(this){
                 INSTANCE = Room.databaseBuilder(context.applicationContext,UserDB::class.java,"user.db").build()
             }
             return INSTANCE
         }
     }
}