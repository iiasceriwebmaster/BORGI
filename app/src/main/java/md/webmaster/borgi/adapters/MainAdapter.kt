package md.webmaster.borgi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import md.webmaster.borgi.R
import md.webmaster.borgi.data.DebtEntity

class MainAdapter(
    private var debts: List<DebtEntity>,
    val context: Context
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
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

        holder.dateTV.text = debtItem.date
        holder.nrTV.text = debtItem.nr
        holder.accountTV.text = debtItem.account


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


//        holder.reportDate.setTypeface(ResourcesCompat.getFont(context, R.font.roboto_regular))
    }

}