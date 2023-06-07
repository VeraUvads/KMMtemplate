package com.uva.kmm_template.android.di

import com.uva.kmm_template.android.detail.DetailViewModel
import com.uva.kmm_template.android.home.HomeViewModel
import com.uva.kmm_template.android.navigation.ComposeNavigationFactory
import com.uva.kmm_template.android.navigation.HomeNavFactory
import com.uva.kmm_template.android.utils.intoSet
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun platformModule() = module {
    viewModel { HomeViewModel(sampleRepository = get()) }
    viewModel { DetailViewModel(sampleRepository = get()) } // todo template
    intoSet<ComposeNavigationFactory> { HomeNavFactory() }
}
