package com.user.brayan.pruebatec_todo1.ui.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.databinding.AccountsItemBinding
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.ui.common.DataBoundListAdapter
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

class AccountAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val callback: ((Accounts) -> Unit)?
): DataBoundListAdapter<Accounts, AccountsItemBinding>
    (
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Accounts>() {
        override fun areItemsTheSame(oldItem: Accounts, newItem: Accounts): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: Accounts, newItem: Accounts): Boolean {
            return oldItem.number == newItem.number && oldItem.accountType == newItem.accountType
        }
    }
) {
    override fun createBinding(parent: ViewGroup): AccountsItemBinding {
        val binding = DataBindingUtil.inflate<AccountsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.accounts_item,
            parent,
            false,
            dataBindingComponent
        )

        binding.tvLinkDetails.setOnClickListener {
            binding.account?.let {
                callback?.invoke(it)
            }
        }

        return binding
    }

    override fun bind(binding: AccountsItemBinding, item: Accounts) {
        binding.account = item

        val format = NumberFormat.getInstance(Locale("es-CO"))
        val balance = BigDecimal(item.balance)
        binding.balanceText = "$ ${format.format(balance)}"
    }
}