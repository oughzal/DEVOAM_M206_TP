package ma.ofppt.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ma.ofppt.roomdb.model.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userDao = UserDB.getInstance(applicationContext)?.userDao()
        GlobalScope.launch {
            if (userDao != null) {
                userDao.insertUser(User(1,"prenom","nom"))
            }
        }
        //
    }
}