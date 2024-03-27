package md.webmaster.borgi.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import md.webmaster.borgi.activities.DebtDetailsActivity
import md.webmaster.borgi.R
import md.webmaster.borgi.data.DebtEntity
import md.webmaster.borgi.tools.Extensions.chunkedToString

class MainAdapter(
    private var debts: List<DebtEntity>,
    val context: Context
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var originalDebts: List<DebtEntity> = debts.toList()

    fun filterFromSearchBar(text: String) {
        debts = if (text.isEmpty()) {
            originalDebts.toList()
        } else {
            originalDebts.filter { debt ->
                debt.nr?.contains(text, ignoreCase = true) == true || debt.account.toString()
                    .contains(
                        text,
                        ignoreCase = true
                    )
            }
        }
        notifyDataSetChanged()
    }

    fun filterFromBtn(date: String = "", isSort: Boolean = false) {
        if (date.isEmpty() && !isSort) {
            debts = originalDebts.toList()
        } else {
            if (date.isNotEmpty()) {
                debts = originalDebts.filter { debt ->
                    debt.date == date
                }
            }
            if (isSort) {
                debts = debts.sortedByDescending { it.debtAmount }
            }
        }
        notifyDataSetChanged()
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTV: TextView = itemView.findViewById<TextView>(R.id.date)
        val accountTV: TextView = itemView.findViewById(R.id.account)
        val nrTV: TextView = itemView.findViewById(R.id.nr)
        val debtAmountTV: TextView = itemView.findViewById(R.id.debtAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.debt_item, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount() = debts.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val debtItem = debts[position]

        val debtAmount = debtItem.debtAmount
        if (debtAmount > 0) {
            holder.debtAmountTV.setTextColor(context.getColor(R.color.green))
        } else if (debtAmount < 0) {
            holder.debtAmountTV.setTextColor(context.getColor(R.color.red))
        } else {
            holder.debtAmountTV.setTextColor(context.getColor(R.color.black))
        }
        var debtText = debtAmount.toString()
        if (debtAmount > 0)
            debtText = "+$debtText"

        holder.debtAmountTV.text = debtText
        holder.dateTV.text = debtItem.date
        holder.nrTV.text = debtItem.nr
        holder.accountTV.text = debtItem.account?.chunkedToString()

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context as Activity, DebtDetailsActivity::class.java))
        }

        holder.dateTV.setTypeface(ResourcesCompat.getFont(context, R.font.onest_regular))
        holder.nrTV.setTypeface(ResourcesCompat.getFont(context, R.font.onest_regular))
        holder.accountTV.setTypeface(ResourcesCompat.getFont(context, R.font.onest_regular))
        holder.debtAmountTV.setTypeface(ResourcesCompat.getFont(context, R.font.onest_medium))
    }

}