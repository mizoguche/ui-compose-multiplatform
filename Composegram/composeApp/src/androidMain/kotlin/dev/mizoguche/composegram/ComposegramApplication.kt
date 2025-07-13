package dev.mizoguche.composegram

import android.app.Application
import dev.mizoguche.composegram.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class ComposegramApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            appDeclaration = {
                androidLogger()
                androidContext(this@ComposegramApplication)
            }
        )
    }
}