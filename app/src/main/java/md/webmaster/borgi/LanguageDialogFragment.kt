package md.webmaster.borgi

import android.app.AlertDialog
import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import java.util.Locale

class LanguageDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = LayoutInflater.from(requireActivity())
        val view = inflater.inflate(R.layout.language_dialog, null)

        val enLang = view.findViewById<RadioButton>(R.id.radioBtnEn)
        val ruLang = view.findViewById<RadioButton>(R.id.radioBtnRu)
        val uaLang = view.findViewById<RadioButton>(R.id.radioBtnUa)
        val cancelBtn = view.findViewById<Button>(R.id.cancelBtn)

        val dialog = builder.setView(view).setTitle("").create()

        enLang.setOnClickListener {
            setLocale("en")
            updateAppLanguage()
            dialog.dismiss()
        }
        ruLang.setOnClickListener {
            setLocale("ru")
            updateAppLanguage()
            dialog.dismiss()
        }
        uaLang.setOnClickListener {
            setLocale("ua")
            updateAppLanguage()
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        return dialog
    }
    // Function to update UI elements based on the selected language (replace with your implementation)
    private fun updateAppLanguage() {
        // Update text views, buttons, etc. based on the current locale
        // You can access string resources using resources.getString(R.string.your_string_id)
    }

    private fun setLocale(lang: String) {
        val currentLocale = resources.configuration.locale
        val newLocale = Locale(lang)
        if (currentLocale != newLocale) {
            Locale.setDefault(newLocale)
            val config = Configuration()
            config.locale = newLocale
            resources.updateConfiguration(config, resources.displayMetrics)
        }
    }

    interface DialogLanguageFragmentListener {
        fun onLanguagePicked(lang: String)
    }
}