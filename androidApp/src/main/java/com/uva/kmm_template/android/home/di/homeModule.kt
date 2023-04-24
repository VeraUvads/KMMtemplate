package com.uva.kmm_template.android.home.di

import com.uva.kmm_template.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(sampleRepository = get())
    }
}
