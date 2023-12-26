package ma.ofppt.roomdb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "prenom")
    val firstName : String,
    @ColumnInfo(name = "nom")
    val lastName : String
    )