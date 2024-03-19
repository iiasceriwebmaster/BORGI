package md.webmaster.borgi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import md.webmaster.borgi.adapters.MainAdapter
import md.webmaster.borgi.data.DebtEntity
import md.webmaster.borgi.databinding.ActivityDebtsIntoMoneyBinding

class DebtsIntoMoneyActivity : AppCompatActivity() {

    lateinit var binding : ActivityDebtsIntoMoneyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDebtsIntoMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listItems = mutableListOf<DebtEntity>()
        for (i in 0..29) {
            listItems.add(DebtEntity(i.toLong(), "${i+1} Oct, 2025", "4000 0000 0000 000${i}", "Nr.45891${i}", (i+1)*1000))
        }

        binding.debtsRV.adapter = MainAdapter(listItems, this)

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}