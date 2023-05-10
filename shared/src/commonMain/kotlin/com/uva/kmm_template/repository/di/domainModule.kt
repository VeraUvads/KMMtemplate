package com.uva.kmm_template.repository.di

import com.uva.kmm_template.repository.SampleRepository
import org.koin.dsl.module

val domainModule = module {
//    single { SampleRepository(remoteDataSource = get()) }
    single { SampleRepository() }
}
