package dev.mizoguche.composegram.di

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.request.crossfade
import coil3.util.DebugLogger
import dev.mizoguche.composegram.data.repository.MockPostRepository
import dev.mizoguche.composegram.data.repository.MockUserRepository
import dev.mizoguche.composegram.domain.post.PostRepository
import dev.mizoguche.composegram.domain.user.UserRepository
import dev.mizoguche.composegram.ui.home.HomeViewModel
import dev.mizoguche.composegram.ui.startup.StartupViewModel
import dev.mizoguche.composegram.ui.userprofile.UserProfileViewModel
import dev.mizoguche.composegram.ui.postdetail.PostDetailViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    appDeclaration: KoinAppDeclaration = {},
    platformModule: Module? = null,
) {
    startKoin {
        appDeclaration()
        modules(
            listOfNotNull(
                module {
                    factory { StartupViewModel() }
                    factory { HomeViewModel(get()) }
                    factory { params -> UserProfileViewModel(get(), params.get()) }
                    factory { params -> PostDetailViewModel(get(), params.get()) }

                    singleOf(::MockPostRepository) { bind<PostRepository>() }
                    singleOf(::MockUserRepository) { bind<UserRepository>() }

                    single<ImageLoader> {
                        ImageLoader.Builder(get<PlatformContext>())
                            .crossfade(true)
                            .logger(DebugLogger())
                            .build()
                    }
                },
                platformModule,
            )
        )
    }
}