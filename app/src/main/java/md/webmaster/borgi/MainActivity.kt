package md.webmaster.borgi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import md.webmaster.borgi.adapters.MainAdapter
import md.webmaster.borgi.data.DebtEntity
import md.webmaster.borgi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listItems = mutableListOf<DebtEntity>()
        for (i in 0..29) {
            listItems.add(DebtEntity(i.toLong(), "${i+1} Oct, 2025", "4000 0000 0000 000${i}", "Nr.45891${i}", -15000+(i+1)*1000))
        }

        binding.mainRV.adapter = MainAdapter(listItems, this)

        binding.goldBtn.setOnClickListener {
            startActivity(Intent(this, DebtsIntoMoneyActivity::class.java))
        }
        binding.redBtn.setOnClickListener {
            startActivity(Intent(this, TransferToAccountActivity::class.java))
        }

        binding.toolbarTitleTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.totalAvailableMCGTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.availableLimitMCGTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.debtsMinusMCGTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.debtsPlusMCGTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.totalAvailableMCGRightTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_bold))
        binding.availableLimitMCGRightTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_bold))
        binding.debtsMinusMCGRightTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_bold))
        binding.debtsPlusMCGRightTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_bold))

        binding.goldBtn.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.transferToAccount2ndTVBtn.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.transferToAccountTVBtn.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))

        binding.searchET.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
    }
}