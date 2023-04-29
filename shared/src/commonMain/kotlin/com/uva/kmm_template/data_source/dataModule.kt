package com.uva.kmm_template.data_source

import org.koin.dsl.module

val dataModule = module {
    single { SampleRemoteDataSource(apiCall = get()) } // TODO Local remote
}