package com.user.brayan.pruebatec_todo1.ui.scan

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.binding.FragmentDataBindingComponent
import com.user.brayan.pruebatec_todo1.databinding.FragmentScanBinding
import com.user.brayan.pruebatec_todo1.di.Injectable
import com.user.brayan.pruebatec_todo1.utils.autoCleared
import javax.inject.Inject

class ScanFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentScanBinding>()

    val scanViewModel: ScanViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var codeScanner: CodeScanner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scan, container, false, dataBindingComponent)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val params = ScanFragmentArgs.fromBundle(requireArguments())
        scanViewModel.setBearerToken(params.bearerToken)
        binding.lifecycleOwner = viewLifecycleOwner

        requestMultiplePermissions.launch(Manifest.permission.CAMERA)
        initScan()
    }

    private fun initScan() {
        codeScanner = CodeScanner(requireContext(), binding.scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        codeScanner.decodeCallback = DecodeCallback {
            appExecutors.mainThread.execute {
                findNavController().navigate(ScanFragmentDirections.actionScanFragmentToPayFragment(it.text, scanViewModel.bearerToken.value.toString()))
            }
        }

        codeScanner.errorCallback = ErrorCallback {
            appExecutors.mainThread.execute {
                Toast.makeText( requireContext(), "error: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }

        codeScanner.startPreview()
    }

    private val requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted  ->
        if (isGranted) {
            codeScanner.startPreview()
        } else {
            findNavController().navigate(ScanFragmentDirections.actionScanFragmentToNavigationTransfer())
        }
    }

    override fun onResume() {
        super.onResume()

        codeScanner.startPreview()
    }

    override fun onStop() {
        codeScanner.releaseResources()
        super.onStop()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigate(ScanFragmentDirections.actionScanFragmentToNavigationTransfer())
        }

        return super.onOptionsItemSelected(item)
    }
}