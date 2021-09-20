package com.user.brayan.pruebatec_todo1.ui.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBoundViewHolder<out T: ViewDataBinding> constructor(val binding: T): RecyclerView.ViewHolder(binding.root)