package md.webmaster.borgi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import md.webmaster.borgi.R
import md.webmaster.borgi.data.DebtEntity


class DebtDetailsAdapter(
    private var debts: List<DebtEntity>,
    val context: Context
) : RecyclerView.Adapter<DebtDetailsAdapter.DebtDetailsHolder>() {
    inner class DebtDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val debtDateTV: TextView = itemView.findViewById<TextView>(R.id.debtDateTV)
        val debtAmountTV: TextView = itemView.findViewById<TextView>(R.id.debtAmountTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtDetailsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.debt_details_item, parent, false)
        return DebtDetailsHolder(view)
    }

    override fun getItemCount() = debts.size

    override fun onBindViewHolder(holder: DebtDetailsHolder, position: Int) {
        val debtItem = debts[position]

        holder.debtDateTV.text = debtItem.date
        holder.debtAmountTV.text = debtItem.debtAmount.toString()

        holder.debtDateTV.setTypeface(ResourcesCompat.getFont(context, R.font.onest_regular))
        holder.debtAmountTV.setTypeface(ResourcesCompat.getFont(context, R.font.onest_regular))
    }

}