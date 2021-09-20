package com.user.brayan.pruebatec_todo1.ui.history

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.databinding.HistoryItemBinding
import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.ui.common.DataBoundListAdapter
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class HistoryAdapter (
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((HistoryAccounts) -> Unit)?
): DataBoundListAdapter<HistoryAccounts, HistoryItemBinding>
(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<HistoryAccounts>() {
        override fun areItemsTheSame(oldItem: HistoryAccounts, newItem: HistoryAccounts): Boolean {
            return oldItem.accountID == newItem.accountID
        }

        override fun areContentsTheSame(oldItem: HistoryAccounts, newItem: HistoryAccounts): Boolean {
            return oldItem.accountID == newItem.accountID && oldItem.date == newItem.date && oldItem.descripcion == newItem.descripcion && oldItem.reference == newItem.reference  && oldItem.amount == newItem.amount && oldItem.accountID == newItem.accountID
        }
    }
) {
    var context: Context? = null

    override fun createBinding(parent: ViewGroup): HistoryItemBinding {
        val binding = DataBindingUtil.inflate<HistoryItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.history_item,
            parent,
            false,
            dataBindingComponent
        )

        context = parent.context
        return binding
    }

    override fun bind(binding: HistoryItemBinding, item: HistoryAccounts) {
        binding.history = item

        val format = NumberFormat.getInstance(Locale("es-CO"))
        val amount = BigDecimal(item.amount)

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            binding.tvAmount.setTextColor(context!!.getColor(R.color.rgb_FF0000))
        } else if (amount.compareTo(BigDecimal.ZERO) > 0) {
            binding.tvAmount.setTextColor(context!!.getColor(R.color.rgb_009900))
        } else {
            binding.tvAmount.setTextColor(context!!.getColor(R.color.colorBlack))
        }

        binding.amountText = "$ ${format.format(amount)}"
    }
}