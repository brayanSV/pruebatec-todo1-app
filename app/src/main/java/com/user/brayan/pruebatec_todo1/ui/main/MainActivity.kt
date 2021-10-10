package com.user.brayan.pruebatec_todo1.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.user.brayan.pruebatec_todo1.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bearerToken = intent.getStringExtra("bearerToken")

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        val bundle = Bundle()
        bundle.putString("bearerToken", bearerToken)
        navController.setGraph(R.navigation.navigation_main, bundle)

        navController.graph.findNode(R.id.navigation_account)
            ?.addArgument(
                "bearerToken", NavArgument.Builder()
                    .setDefaultValue(bearerToken)
                    .build()
            )

        navController.graph.findNode(R.id.navigation_transfer)
            ?.addArgument(
                "bearerToken", NavArgument.Builder()
                    .setDefaultValue(bearerToken)
                    .build()
            )

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_account, R.id.navigation_transfer
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}