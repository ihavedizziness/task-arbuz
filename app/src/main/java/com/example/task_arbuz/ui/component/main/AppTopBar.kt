package com.example.task_arbuz.ui.component.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.task_arbuz.ui.navigation.BottomNavItem

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ShopAppTopBar(
    currentScreen: BottomNavItem,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = currentScreen.title,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier
    )
}