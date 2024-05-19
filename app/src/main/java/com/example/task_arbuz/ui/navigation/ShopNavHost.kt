package com.example.task_arbuz.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.task_arbuz.ui.navigation.BottomNavItem.HOME
import com.example.task_arbuz.ui.navigation.BottomNavItem.CART
import com.example.task_arbuz.ui.screens.cart.CartScreen
import com.example.task_arbuz.ui.screens.home.HomeScreen

@Composable
fun ShopNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = HOME.route,
        modifier = Modifier.padding(innerPadding),
    ) {
        homeComposable()
        cartComposable()
    }
}

fun NavGraphBuilder.homeComposable() {
    composable(
        route = HOME.route,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500))
        },
        content = {
            HomeScreen()
        }
    )
}

fun NavGraphBuilder.cartComposable() {
    composable(
        route = CART.route,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500))
        },
        content = {
            CartScreen()
        }
    )
}