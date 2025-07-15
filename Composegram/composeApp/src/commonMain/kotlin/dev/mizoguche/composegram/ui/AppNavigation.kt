package dev.mizoguche.composegram.ui

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import dev.mizoguche.composegram.domain.post.PostId
import dev.mizoguche.composegram.domain.user.UserId
import dev.mizoguche.composegram.ui.home.HomeRoute
import dev.mizoguche.composegram.ui.postdetail.PostDetailRoute
import dev.mizoguche.composegram.ui.startup.StartupRoute
import dev.mizoguche.composegram.ui.userprofile.UserProfileRoute
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform
import kotlin.reflect.typeOf

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
                    navController.navigate(AppRoute.Home) {
                        popUpTo(AppRoute.Startup) { inclusive = true }
                    }
                },
            )
        }

        composable<AppRoute.Home>(
            exitTransition = { fadeOut() },
            enterTransition = { fadeIn() },
        ) {
            HomeRoute(
                viewModel = koinViewModel(),
                onNavigateToStartup = {
                    navController.navigate(AppRoute.Startup) {
                        popUpTo(AppRoute.Home) { inclusive = true }
                    }
                },
                onNavigateToUserProfile = { userId ->
                    navController.navigate(AppRoute.UserProfile(userId))
                },
                onNavigateToPostDetail = { postId ->
                    navController.navigate(AppRoute.PostDetail(postId))
                },
            )
        }

        composable<AppRoute.UserProfile>(
            exitTransition = { fadeOut() },
            enterTransition = { fadeIn() },
            typeMap = mapOf(typeOf<UserId>() to userIdNavType),
        ) {
            val route = it.toRoute<AppRoute.UserProfile>()
            UserProfileRoute(
                viewModel =
                    koinViewModel {
                        parametersOf(route.userId)
                    },
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }

        composable<AppRoute.PostDetail>(
            exitTransition = { fadeOut() },
            enterTransition = { fadeIn() },
            typeMap = mapOf(typeOf<PostId>() to postIdNavType),
        ) {
            val route = it.toRoute<AppRoute.PostDetail>()
            PostDetailRoute(
                viewModel =
                    koinViewModel {
                        parametersOf(route.postId)
                    },
                onBackClick = {
                    navController.popBackStack()
                },
                onNavigateToUserProfile = { userId ->
                    navController.navigate(AppRoute.UserProfile(userId))
                },
            )
        }
    }
}

val userIdNavType =
    object : NavType<UserId>(isNullableAllowed = false) {
        override fun put(
            bundle: SavedState,
            key: String,
            value: UserId,
        ) {
            bundle.write { putString(key, value.value) }
        }

        override fun get(
            bundle: SavedState,
            key: String,
        ): UserId? {
            val value = bundle.read { getString(key) }
            return UserId(value)
        }

        override fun parseValue(value: String): UserId = UserId(value)
    }

val postIdNavType =
    object : NavType<PostId>(isNullableAllowed = false) {
        override fun put(
            bundle: SavedState,
            key: String,
            value: PostId,
        ) {
            bundle.write { putString(key, value.value) }
        }

        override fun get(
            bundle: SavedState,
            key: String,
        ): PostId? {
            val value = bundle.read { getString(key) }
            return PostId(value)
        }

        override fun parseValue(value: String): PostId = PostId(value)
    }
