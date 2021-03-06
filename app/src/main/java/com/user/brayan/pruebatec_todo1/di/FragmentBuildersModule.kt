package com.user.brayan.pruebatec_todo1.di

import com.user.brayan.pruebatec_todo1.ui.account.AccountFragment
import com.user.brayan.pruebatec_todo1.ui.history.HistoryFragment
import com.user.brayan.pruebatec_todo1.ui.pay.PayFragment
import com.user.brayan.pruebatec_todo1.ui.recharge_account.RechargeAccountFragment
import com.user.brayan.pruebatec_todo1.ui.scan.ScanFragment
import com.user.brayan.pruebatec_todo1.ui.transfers.TransfersFragment
import com.user.brayan.pruebatec_todo1.ui.viewcode.ViewCodeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeAccountFragment(): AccountFragment

    @ContributesAndroidInjector
    abstract fun contributeHistoryFragment(): HistoryFragment

    @ContributesAndroidInjector
    abstract fun contributeTransferFragment(): TransfersFragment

    @ContributesAndroidInjector
    abstract fun contributeScanFragment(): ScanFragment

    @ContributesAndroidInjector
    abstract fun contributeRechargeAccountFragment(): RechargeAccountFragment

    @ContributesAndroidInjector
    abstract fun contributeViewCodeFragment(): ViewCodeFragment

    @ContributesAndroidInjector
    abstract fun contributePayFragment(): PayFragment
}