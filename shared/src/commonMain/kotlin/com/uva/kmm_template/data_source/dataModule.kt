package com.uva.kmm_template.data_source

import org.koin.dsl.module

val dataModule = module {
    single { SampleDataSource(apiCall = get()) } // TODO Local remote
}