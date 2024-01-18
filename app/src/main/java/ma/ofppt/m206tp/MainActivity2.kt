package ma.ofppt.m206tp

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import ma.ofppt.m206tp.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var googleSignIn: ActivityResultLauncher<Intent>
    lateinit var account: GoogleSignInAccount
    lateinit var binding: ActivityMain2Binding
    lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email
            binding.name.setText(personName)
            binding.email.setText(personEmail)
        }
        binding.signout.setOnClickListener {
            signOut()
        }
    }
    fun signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(OnCompleteListener<Void?> {
            finish()
            startActivity(Intent(this@MainActivity2, MainActivity::class.java))
        })
    }
}