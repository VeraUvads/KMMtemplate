package com.uva.kmm_template.android.main

import android.app.Application
import com.uva.kmm_template.android.di.uiModule
import com.uva.kmm_template.commonModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(commonModules() + uiModule)
        }
    }
}
