package md.webmaster.borgi.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import md.webmaster.borgi.AccountPickerDialogFragment
import md.webmaster.borgi.LanguageDialogFragment
import md.webmaster.borgi.R
import md.webmaster.borgi.adapters.MainAdapter
import md.webmaster.borgi.data.BorgiDatabase
import md.webmaster.borgi.data.DebtEntity
import md.webmaster.borgi.data.UserEntity
import md.webmaster.borgi.databinding.ActivityMainBinding
import md.webmaster.borgi.tools.Extensions.chunkedToString
import md.webmaster.borgi.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(), LanguageDialogFragment.DialogLanguageFragmentListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var appDatabase: BorgiDatabase
    private lateinit var userEntity: UserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        runBlocking {
            appDatabase = BorgiDatabase.getInstance(applicationContext)
            userViewModel = UserViewModel(appDatabase.userDao(), appDatabase.debtDao())
            withContext(Dispatchers.IO) {
                userEntity = userViewModel.getAll()[0]
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listItems = mutableListOf<DebtEntity>()
        for (i in 0..29) {
            if (i % 2 == 0) {
                listItems.add(
                    DebtEntity(
                        i.toLong(),
                        "${i + 1} Oct, 2025",
                        4000000000000000 + i,
                        "Nr.45891${i}",
                        -15000 + (i + 1) * 1000
                    )
                )
            } else {
                listItems.add(
                    DebtEntity(
                        i.toLong(),
                        "${i + 1} Oct, 2025",
                        7000000000000000 + i,
                        "Nr.45891${i}",
                        -15000 + (i + 1) * 1000
                    )
                )
            }
        }

        val accountTypeOneList = listItems.filter { debt ->
            debt.account.toString().startsWith("4")
        }

        val accountTypeTwoList = listItems.filter { debt ->
            debt.account.toString().startsWith("7")
        }

        binding.mainRV.adapter = MainAdapter(accountTypeOneList, this)

        binding.searchET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Filter the adapter based on the text entered
                ((binding.mainRV.adapter) as MainAdapter).filterFromSearchBar(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }
        })

        binding.goldBtn.setOnClickListener {
            startActivity(Intent(this, DebtsIntoMoneyActivity::class.java))
        }
        binding.redBtn.setOnClickListener {
            startActivity(Intent(this, TransferToAccountActivity::class.java))
        }
        binding.drawerBtn.setOnClickListener {
            binding.main.open()
        }

        binding.sortBtn.setOnClickListener {
            ((binding.mainRV.adapter) as MainAdapter).filterFromBtn(isSort = true)
        }

        setUpFonts()
        updateNavigationHeader()
        setUpNavClicks(this)

        binding.toolbarTitleTV.text = userEntity.accountTypeTwo.chunkedToString()

        binding.arrowsBtn.setOnClickListener {
            val dialog = AccountPickerDialogFragment(
                userEntity.accountTypeTwo,
                userEntity.accountTypeThree
            ) { isFirstAccount ->
                if (isFirstAccount) {
                    binding.toolbarTitleTV.text = userEntity.accountTypeTwo.chunkedToString()
                    binding.mainRV.adapter = MainAdapter(accountTypeOneList, this)
                } else {
                    binding.toolbarTitleTV.text = userEntity.accountTypeThree.chunkedToString()
                    binding.mainRV.adapter = MainAdapter(accountTypeTwoList, this)
                }
            }
            dialog.show(supportFragmentManager, "AccountPickerDialogFragment")
        }
    }

    private fun logoutUser() {
        userViewModel.deleteAll()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
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

    private fun setUpFonts() {
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
        //TODO: implement
    }
}