package com.user.brayan.pruebatec_todo1.di

import com.user.brayan.pruebatec_todo1.model.HistoryAccounts
import com.user.brayan.pruebatec_todo1.ui.account.AccountFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeAccountsFragment(): AccountFragment

    @ContributesAndroidInjector
    abstract fun contributeHistoryAccountsActivity(): HistoryAccounts
}