package com.recurt.encyclopedia

import android.app.Application
import com.recurt.core.common.di.networkModule
import com.recurt.feature.creature.di.creatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EncyclopediaApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@EncyclopediaApp)
            modules(
                networkModule,
                creatureModule
            )
        }
    }
}