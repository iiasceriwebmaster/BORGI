package md.webmaster.borgi.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import md.webmaster.borgi.R
import md.webmaster.borgi.adapters.DebtDetailsAdapter
import md.webmaster.borgi.data.DebtEntity
import md.webmaster.borgi.databinding.ActivityDebtDetailsBinding

class DebtDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDebtDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDebtDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listItems = mutableListOf<DebtEntity>()
        for (i in 0..29) {
            listItems.add(
                DebtEntity(
                    i.toLong(),
                    "${i + 1} Oct, 2025",
                    400000000000000 + i,
                    "Nr.45891${i}",
                    (i + 1) * 1000
                )
            )
        }

        binding.debtDetailsRV.adapter = DebtDetailsAdapter(listItems, this)

        binding.toolbar.backBtn.setOnClickListener {
            finish()
        }
        binding.toolbar.toolbarTV.text = "Nr.458912"

        binding.toolbar.toolbarTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.balanceDueMCGTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.convertedToMoneyMCGTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))

        binding.balanceDueTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_bold))
        binding.convertedToMoneyTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_bold))

        binding.nameTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.underNameTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.debtAmountMCGTVAU.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.underDebtAmountMCGTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.firstDateTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.underFirstDateTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.secondDateTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
        binding.underSecondDateTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
    }
}