package md.webmaster.borgi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import md.webmaster.borgi.databinding.ActivityTransferToAccountBinding

class TransferToAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferToAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTransferToAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbar.toolbarTV.text = getString(R.string.transfer_to_account_borrow)
        binding.toolbar.backBtn.setOnClickListener { finish() }
        binding.continueBtn.setOnClickListener { finish() }

        binding.toolbar.toolbarTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.continueBtn.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))

        binding.accountNrTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.accountNrET.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))

        binding.debtToBeByET.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.debtToBeByTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))

        binding.loanAmountET.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.loanAmountTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))

        binding.nameET.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.nameTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.surnameET.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.surnameTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
    }
}