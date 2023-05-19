package com.uva.kmm_template

import com.uva.kmm_template.di.commonModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(commonModules)
    }
}
