package md.webmaster.borgi

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import md.webmaster.borgi.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.email.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.password.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.dontHaveAccTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.signupTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.loginBtn.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.forgotPasswordTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
    }
}