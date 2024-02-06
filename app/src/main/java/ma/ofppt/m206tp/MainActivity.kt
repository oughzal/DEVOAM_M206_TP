package ma.ofppt.m206tp

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ma.ofppt.m206tp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // créer sharedPreference
        sharedPreferences = application.getSharedPreferences("MyShared", Context.MODE_PRIVATE)

        // TODO :lire les valeurs
        val color = sharedPreferences.getInt("color",Color.BLUE)

        //utiliser les valeur
        binding.txtContent.setTextColor(color)
        when(color){
            Color.BLACK -> binding.black.isChecked = true
            Color.BLUE -> binding.blue.isChecked = true
            Color.RED -> binding.red.isChecked = true
            Color.GREEN -> binding.green.isChecked = true
        }
        binding.txtColor.setOnCheckedChangeListener { group, checkedId ->
            var color = when (checkedId) {
                R.id.black -> Color.BLACK
                R.id.blue -> Color.BLUE
                R.id.red -> Color.RED
                R.id.green -> Color.GREEN
                else -> 0
            }
            when(color){
                Color.BLACK -> binding.black.setTextColor(color)
                Color.BLUE -> binding.blue.setTextColor(color)
                Color.RED -> binding.red.setTextColor(color)
                Color.GREEN -> binding.green.setTextColor(color)
            }
            //TODO Ecrire les valeurs
            val editor = sharedPreferences.edit()
            editor.putInt("color",color)
            editor.commit()
            binding.txtContent.setTextColor(color)
        }

    }
}