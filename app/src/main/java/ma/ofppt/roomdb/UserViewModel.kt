package ma.ofppt.roomdb

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ma.ofppt.roomdb.model.User

class UserViewModel(application: Application) : AndroidViewModel(application) {
    init {
        getUsers()
    }
    val userDao by lazy {  UserDB.getInstance(application.applicationContext)?.userDao()}
    var userList = MutableLiveData<List<User>>()

    fun getUsers(){
        GlobalScope.launch(Dispatchers.IO) {
          val users =  userDao?.getUsers() ?: emptyList()

          userList.postValue(users)
        }
    }

}