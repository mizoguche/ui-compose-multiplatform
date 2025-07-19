package dev.mizoguche.composegram.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.mizoguche.composegram.ui.component.ComposegramIcon
import dev.mizoguche.composegram.ui.component.ComposegramNavigationBar
import dev.mizoguche.composegram.ui.component.ComposegramNavigationBarItem
import dev.mizoguche.composegram.ui.component.ComposegramScaffold
import dev.mizoguche.composegram.ui.component.ComposegramText

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
        route = MainRoute.MyProfile,
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
fun MainScreen(onNavigateToStartup: () -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavItems =
        listOf(
            BottomNavItem.Home,
            BottomNavItem.Profile,
            BottomNavItem.Settings,
        )

    var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

    // 現在のdestinationがBottomNavigationのいずれかの場合、選択状態を更新
    when (currentDestination?.route) {
        MainRoute.Home::class.qualifiedName -> selectedItem = BottomNavItem.Home
        MainRoute.MyProfile::class.qualifiedName -> selectedItem = BottomNavItem.Profile
        MainRoute.Settings::class.qualifiedName -> selectedItem = BottomNavItem.Settings
    }

    ComposegramScaffold(
        bottomBar = {
            ComposegramNavigationBar {
                bottomNavItems.forEach { item ->
                    ComposegramNavigationBarItem(
                        icon = {
                            ComposegramIcon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                            )
                        },
                        label = { ComposegramText(item.label) },
                        selected = selectedItem == item,
                        onClick = {
                            selectedItem = item
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
            modifier = Modifier.fillMaxSize(),
            bottomNavPadding = innerPadding,
        )
    }
}
