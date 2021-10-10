package com.user.brayan.pruebatec_todo1.ui.pay

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
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.binding.FragmentDataBindingComponent
import com.user.brayan.pruebatec_todo1.databinding.FragmentPayBinding
import com.user.brayan.pruebatec_todo1.di.Injectable
import com.user.brayan.pruebatec_todo1.repository.Status
import com.user.brayan.pruebatec_todo1.ui.common.PayCallback
import com.user.brayan.pruebatec_todo1.utils.autoCleared
import javax.inject.Inject

class PayFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentPayBinding>()

    val payViewModel: PayViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_pay,
            container,
            false,
            dataBindingComponent
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val params = PayFragmentArgs.fromBundle(requireArguments())
        binding.lifecycleOwner = viewLifecycleOwner
        payViewModel.convertJsonToData(params.qrCode)
        binding.qrCode = payViewModel.decodeQRCode.value

        binding.payCallback = object: PayCallback {
            override fun pay() {
                payViewModel.setData(payViewModel.createTransfer(), params.bearerToken)
            }
        }

        payViewModel.history.observe(viewLifecycleOwner, Observer {
            appExecutors.mainThread.execute {
                if (it.status == Status.SUCCESS) {
                    Toast.makeText(requireContext(), "Pago realizado correctamente", Toast.LENGTH_LONG).show()
                    findNavController().navigate(PayFragmentDirections.actionPayFragmentToNavigationTransfer())
                } else if (it.status == Status.ERROR) {
                    Toast.makeText(requireContext(), "No fue posible realizar el pago, intentelo de nuevo mas tarde.", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigate(PayFragmentDirections.actionPayFragmentToNavigationTransfer())
        }

        return super.onOptionsItemSelected(item)
    }
}