package dev.mizoguche.composegram

import android.app.Application
import coil3.PlatformContext
import dev.mizoguche.composegram.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.module

class ComposegramApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            appDeclaration = {
                androidLogger()
                androidContext(this@ComposegramApplication)
            },
            platformModule =
                module {
                    single<PlatformContext> { this@ComposegramApplication }
                },
        )
    }
}
