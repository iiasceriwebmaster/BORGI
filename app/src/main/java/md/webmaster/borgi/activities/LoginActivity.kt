package md.webmaster.borgi.activities

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import md.webmaster.borgi.R
import md.webmaster.borgi.data.BorgiDatabase
import md.webmaster.borgi.databinding.ActivityLoginBinding
import md.webmaster.borgi.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var appDatabase: BorgiDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking {
            appDatabase = BorgiDatabase.getInstance(applicationContext)
            userViewModel = UserViewModel(appDatabase.userDao(), appDatabase.debtDao())
            withContext(Dispatchers.IO) {
                val userEntities = userViewModel.getAll()
                if (userEntities.isNotEmpty()) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textValue = binding.forgotPasswordTV.text.toString()
        binding.forgotPasswordTV.apply {
            text = textValue // Set your text here
            paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }

        binding.signupTV.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            intent.putExtra("title", resources.getString(R.string.sign_up))
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.email.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.password.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.dontHaveAccTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.signupTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.loginBtn.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.forgotPasswordTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
    }
}