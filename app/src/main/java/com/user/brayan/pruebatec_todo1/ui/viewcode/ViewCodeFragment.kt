package com.user.brayan.pruebatec_todo1.ui.viewcode

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.binding.FragmentDataBindingComponent
import com.user.brayan.pruebatec_todo1.databinding.FragmentRechargeAccountBinding
import com.user.brayan.pruebatec_todo1.databinding.FragmentViewCodeBinding
import com.user.brayan.pruebatec_todo1.di.Injectable
import com.user.brayan.pruebatec_todo1.ui.common.ShareCallback
import com.user.brayan.pruebatec_todo1.ui.history.HistoryFragmentArgs
import com.user.brayan.pruebatec_todo1.ui.recharge_account.RechargeAccountFragmentDirections
import com.user.brayan.pruebatec_todo1.ui.recharge_account.RechargeAccountViewModel
import com.user.brayan.pruebatec_todo1.utils.autoCleared
import javax.inject.Inject


class ViewCodeFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentViewCodeBinding>()

    val viewCodeViewModel: ViewCodeViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_view_code,
            container,
            false,
            dataBindingComponent
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        val params = ViewCodeFragmentArgs.fromBundle(requireArguments())
        viewCodeViewModel.setPath(params.nameFile)

        binding.url = viewCodeViewModel.QRCode

        binding.shareCallback = object: ShareCallback {
            override fun share() {
                val share = Intent(Intent.ACTION_SEND)
                share.type = "image/jpeg"
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(viewCodeViewModel.QRCode.value))
                requireContext().startActivity(Intent.createChooser(share, "QR Code"))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigate(ViewCodeFragmentDirections.actionViewCodeFragmentToNavigationTransfer())
        }

        return super.onOptionsItemSelected(item)
    }
}