package com.uva.kmm_template.android.main

import com.uva.kmm_template.android.home.di.homeModule
import com.uva.kmm_template.data_source.dataModule
import com.uva.kmm_template.data_source.remote.di.networkModule
import com.uva.kmm_template.repository.di.domainModule

val allModules = homeModule +
    domainModule +
    dataModule +
    networkModule
