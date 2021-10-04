package com.user.brayan.pruebatec_todo1.ui.recharge_account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.binding.FragmentDataBindingComponent
import com.user.brayan.pruebatec_todo1.databinding.FragmentRechargeAccountBinding
import com.user.brayan.pruebatec_todo1.di.Injectable
import com.user.brayan.pruebatec_todo1.model.Accounts
import com.user.brayan.pruebatec_todo1.model.QRCodes
import com.user.brayan.pruebatec_todo1.ui.common.GenerateQRCodeCallback
import com.user.brayan.pruebatec_todo1.utils.AbsentLiveData
import com.user.brayan.pruebatec_todo1.utils.autoCleared
import javax.inject.Inject


class RechargeAccountFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentRechargeAccountBinding>()

    val rechargeAccountViewModel: RechargeAccountViewModel by viewModels {
        viewModelFactory
    }

    lateinit var adapter: AccountAdapterSpinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recharge_account,
            container,
            false,
            dataBindingComponent
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        binding.codeQRGenerateCallback = object: GenerateQRCodeCallback {
            override fun generate() {
                if (rechargeAccountViewModel.typeAccount.value != null) {
                    val accountID = rechargeAccountViewModel.typeAccount.value!!.accountID
                    val accountType = rechargeAccountViewModel.typeAccount.value!!.accountType
                    val number = rechargeAccountViewModel.typeAccount.value!!.number
                    val description = binding.tilDescription.editText!!.text.toString()
                    val amount = binding.tilAmount.editText!!.text.toString()

                    val file = rechargeAccountViewModel.convertData(QRCodes(accountID, accountType, number, description, amount))
                    findNavController().navigate(RechargeAccountFragmentDirections.actionRechargeAccountFragmentToViewCodeFragment(file))
                }
            }
        }

        rechargeAccountViewModel.repositories.observe(viewLifecycleOwner, Observer { accounts ->
            var list: List<Accounts> = listOf()

            if (!accounts.data.isNullOrEmpty()) {
                list = accounts.data
            }

            adapter = AccountAdapterSpinner(requireContext(), android.R.layout.simple_list_item_1, list, dataBindingComponent)
            binding.typeAccount.setAdapter(adapter)

            binding.typeAccount.setOnItemClickListener { adapterView, view, position, l ->
                rechargeAccountViewModel.typeAccount.value = accounts.data!![position]
                binding.accounts = rechargeAccountViewModel.typeAccount.value
            }
        })

        binding.typeAccount.setOnClickListener {
            //Limpiamos el spinner ya que este tiene un bug que no permite seleccionar cuando se le establece un texto
            if (rechargeAccountViewModel.typeAccount.value != null && !binding.typeAccount.text.isNullOrEmpty()) {
                binding.typeAccount.text = null
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigate(RechargeAccountFragmentDirections.actionRechargeAccountFragmentToNavigationTransfer())
        }

        return super.onOptionsItemSelected(item)
    }
}