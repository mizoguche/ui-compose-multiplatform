package dev.mizoguche.composegram

import coil3.PlatformContext
import dev.mizoguche.composegram.di.initKoin
import org.koin.dsl.module

fun doInitKoin() {
    initKoin(
        platformModule = module {
            single<PlatformContext> { PlatformContext.INSTANCE }
        }
    )
}