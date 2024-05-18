package com.example.task_arbuz.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    data object HOME : BottomNavItem(route = "home", title = "Home", icon = Icons.Filled.Home)
    data object CART : BottomNavItem(route = "cart", title = "Cart", icon = Icons.Filled.ShoppingCart)
}