package com.user.brayan.pruebatec_todo1.di

import com.user.brayan.pruebatec_todo1.ui.account.AccountFragment
import com.user.brayan.pruebatec_todo1.ui.history.HistoryFragment
import com.user.brayan.pruebatec_todo1.ui.login.Login
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeAccountFragment(): AccountFragment

    @ContributesAndroidInjector
    abstract fun contributeHistoryFragment(): HistoryFragment
}