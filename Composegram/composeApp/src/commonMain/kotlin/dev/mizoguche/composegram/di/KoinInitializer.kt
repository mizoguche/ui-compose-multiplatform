package dev.mizoguche.composegram.di

import dev.mizoguche.composegram.ui.home.HomeViewModel
import dev.mizoguche.composegram.ui.startup.StartupViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    appDeclaration: KoinAppDeclaration = {},
) {
    startKoin {
        appDeclaration()
        modules(
            module {
                factory { StartupViewModel() }
                factory { HomeViewModel() }
            }
        )
    }
}