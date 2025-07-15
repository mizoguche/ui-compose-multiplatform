package dev.mizoguche.composegram.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import coil3.ImageLoader
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

private object ImageLoaderHelper : KoinComponent {
    fun getImageLoader(): ImageLoader = get()
}

@Composable
fun rememberImageLoader(): ImageLoader {
    return remember {
        ImageLoaderHelper.getImageLoader()
    }
}