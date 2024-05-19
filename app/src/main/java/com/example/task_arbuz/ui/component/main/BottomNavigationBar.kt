package com.example.task_arbuz.ui.component.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.task_arbuz.core.func.Resource
import com.example.task_arbuz.ui.navigation.BottomNavItem
import com.example.task_arbuz.ui.screens.cart.CartViewModel

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    var cartCount by remember { mutableIntStateOf(0) }
    val cartProductsState by cartViewModel.cartProductsState.collectAsState()

    val navItemList = listOf(
        BottomNavItem.HOME,
        BottomNavItem.CART,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .shadow(
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                elevation = 12.dp
            )
            .clickable {
                when (val state = cartProductsState) {
                    is Resource.Success -> { cartCount = state.data.size }
                    else -> {
                        println("evevewsd")
                    }
                }
            }
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
    ) {
        navItemList.forEach { screen ->
            NavigationBarItem(
                selected = navBackStackEntry?.destination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route)
                },
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Row {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title,
                            tint = if (navBackStackEntry?.destination?.route == screen.route) MaterialTheme.colorScheme.primary else LocalContentColor.current
                        )
                        if (screen == BottomNavItem.CART) {
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "$cartCount",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                ),
                            )
                        }
                    }
                }
            )
        }
    }

}
