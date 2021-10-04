package com.user.brayan.pruebatec_todo1.ui.recharge_account

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.databinding.AccountsItemsSpinnerBinding
import com.user.brayan.pruebatec_todo1.model.Accounts


class AccountAdapterSpinner(
    context: Context,
    resource: Int,
    val list: List<Accounts>,
    private val dataBindingComponent: DataBindingComponent
) : ArrayAdapter<Any> (context, resource, list) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = DataBindingUtil.inflate<AccountsItemsSpinnerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.accounts_items_spinner,
            parent,
            false,
            dataBindingComponent
        )

        binding.account = list[position]
        return binding.root
    }
}