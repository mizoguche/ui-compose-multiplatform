package dev.mizoguche.composegram.ui.main

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
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
import dev.mizoguche.composegram.ui.koinViewModel
import dev.mizoguche.composegram.ui.postdetail.PostDetailRoute
import dev.mizoguche.composegram.ui.settings.SettingsRoute
import dev.mizoguche.composegram.ui.userprofile.UserProfileRoute
import org.koin.core.parameter.parametersOf
import kotlin.reflect.typeOf

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
    onNavigateToStartup: () -> Unit,
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Home,
        modifier = modifier,
    ) {
        composable<MainRoute.Home>(
            exitTransition = { fadeOut() },
            enterTransition = { fadeIn() },
        ) {
            HomeRoute(
                viewModel = koinViewModel(),
                onNavigateToSettings = {
                    navController.navigate(MainRoute.Settings)
                },
                onNavigateToUserProfile = { userId ->
                    navController.navigate(MainRoute.UserProfile(userId))
                },
                onNavigateToPostDetail = { postId ->
                    navController.navigate(MainRoute.PostDetail(postId))
                },
            )
        }

        composable<MainRoute.UserProfile>(
            exitTransition = { fadeOut() },
            enterTransition = { fadeIn() },
            typeMap = mapOf(typeOf<UserId>() to userIdNavType),
        ) {
            val route = it.toRoute<MainRoute.UserProfile>()
            UserProfileRoute(
                viewModel =
                    koinViewModel {
                        parametersOf(route.userId)
                    },
                onBackClick = {
                    navController.popBackStack()
                },
                isRootScreen = false,
            )
        }

        composable<MainRoute.MyProfile>(
            exitTransition = { fadeOut() },
            enterTransition = { fadeIn() },
        ) {
            UserProfileRoute(
                viewModel =
                    koinViewModel {
                        parametersOf(UserId("user1"))
                    },
                onBackClick = {
                    navController.popBackStack()
                },
                isRootScreen = true,
            )
        }

        composable<MainRoute.PostDetail>(
            exitTransition = { fadeOut() },
            enterTransition = { fadeIn() },
            typeMap = mapOf(typeOf<PostId>() to postIdNavType),
        ) {
            val route = it.toRoute<MainRoute.PostDetail>()
            PostDetailRoute(
                viewModel =
                    koinViewModel {
                        parametersOf(route.postId)
                    },
                onBackClick = {
                    navController.popBackStack()
                },
                onNavigateToUserProfile = { userId ->
                    navController.navigate(MainRoute.UserProfile(userId))
                },
            )
        }

        composable<MainRoute.Settings>(
            exitTransition = { fadeOut() },
            enterTransition = { fadeIn() },
        ) {
            SettingsRoute(
                viewModel = koinViewModel(),
                onBackClick = {
                    navController.popBackStack()
                },
                onNavigateToStartup = onNavigateToStartup,
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
