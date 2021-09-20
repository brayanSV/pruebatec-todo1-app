package com.user.brayan.pruebatec_todo1.ui.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.user.brayan.mvvmkotlinudemy.binding.FragmentDataBindingComponent
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.databinding.FragmentAccountBinding
import com.user.brayan.pruebatec_todo1.databinding.FragmentHistoryBinding
import com.user.brayan.pruebatec_todo1.di.Injectable
import com.user.brayan.pruebatec_todo1.ui.account.AccountViewModel
import com.user.brayan.pruebatec_todo1.utils.autoCleared
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class HistoryFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentHistoryBinding>()
    var adapter by autoCleared<HistoryAdapter>()

    val historyViewModel: HistoryViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false, dataBindingComponent)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val params = HistoryFragmentArgs.fromBundle(requireArguments())
        historyViewModel.setId(params.accountId, params.type)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.accountType = params.type
        binding.number = params.number

        val format = NumberFormat.getInstance(Locale("es-CO"))
        val balance = BigDecimal(params.balance)
        binding.balanceText = "$ ${format.format(balance)}"

        val adapter = HistoryAdapter(dataBindingComponent, appExecutors) {
        }

        this.adapter = adapter
        binding.historyList.adapter = adapter

        initAccountsList(historyViewModel)
    }

    private fun initAccountsList(viewModel: HistoryViewModel) {
        viewModel.history.observe(viewLifecycleOwner, Observer { listResource ->
            if(listResource?.data != null) {
                adapter.submitList(listResource.data)
            } else {
                adapter.submitList(emptyList())
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigate(HistoryFragmentDirections.actionHistoryFragmentToNavigationAccount())
        }

        return super.onOptionsItemSelected(item)
    }
}