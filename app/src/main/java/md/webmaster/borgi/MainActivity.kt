package md.webmaster.borgi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import md.webmaster.borgi.adapters.MainAdapter
import md.webmaster.borgi.data.DebtEntity
import md.webmaster.borgi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LanguageDialogFragment.DialogLanguageFragmentListener {

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
        binding.drawerBtn.setOnClickListener {
            binding.main.open()
        }

        setUpFonts()
        updateNavigationHeader()
        setUpNavClicks(this)


//        binding.toolbarTitleTV.text = ""
    }

    fun showLanguageDialog() {

    }

    fun logoutUser() {
        // Perform logout action, such as clearing user session and navigating to the login screen
        // For example:
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Finish the MainActivity to prevent returning to it after logout
    }


    fun setUpNavClicks(context: Context) {
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            binding.main.close()
            when (menuItem.itemId) {
                R.id.nav_user_profile -> {
                    // Open user profile
                    val intent = Intent(this, SignupActivity::class.java)
                    intent.putExtra("title", resources.getString(R.string.user_profile))
                    startActivity(intent)
                    return@setNavigationItemSelectedListener true
                }

                R.id.nav_language -> {
                    // Open car acceptance activity
                    val dialog = LanguageDialogFragment()
                    dialog.show(supportFragmentManager, "LanguageDialogFragment")
                    return@setNavigationItemSelectedListener true
                }

                R.id.nav_sign_out -> {
                    // Perform logout action
                    logoutUser()
                    return@setNavigationItemSelectedListener true
                }

                else -> return@setNavigationItemSelectedListener super.onOptionsItemSelected(
                    menuItem
                )
            }
        }
    }

    fun setUpFonts() {
        binding.toolbarTitleTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.totalAvailableMCGTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.availableLimitMCGTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.debtsMinusMCGTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.debtsPlusMCGTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.totalAvailableMCGRightTV.setTypeface(
            ResourcesCompat.getFont(
                this,
                R.font.onest_bold
            )
        )
        binding.availableLimitMCGRightTV.setTypeface(
            ResourcesCompat.getFont(
                this,
                R.font.onest_bold
            )
        )
        binding.debtsMinusMCGRightTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_bold))
        binding.debtsPlusMCGRightTV.setTypeface(ResourcesCompat.getFont(this, R.font.onest_bold))

        binding.goldBtn.setTypeface(ResourcesCompat.getFont(this, R.font.onest_medium))
        binding.transferToAccount2ndTVBtn.setTypeface(
            ResourcesCompat.getFont(
                this,
                R.font.onest_medium
            )
        )
        binding.transferToAccountTVBtn.setTypeface(
            ResourcesCompat.getFont(
                this,
                R.font.onest_medium
            )
        )

        binding.searchET.setTypeface(ResourcesCompat.getFont(this, R.font.onest_regular))
    }

    fun updateNavigationHeader(
        fullName: String = "John Doe",
        phoneNr: String = "+44 75 35 123 123"
    ) {
        val headerView = binding.navView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.nav_header_title).text = fullName
        headerView.findViewById<TextView>(R.id.nav_header_subtitle).text = phoneNr
    }

    override fun onLanguagePicked(lang: String) {
        //nothing yet
    }
}