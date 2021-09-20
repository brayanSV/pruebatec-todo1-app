package com.user.brayan.pruebatec_todo1.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.user.brayan.pruebatec_todo1.R
import com.user.brayan.pruebatec_todo1.di.ViewModelModule
import dagger.android.support.AndroidSupportInjectionModule


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


    }
}