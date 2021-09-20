package com.user.brayan.pruebatec_todo1.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.user.brayan.pruebatec_todo1.ui.account.AccountViewModel
import com.user.brayan.pruebatec_todo1.ui.history.HistoryViewModel
import com.user.brayan.pruebatec_todo1.view_model.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryAccountViewModel(historyViewModel: HistoryViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}