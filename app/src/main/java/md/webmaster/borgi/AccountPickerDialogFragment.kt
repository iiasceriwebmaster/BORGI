package md.webmaster.borgi

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import md.webmaster.borgi.tools.Extensions.chunkedToString

class AccountPickerDialogFragment(
    val accountOne: Long,
    val accountTwo: Long,
    val callback: (Boolean) -> Unit
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = LayoutInflater.from(requireActivity())
        val view = inflater.inflate(R.layout.account_picker_dialog, null)

        val firstBtn = view.findViewById<RadioButton>(R.id.firstRadioBtn)
        val secondBtn = view.findViewById<RadioButton>(R.id.secondRadioBtn)
        val cancelBtn = view.findViewById<Button>(R.id.cancelBtn)

        firstBtn.text = accountOne.chunkedToString()
        secondBtn.text = accountTwo.chunkedToString()

        val dialog = builder.setView(view).setTitle("").create()

        var isFirstAccount: Boolean

        firstBtn.setOnClickListener {
            isFirstAccount = true
            callback(isFirstAccount)
            dialog.dismiss()
        }

        secondBtn.setOnClickListener {
            isFirstAccount = false
            callback(isFirstAccount)
            dialog.dismiss()
        }

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        return dialog
    }
}