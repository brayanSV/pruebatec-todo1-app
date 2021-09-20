package com.user.brayan.pruebatec_todo1.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.user.brayan.mvvmkotlinudemy.binding.FragmentDataBindingComponent
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.databinding.FragmentHistoryBinding
import com.user.brayan.pruebatec_todo1.databinding.FragmentLoginBinding
import com.user.brayan.pruebatec_todo1.model.LoginUser
import com.user.brayan.pruebatec_todo1.ui.history.HistoryAdapter
import com.user.brayan.pruebatec_todo1.ui.history.HistoryFragmentArgs
import com.user.brayan.pruebatec_todo1.ui.history.HistoryViewModel
import com.user.brayan.pruebatec_todo1.ui.main.MainActivity
import com.user.brayan.pruebatec_todo1.utils.autoCleared
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class LoginFragment : Fragment() {
        @Inject
        lateinit var viewModelFactory: ViewModelProvider.Factory

        @Inject
        lateinit var appExecutors: AppExecutors

        var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
        var binding by autoCleared<FragmentLoginBinding>()

        val loginViewModel: LoginViewModel by viewModels {
            viewModelFactory
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false, dataBindingComponent)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            binding.lifecycleOwner = viewLifecycleOwner

            binding.btnRegistrar.setOnClickListener {
               login()
            }
        }

        fun login() {
            if (loginViewModel.fieldsIsEmpty(binding.tilUsuario.editText?.text.toString(), binding.tilPassword.editText?.text.toString())) {
                val toast = Toast.makeText(context, "Verifique que los campos no se encuentren vacios", Toast.LENGTH_LONG)
                toast.show()
            } else {
                Log.e("datos", "hola Mundo")
                loginViewModel.loginUser.value = LoginUser(binding.tilUsuario.editText?.text.toString(), binding.tilPassword.editText?.text.toString())

                val intent = Intent(context, MainActivity::class.java)
                context?.startActivity(intent)
                activity?.finish()
            }
        }
    }



