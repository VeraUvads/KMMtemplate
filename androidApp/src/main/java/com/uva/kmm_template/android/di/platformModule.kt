package com.uva.kmm_template.android.di

import com.uva.kmm_template.android.detail.DetailNavFactory
import com.uva.kmm_template.android.detail.DetailViewModel
import com.uva.kmm_template.android.home.HomeNavFactory
import com.uva.kmm_template.android.home.HomeViewModel
import com.uva.kmm_template.android.navigation.ComposeNavigationFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

fun platformModule() = module { // todo rewrite koin to annotations?
    viewModel { HomeViewModel(sampleRepository = get()) }
    viewModel {
        DetailViewModel(
            sampleRepository = get(),
            savedStateHandle = get()
        )
    } // todo template

    single(named("topLevel")) { HomeNavFactory() } bind ComposeNavigationFactory::class
    single(named("topLevel")) { DetailNavFactory() } bind ComposeNavigationFactory::class
}
