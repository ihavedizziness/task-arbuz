package com.example.task_arbuz.ui.component.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.task_arbuz.ui.navigation.ShopNavHost
import com.example.task_arbuz.ui.navigation.BottomNavItem.HOME
import com.example.task_arbuz.ui.navigation.BottomNavItem.CART

@Composable
fun ShopApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = backStackEntry?.destination?.route?.let { route ->
        when (route) {
            HOME.route -> HOME
            CART.route -> CART
            else -> null
        }
    } ?: HOME

    Scaffold(
        topBar = {
            ShopAppTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                },
            )
        },
        content = { innerPadding ->
            ShopNavHost(navController = navController, innerPadding = innerPadding)
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}