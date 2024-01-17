package ma.ofppt.m206tp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import ma.ofppt.m206tp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var googleSignIn: ActivityResultLauncher<Intent>
    lateinit var account: GoogleSignInAccount
    lateinit var binding: ActivityMainBinding
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var startActivityForResult: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO : initialiser les variables
        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN
            )
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        googleSignIn =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                run {
                    if (result.resultCode == RESULT_OK) {
                        val data: Intent? = result.getData()
                        val task: Task<GoogleSignInAccount> =
                            GoogleSignIn.getSignedInAccountFromIntent(data)
                        handleSignInResult(task)
                    }
                }


                binding.googleBtn.setOnClickListener {
                    signIn()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }

    private fun updateUI(account: GoogleSignInAccount?) {

    }

    fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
        googleSignIn.launch(signInIntent)
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount =
                task.getResult(ApiException::class.java)
            updateUI(account)
        } catch (e: ApiException) {
            Log.w("TAG", "signInResult:failed code=" + e.statusCode)
        }
    }

}
