package com.user.brayan.pruebatec_todo1.ui.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.user.brayan.pruebatec_todo1.di.Injectable
import com.user.brayan.pruebatec_todo1.ui.history.HistoryFragmentArgs
import com.user.brayan.pruebatec_todo1.utils.autoCleared
import javax.inject.Inject

class AccountFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentAccountBinding>()
    var adapter by autoCleared<AccountAdapter>()

    val accountViewModel: AccountViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false, dataBindingComponent)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        accountViewModel.setBearerToken(arguments?.getString("bearerToken"))

        binding.lifecycleOwner = viewLifecycleOwner
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val rvAdapter = AccountAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        ) { accounts ->
            findNavController().navigate(AccountFragmentDirections.actionNavigationAccountToHistoryFragment(accounts.accountID, accounts.accountType, accounts.balance, accounts.number, accountViewModel.bearerToken.value!!))
        }

        binding.accountsList.adapter = rvAdapter
        this.adapter = rvAdapter

        if (!accountViewModel.valBearerTokenIsNull()) {
            initAccountsList(accountViewModel)
        }
    }

    private fun initAccountsList(viewModel: AccountViewModel) {
        viewModel.repositories.observe(viewLifecycleOwner, Observer { listResource ->
            if(listResource?.data != null) {
                adapter.submitList(listResource.data)
            } else {
                adapter.submitList(emptyList())
            }
        })
    }
}