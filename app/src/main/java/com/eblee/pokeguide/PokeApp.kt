package com.eblee.pokeguide

import android.app.Application
import com.eblee.pokeguide.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PokeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@PokeApp)
            modules(apiModules)
            modules(dataModules)
            modules(domainModules)
            modules(presentationModules)
            modules(localModules)
        }
    }

}