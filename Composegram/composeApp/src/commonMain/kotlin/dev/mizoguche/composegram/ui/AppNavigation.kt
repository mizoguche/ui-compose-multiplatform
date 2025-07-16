package dev.mizoguche.composegram.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.mizoguche.composegram.ui.main.MainScreen
import dev.mizoguche.composegram.ui.startup.StartupRoute
import org.koin.core.parameter.ParametersDefinition
import org.koin.mp.KoinPlatform

@Composable
inline fun <reified T : ViewModel> koinViewModel(noinline parameters: ParametersDefinition? = null): T {
    val koin = KoinPlatform.getKoin()
    return remember {
        koin.get<T>(parameters = parameters)
    }
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Startup,
    ) {
        composable<AppRoute.Startup>(
            exitTransition = { fadeOut() },
            enterTransition = { fadeIn() },
        ) {
            StartupRoute(
                viewModel = koinViewModel(),
                onNavigateToHome = {
                    navController.navigate(AppRoute.Main) {
                        popUpTo(AppRoute.Startup) { inclusive = true }
                    }
                },
            )
        }

        composable<AppRoute.Main>(
            exitTransition = { fadeOut() },
            enterTransition = { fadeIn() },
        ) {
            MainScreen(
                onNavigateToStartup = {
                    navController.navigate(AppRoute.Startup) {
                        popUpTo(AppRoute.Main) { inclusive = true }
                    }
                },
            )
        }
    }
}
