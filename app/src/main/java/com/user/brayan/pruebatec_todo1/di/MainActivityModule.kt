package com.user.brayan.pruebatec_todo1.di

import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.ui.account.AccountFragment
import com.user.brayan.pruebatec_todo1.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}

