package com.uva.kmm_template.android.di

import com.uva.kmm_template.android.detail.DetailViewModel
import com.uva.kmm_template.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { HomeViewModel(sampleRepository = get()) }
    viewModel { DetailViewModel(sampleRepository = get()) } // todo template
}
