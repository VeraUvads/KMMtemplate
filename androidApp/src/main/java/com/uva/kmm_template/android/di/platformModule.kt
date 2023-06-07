package com.uva.kmm_template.android.di

import com.uva.kmm_template.android.detail.DetailViewModel
import com.uva.kmm_template.android.home.HomeViewModel
import com.uva.kmm_template.android.navigation.HomeNavFactory
import com.uva.kmm_template.android.navigation.TopLevelDeclaration
import com.uva.kmm_template.android.utils.intoSetSingle
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun platformModule() = module { // todo rewrite koin to annotations?
    viewModel { HomeViewModel(sampleRepository = get()) }
    viewModel { DetailViewModel(sampleRepository = get()) } // todo template
    intoSetSingle<TopLevelDeclaration>() { HomeNavFactory() }
}
