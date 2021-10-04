package com.user.brayan.pruebatec_todo1.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.user.brayan.pruebatec_todo1.ui.account.AccountViewModel
import com.user.brayan.pruebatec_todo1.ui.history.HistoryViewModel
import com.user.brayan.pruebatec_todo1.ui.login.LoginViewModel
import com.user.brayan.pruebatec_todo1.ui.pay.PayViewModel
import com.user.brayan.pruebatec_todo1.ui.recharge_account.RechargeAccountViewModel
import com.user.brayan.pruebatec_todo1.ui.scan.ScanViewModel
import com.user.brayan.pruebatec_todo1.ui.transfers.TransferViewModel
import com.user.brayan.pruebatec_todo1.ui.viewcode.ViewCodeViewModel
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
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransferViewModel::class)
    abstract fun bindTransferViewModel(transferViewModel: TransferViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScanViewModel::class)
    abstract fun bindScanViewModel(scanViewModel: ScanViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RechargeAccountViewModel::class)
    abstract fun bindPayViewModel(rechargeAccountViewModel: RechargeAccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewCodeViewModel::class)
    abstract fun bindViewCodeModel(viewCodeViewModel: ViewCodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PayViewModel::class)
    abstract fun bindPayModel(payViewModel: PayViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}