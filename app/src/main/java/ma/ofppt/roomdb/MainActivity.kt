package ma.ofppt.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ma.ofppt.roomdb.adapter.UserAdapter
import ma.ofppt.roomdb.databinding.ActivityMainBinding
import ma.ofppt.roomdb.model.User

class MainActivity : AppCompatActivity() {
    lateinit var adapter: UserAdapter
    var userList= ArrayList<User>()
    lateinit var binding: ActivityMainBinding
    lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        adapter = UserAdapter(userList)
        binding.recycler.adapter = adapter
        userViewModel.userList.observe(this) {
            userList.clear()
            userList.addAll(it)
            adapter.notifyDataSetChanged()
        }


        //
    }
}