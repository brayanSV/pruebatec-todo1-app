package com.user.brayan.pruebatec_todo1.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.lifecycleScope
import com.user.brayan.pruebatec_todo1.binding.FragmentDataBindingComponent
import com.user.brayan.pruebatec_todo1.AppExecutors
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.databinding.ActivityLoginBinding
import com.user.brayan.pruebatec_todo1.di.Injectable
import com.user.brayan.pruebatec_todo1.di.ViewModelModule
import com.user.brayan.pruebatec_todo1.model.LoginUser
import com.user.brayan.pruebatec_todo1.ui.common.LoginCallback
import com.user.brayan.pruebatec_todo1.ui.common.RetryCallback
import com.user.brayan.pruebatec_todo1.ui.main.MainActivity
import com.user.brayan.pruebatec_todo1.utils.autoCleared
import com.user.brayan.pruebatec_todo1.view_model.AppViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class Login : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    @Inject
    lateinit var appExecutors: AppExecutors

    val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.retryCallback = object: RetryCallback {
            override fun retry() {
                loginViewModel.retry()
            }
        }

        binding.loginCallback = object: LoginCallback {
            override fun login() {
                loginUser()
            }
        }
    }

    fun loginUser() {
        val user = binding.tilUsuario.editText?.text.toString()
        val passw = binding.tilPassword.editText?.text.toString()

        if (loginViewModel.fieldsIsEmpty(user, passw)) {
            showToast(getString(R.string.text_message_error_field_empty_login))
        } else {
            loginViewModel.setUser(user, passw)

            loginViewModel.result.observe(this, Observer { infoToken ->
                binding.tokenResult = loginViewModel.result

                if (infoToken.data != null) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("bearerToken", infoToken.data?.token)
                    startActivity(intent)
                    finish()
                }
            })
        }
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }
}