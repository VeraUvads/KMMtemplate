package com.uva.kmm_template.android.main

import com.uva.kmm_template.android.di.uiModule
import com.uva.kmm_template.data_source.dataModule
import com.uva.kmm_template.data_source.remote.di.networkModule
import com.uva.kmm_template.repository.di.domainModule

val allModules = uiModule +
    domainModule +
    dataModule +
    networkModule
