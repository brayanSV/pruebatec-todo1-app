package com.user.brayan.mvvmkotlinudemy.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment

class FragmentDataBindingComponent (fragment: Fragment): DataBindingComponent {
    private val adapter = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters(): FragmentBindingAdapters = adapter
}