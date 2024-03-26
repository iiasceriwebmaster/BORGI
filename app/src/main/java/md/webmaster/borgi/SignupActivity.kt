package md.webmaster.borgi

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import md.webmaster.borgi.BitmapConverter.convertBitmapToString
import md.webmaster.borgi.data.BorgiDatabase
import md.webmaster.borgi.data.UserEntity
import md.webmaster.borgi.databinding.ActivitySignupBinding
import md.webmaster.borgi.viewmodel.UserViewModel
import java.io.InputStream

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding
    private var PICK_IMAGE_MULTIPLE = 123
    private lateinit var userViewModel: UserViewModel
    private lateinit var appDatabase: BorgiDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbar.toolbarTV.text = intent.getStringExtra("title") ?: "Missing Title"

        binding.toolbar.backBtn.setOnClickListener {
            finish()
        }

        binding.signatureBtn.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_MULTIPLE
            )
        }

        binding.signupBtn.setOnClickListener {
            val name = binding.name.text.toString()
            val surname = binding.surname.text.toString()
//            val middleName = binding.surname
            val email = binding.email.text.toString()
            val dateOfBirth = binding.dateOfBirth.text.toString()
            val passportNr = binding.passportNr.text.toString()
            val passportIssuer = binding.issuer.text.toString()
            val passportCountryName = binding.country.selectedCountryName
            val passportCity = binding.city.text.toString()
            val passportStreet = binding.street.text.toString()
            val passportHouseNr = binding.house.text.toString()
            val flatNr = binding.flatNr.text.toString()
            val phoneCountryCode = binding.phoneNrCCP.selectedCountryCode
            val phoneNrLastDigits = binding.phoneNr.text.toString()
            val phoneNr = "+$phoneCountryCode$phoneNrLastDigits"
            val passportIssueDate = binding.passportDate.text.toString()
            userViewModel.signatureBitmap.value?.let { it1 ->
                convertBitmapToString(
                    it1
                )
            }?.let { it2 ->
                userViewModel.insert(UserEntity(
                    firstName = name,
                    lastName = surname,
                    email = email,
                    dateOfBirth = dateOfBirth,
                    passportNr = passportNr,
                    passportIssuer = passportIssuer,
                    passportCountry = passportCountryName,
                    passportCity = passportCity,
                    passportStreet = passportStreet,
                    passportStreetNr = passportHouseNr,
                    passportFlatNr = flatNr,
                    phoneNr = phoneNr,
                    passportIssueDate = passportIssueDate,
                    signature = it2
                ))
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        appDatabase = BorgiDatabase.getInstance(applicationContext)
        userViewModel = UserViewModel(appDatabase.userDao())

        userViewModel.signatureBitmap.observe(
            this@SignupActivity
        ) { signature ->
            binding.signatureView.setImageBitmap(signature)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && data != null) {

            if (data.clipData != null) {
                val clipData = data.clipData
                val count = clipData!!.itemCount
                for (i in 0 until count) {
                    val imageUri = clipData.getItemAt(i).uri
                    val inputStream: InputStream? = this.contentResolver.openInputStream(imageUri)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    userViewModel.addSelectedImage(bitmap)
                }
            } else {
                val imageUri = data.data
                val inputStream: InputStream? = imageUri?.let {
                    this.contentResolver.openInputStream(
                        it
                    )
                }
                val bitmap = BitmapFactory.decodeStream(inputStream)
                userViewModel.addSelectedImage(bitmap!!)
            }
        } else {
            Log.i("borgi", "You haven't picked Image")
        }
    }
}