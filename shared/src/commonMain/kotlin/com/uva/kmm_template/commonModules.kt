package com.uva.kmm_template

import com.uva.kmm_template.data_source.dataModule
import com.uva.kmm_template.data_source.remote.di.networkModule
import com.uva.kmm_template.repository.di.domainModule

fun commonModules() = listOf(
    domainModule,
    dataModule,
    networkModule
)
