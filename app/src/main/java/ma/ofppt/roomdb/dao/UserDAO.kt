package ma.ofppt.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ma.ofppt.roomdb.model.User

@Dao
interface UserDAO {
    @Query("SELECT * from User")
    suspend fun getUsers():List<User>

    @Query("SELECT * from User where id=:id")
    suspend fun getUser(id : Int):User

    @Insert
    suspend fun insertUser(user:User)

    @Update
    suspend fun updateUser(user:User)

    @Delete
    suspend fun deleteUser(user: User)
}