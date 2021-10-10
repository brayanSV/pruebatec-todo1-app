package com.user.brayan.pruebatec_todo1.ui.transfers

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.binding.FragmentDataBindingComponent
import com.user.brayan.pruebatec_todo1.databinding.FragmentTransfersBinding
import com.user.brayan.pruebatec_todo1.di.Injectable
import com.user.brayan.pruebatec_todo1.ui.common.RechargeAccountCallback
import com.user.brayan.pruebatec_todo1.ui.common.QRScanCallback
import com.user.brayan.pruebatec_todo1.utils.autoCleared
import javax.inject.Inject


class TransfersFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentTransfersBinding>()

    val transferViewModel: TransferViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_transfers,
            container,
            false,
            dataBindingComponent
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        transferViewModel.setBearerToken(arguments?.getString("bearerToken"))
        binding.lifecycleOwner = viewLifecycleOwner

        binding.scanCallback = object: QRScanCallback {
            override fun scan() {
                requestMultiplePermissions.launch(Manifest.permission.CAMERA)
            }
        }

        binding.rechargeAccountCallback = object: RechargeAccountCallback {
            override fun recharge() {
                findNavController().navigate(TransfersFragmentDirections.actionNavigationTransferToRechargeAccountFragment())
            }
        }
    }

    private val requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted  ->
        if (isGranted) {
            findNavController().navigate(TransfersFragmentDirections.actionNavigationTransferToScanFragment(transferViewModel.bearerToken.value.toString()))
        }
    }
}