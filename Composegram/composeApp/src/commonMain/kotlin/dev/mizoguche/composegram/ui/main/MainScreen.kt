package dev.mizoguche.composegram.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.mizoguche.composegram.domain.user.UserId

sealed class BottomNavItem(
    val route: MainRoute,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val label: String,
) {
    data object Home : BottomNavItem(
        route = MainRoute.Home,
        icon = Icons.Default.Home,
        label = "ホーム",
    )

    data object Profile : BottomNavItem(
        route = MainRoute.UserProfile(UserId("user1")),
        icon = Icons.Default.Person,
        label = "プロフィール",
    )

    data object Settings : BottomNavItem(
        route = MainRoute.Settings,
        icon = Icons.Default.Settings,
        label = "設定",
    )
}

@Composable
fun MainScreen(
    onNavigateToStartup: () -> Unit,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Settings,
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                            )
                        },
                        label = { Text(item.label) },
                        selected = currentDestination?.hierarchy?.any { destination ->
                            when (item) {
                                is BottomNavItem.Home -> destination.route == MainRoute.Home::class.qualifiedName
                                is BottomNavItem.Profile -> destination.route == MainRoute.UserProfile::class.qualifiedName
                                is BottomNavItem.Settings -> destination.route == MainRoute.Settings::class.qualifiedName
                            }
                        } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
            }
        },
    ) { innerPadding ->
        MainNavigation(
            navController = navController,
            onNavigateToStartup = onNavigateToStartup,
            modifier = Modifier.padding(innerPadding),
        )
    }
}