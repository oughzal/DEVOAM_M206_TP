package ma.ofppt.m206tp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ma.ofppt.m206tp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedpreferences = application.getSharedPreferences("myShared",Context.MODE_PRIVATE)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var number = sharedpreferences.getInt("number",0)
        binding.txtNumber.text = "$number"
        binding.btnAddOne.setOnClickListener {
            var number = sharedpreferences.getInt("number",0)
            var x = sharedpreferences.getFloat("x",0f)
            number++
            val editor = sharedpreferences.edit()
            editor.putInt("number",number)
            editor.putString("name","nom 1")
            editor.putFloat("x",4f)
            editor.commit()
            binding.txtNumber.text = "$number"
        }
        binding.btnParameters.setOnClickListener {
            val intent = Intent(this,SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}